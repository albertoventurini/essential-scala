import scala.annotation.tailrec

sealed trait MyList[A] {
  def length: Int = this match {
    case End() => 0
    case Cons(_, tail) => 1 + tail.length
  }

  @tailrec
  final def contains(elem: A): Boolean = this match {
    case End() => false
    case Cons(head, tail) => head == elem || tail.contains(elem)
  }

//  def apply(index: Int): A =
//    if(index < 0) throw new Exception
//    else if(index == 0) {
//      this match {
//        case End() => throw new Exception
//        case Cons(head, _) => head
//      }
//    }
//    else this.apply(index - 1)

  def apply(index: Int): A = this match {
    case End() => throw new Exception
    case Cons(_, _) if index < 0 => throw new Exception
    case Cons(head, _) if index == 0 => head
    case Cons(_, tail) if index > 0 => tail.apply(index - 1)
  }
}

final case class End[A]() extends MyList[A]
final case class Cons[A](head: A, tail: MyList[A]) extends MyList[A]

val example = Cons(1, Cons(2, Cons(3, End())))
assert(example(0) == 1)
assert(example(1) == 2)
assert(example(2) == 3)
assert(try {
  example(3)
  false
} catch {
  case e: Exception => true
})