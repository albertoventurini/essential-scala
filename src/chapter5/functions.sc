sealed trait IntList {

  def fold[A](end: A, f: (Int, A) => A): A = {
    this match {
      case End => end
      case Cons(head, tail) => f(head, tail.fold(end, f))
    }
  }

  def sum: Int = this.fold(0, (a, b) => a+b)
  def product: Int = this.fold(1, (a, b) => a*b)
  def length: Int = this.fold(0, (_, b) => 1 + b)
  def double: IntList = this.fold(End, (head, tail) => Cons(head*2, tail.double))

}

final case object End extends IntList
final case class Cons(head: Int, tail: IntList) extends IntList


val example = Cons(1, Cons(2, Cons(3, End)))
assert(example.length == 3)
assert(example.tail.length == 2)
assert(End.length == 0)

assert(example.product == 6)
assert(example.tail.product == 6)
assert(End.product == 1)

assert(example.double == Cons(2, Cons(4, Cons(6, End))))
assert(example.tail.double == Cons(4, Cons(6, End)))
assert(End.double == End)
