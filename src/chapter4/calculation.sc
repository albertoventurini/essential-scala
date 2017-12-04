sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

object Calculator {
  def +(calculation: Calculation, operand: Int): Calculation = {
    calculation match {
      case Failure(msg) => Failure(msg)
      case Success(result) => Success(result + operand)
    }
  }

  def -(calculation: Calculation, operand: Int): Calculation =
    operation((a, b) => Success(a - b))(calculation, operand)

  def /(calculation: Calculation, operand: Int): Calculation = {
    def divide = (a: Int, b: Int) => b match {
      case 0 => Failure("Division by zero")
      case _ => Success(a/b)
    }

    operation(divide)(calculation, operand)
  }

  def operation(combiner: (Int, Int) => Calculation)(calculation: Calculation, operand: Int) = {
    calculation match {
      case Failure(msg) => Failure(msg)
      case Success(result) => combiner(result, operand)
    }
  }
}

assert(Calculator.+(Success(1), 1) == Success(2))
assert(Calculator.-(Success(1), 1) == Success(0))
assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))
assert(Calculator./(Success(4), 2) == Success(2))
assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))