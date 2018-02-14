def calculator(operand1: String, operator: String, operand2: String): String = {

  def safeOperand(s: String): Either[String, Int] = {
    try {
      Right(s.toInt)
    } catch {
      case e: Exception => Left(s"Operand $s is not an int")
    }
  }

  def safeDivision: (Int, Int) => Either[String, Int] =
    (a, b) =>
      if(b == 0)
        Left("Division by zero")
      else
        Right(a / b)

  def safeOperator(operator: String): (Int, Int) => Either[String, Int] =
    operator match {
      case "+" => (a, b) => Right(a + b)
      case "-" => (a, b) => Right(a - b)
      case "*" => (a, b) => Right(a * b)
      case "/" => safeDivision
    }

  // Either is right-based. This means that the right value is "success",
  // the right value is "failure" and will flatmap to a failure
  // (i.e. will not process further steps of the for comprehension)
  val res: Either[String, Int] = for {
    x <- safeOperand(operand1)
    y <- safeOperand(operand2)
    result <- safeOperator(operator)(x, y)
  } yield result

  res match {
    case Left(msg) => msg
    case Right(result) => result.toString
  }
}

calculator("1", "+", "2")