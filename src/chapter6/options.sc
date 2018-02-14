// Write a method addOptions that accepts two parameters of type Option[Int] and adds them together. Use
// a for comprehension to structure your code.

def addOptions(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  for {
    a <- optionA
    b <- optionB
  } yield a + b

addOptions(Some(1), Some(2))

// Write a second version of your code using map and flatMap instead of a for comprehension.

def addOptions2(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.map(b => a+b))

// Overload addOptions with another implementa􀦞on that accepts three Option[Int] parameters and adds
// them all together.

def addOptions(optionA: Option[Int], optionB: Option[Int], optionC: Option[Int]): Option[Int] =
  for {
    a <- optionA
    b <- optionB
    c <- optionC
  } yield a + b + c

def addOptions2(optionA: Option[Int], optionB: Option[Int], optionC: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.flatMap(b => optionC.map(c => a + b + c)))


def calculator(operand1: String, operator: String, operand2: String): String = {

  def safeOperand(s: String): Either[String, Int] = {
    try {
      Right(s.toInt)
    } catch {
      case Exception => Left(s"Operand $s is not an int")
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

  val res: String = for {
    x <- safeOperand(operand1)
    y <- safeOperand(operand2)
    result <- safeOperator(operator)(x, y)
  } yield result

  res

}

calculator("1", "+", "2")