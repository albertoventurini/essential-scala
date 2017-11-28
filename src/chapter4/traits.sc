trait Feline {
  def colour: String
  def sound: String
}

// It's bad practice to give a default sound in Feline,
// because we would override that in Cats.
// Rather define an intermediate trait with a common
// sound for all big cats.
trait BigCat extends Feline {
  override val sound: String = "roar"
}

case class Tiger(colour: String) extends BigCat

case class Lion(colour: String, maneSize: Int) extends BigCat

case class Panther(colour: String) extends BigCat

case class Cat(colour: String, food: String) extends Feline {
  override val sound: String = "meow"
}


val p = Panther("black")
p.sound
p.colour

val c = Cat("ginger", "chips")
c.sound
c.food

