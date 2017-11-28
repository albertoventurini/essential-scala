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

