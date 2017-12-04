import scala.annotation.tailrec

sealed trait IntList {

  def length: Int = {

    @tailrec
    def lengthRec(list: IntList, acc: Int): Int = list match {
      case End => acc
      case Pear(_, tail) => lengthRec(tail, acc + 1)
    }

    lengthRec(this, 0)

  }

  def product: Int = this match {
    case End => 1
    case Pear(head, tail) => head * tail.product
  }

  def double: IntList = this match {
    case End => End
    case Pear(head, tail) => Pear(head * 2, tail.double)
  }
}

final case object End extends IntList
final case class Pear(head: Int, tail: IntList) extends IntList


val example = Pear(1, Pear(2, Pear(3, End)))
assert(example.length == 3)
assert(example.tail.length == 2)
assert(End.length == 0)

assert(example.product == 6)
assert(example.tail.product == 6)
assert(End.product == 1)

assert(example.double == Pear(2, Pear(4, Pear(6, End))))
assert(example.tail.double == Pear(4, Pear(6, End)))
assert(End.double == End)
