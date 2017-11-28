sealed trait DivisionResult

final case class Finite(result: Int) extends DivisionResult

final case object Infinite extends DivisionResult

object divide {
  def apply(a: Int, b: Int): DivisionResult = b match {
    case 0 => Infinite
    case _ => Finite(a/b)
  }
}

def doDivide(a: Int, b: Int) = divide(a, b) match {
  case Infinite => println("infinity!")
  case Finite(result) => println(s"$a / $b = $result")
}

doDivide(1, 2)
doDivide(1, 0)