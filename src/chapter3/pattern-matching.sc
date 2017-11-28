case class Cat(colour: String, food: String)

object ChipShop {
  def willServe(cat: Cat): Boolean = cat match {
    case Cat(_, "chips") => true
    case _ => false
  }
}

case class Person(firstName: String, lastName: String) {
  def name = s"$firstName $lastName"
}

object Stormtrooper {
  def inspect(person: Person): String =
    person match {
      case Person("Luke", "Skywalker") => "Stop, rebel scum!"
      case Person("Han", "Solo") => "Stop, rebel scum!"
      case Person(first, _) => s"Move along, $first"
    }
}