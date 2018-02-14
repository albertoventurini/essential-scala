import scala.util.{Failure, Success, Try}

// This example uses Try instead of Either.
def calculator(operand1: String, operator: String, operand2: String): String = {

  def safeOperand(s: String): Try[Int] = {
    try {
      Success(s.toInt)
    } catch {
      case e: Exception => throw new Exception(s"Operand $s is not an int")
    }
  }

  def safeDivision: (Int, Int) => Try[Int] =
    (a, b) =>
      if(b == 0)
        throw new Exception("Division by zero")
      else
        Success(a / b)

  def safeOperator(operator: String): (Int, Int) => Try[Int] =
    operator match {
      case "+" => (a, b) => Success(a + b)
      case "-" => (a, b) => Success(a - b)
      case "*" => (a, b) => Success(a * b)
      case "/" => safeDivision
    }

  val res: Try[Int] = for {
    x <- safeOperand(operand1)
    y <- safeOperand(operand2)
    result <- safeOperator(operator)(x, y)
  } yield result

  res match {
    case Failure(e) => throw e
    case Success(result) => result.toString
  }
}

calculator("1", "/", "0")