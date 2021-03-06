# Chapter 3 - Objects and classes

## Types and values namespaces

Scala has two distinct namespaces: a space of *type names* and a space of *value names*.

This separation allows us to have a class and an object (the companion object) with the same name.
The object is a value and, as such, it belongs to the space of value names.
The class, on the other hand, belongs to the space of type names.

## Additional constructors

It is common to use the `apply()` method in companion objects to provide additional constructors for a class
(or even to provide easier access to the default constructor, so as to avoid the keyword `new`).

For example:

```
class Person(val fullName: String)

object Person {
  def apply(firstName: String, lastName: String) = new Person(s"$firstName $lastName")
}
```

## Case classes (3.4)

Case classes have the following useful features:

- a field for each constructor argument (same as prepending constructor arguments with `val` for normal classes)
- default `toString` method
- `equals` and `hashCode` methods
- `copy` method to create new copies (possibly with different parameters)
- default companion object with:
  - `apply` method (constructor shorthand, people prefer this)
  - extractor pattern to use in pattern matching

There are also **case objects**. They are like case classes but they don't have constructor parameters.


# Chapter 4 - Traits

## Defining traits

```
import java.util.Date

trait Visitor {
  def id: String // Unique id assigned to each user
  def createdAt: Date // Date this user first visited the site
  // How long has this visitor been around?
  def age: Long = new Date().getTime - createdAt.getTime
}

case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

case class User(
  id: String,
  email: String,
  createdAt: Date = new Date()
) extends Visitor
```

Things to note:
- the `Visitor` trait defines two abstract methods, `id` and `createdAt`
- the case classes implement these abstract methods as `val`s (implicit from the definition of case class).
This is legal because, to Scala, `def` is a more general version of `val`.

It is good practice to never define `val`s in a trait, but rather to use `def`. A concrete implementation
can then implement it using using a `def` or `val` as appropriate.

## Sealed trait and final classes

When a trait is marked as `sealed`, all the types extending that trait must be defined in the same file.
Moreover, if there is a pattern matching that doesn't contain some of the types, the compiler issues a warning.

The types that extend a trait can be marked as `final`. This means they can't be further extended.

Example (sealed trait / final class pattern):

```
sealed trait Visitor { /* ... */ }

final case class User(/* ... */) extends Visitor

final case class Anonymous(/* ... */) extends Visitor
```

## Product type pattern

`A` has a `b` (of type `B`) and a `c` (of type `C`). This can be expressed in two ways.

- Using a case class

```
case class A(b: B, c: C)
```

- Using a trait

```
trait A {
  def b: B
  def c: C
}
```

## Sum type pattern

`A` is a `B` or a `C`. This can be expressed with the sealed trait / final class pattern:

```
sealed trait A
final case class B() extends A
final case class C() extends A
```

# Chapter 5 - Modelling data with generic types

## Fold Pattern

Fold is not limited to sequences. Indeed, the fold pattern can be applied to any
algebraic data type.

For an algebraic data type A, fold converts it to a generic type B. Fold is a structural recursion with:
- one function parameter for each case in A;
- each function takes as parameters the fields for its associated class;
- if A is recursive, any function parameters that refer to a recursive field take a parameter of type B.
The right-hand side of pattern matching cases, or the polymorphic methods as appropriate, consists of
calls to the appropriate function.

Example with `Maybe`:

```
sealed trait Maybe[A] {

  // Method parameters contain functions for each case class.
  // In case of empty, the function collapses to a value.
  def fold[B](empty: B, full: A => B): B = this match {

    // Here we pattern match all case classes
    case Empty() => empty
    case Full(value) => full(value)
  }

}

final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]
```

Example with `Sum` (which is basically like `Either`):

```
sealed trait Sum[A, B] {

  def fold[C](left: A => C, right: B => C): C = this match {
    case Left(value) => left(value)
    case Right(value) => right(value)
  }

}

final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]
```

## Map

Given a type `F[A]` and a function `A => B`, map gives `F[B]`.

For example, given a `Maybe[User]` and a function `User => Order`, map gives a `Maybe[Order]`.

## FlatMap

Given a type `F[A]` and a function `A => F[B]`, flatMap gives `F[B]`.

For example, given a `Maybe[User]` and a function `User => Maybe[Order]`, flatMap gives a `Maybe[Order]`.

## Functors and monads

A type `F[A]` with a `map` method is called a *functor*. If a functor also has a `flatMap`, it is called a *monad*.

Monads are useful to sequence computations with context. An example of context is failure.
So we can have a sequence of computations, each of which can fail, and we sequence them using a flatMap as such:

```
getUser().flatMap(getOrder(_))
```

# Chapter 6

## For comprehension

Another convenient way to chain monadic operations together is provided by *for comprehensions*.

For example, suppose we are using the `Either` monad to denote an operation that can either succeed or return an error message:

```
  def safeOperand(s: String): Either[String, Int] = {
    try {
      Right(s.toInt)
    } catch {
      case e: Exception => Left(s"Operand $s is not an int")
    }
  }
```

The `Either` monad is right-biased, which means that the right side implements `flatMap` and `map`
in a way that chains further operations, whereas the left side stops the chain. We can use `Either` with `flatMap`:

```
safeOperand("1").flatMap(o1 => safeOperand("2").map(o2 => o1 + o2)) // Right(3)
safeOperand("1").flatMap(o1 => safeOperand("abc").map(o2 => o1 + o2)) // Left(Operand abc is not an int)
```

A for comprehension is arguably more readable:

```
for {
  o1 <- safeOperand("1")    // 'o1' is bound to an Int (not a Right[Int])
  o2 <- safeOperand("abc")  // 'o2' is bound to an Int (not a Right[Int])
} yield o1 + o2             // the result is wrapped back into a Right[Int]
```

In the example above, the chain of generators will short-circuit if a generator provides a `Left` value;
on the other hand, it will reach the `yield` expression if all the generators have provided `Right` values.
The result of the `yield` expression is then wrapped back into a monad.

