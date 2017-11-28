// A traffic light is red, green, or yellow. Translate this description into Scala code.

sealed trait TrafficLight
final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Yellow extends TrafficLight


// A calculation may succeed (with an Int result) or fail (with a String message). Implement this.

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(message: String) extends Calculation


// Boiled water has a size (an Int), a source (which is a well, spring, or tap), and a Boolean carbonated.
// Implement this in Scala.

sealed trait Source
final case object Well extends Source
final case object Spring extends Source
final case object Tap extends Source

trait BoiledWater {
  def size: Int
  def source: Source
  def carbonated: Boolean
}