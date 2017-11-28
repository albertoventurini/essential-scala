trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double) extends Shape {
  val sides: Int = 1
  val perimeter: Double = 2 * Math.PI * radius
  val area: Double = Math.PI * radius * radius
}

// Trait extending another trait.
// We suppose we'll get width and height from
// implementations, and we provide default fields
// based on those.
trait Rectangular extends Shape {
  def width: Double
  def height: Double

  val sides: Int = 4
  val perimeter: Double = width*2 + height*2
  val area: Double = width*height
}

case class Rectangle(width: Double, height: Double) extends Rectangular

case class Square(size: Double) extends Rectangular {
  val width: Double = size
  val height: Double = size
}