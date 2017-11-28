case class Cat(colour: String, food: String)

val oswald = Cat(colour = "Black", food = "Milk")
val henderson = Cat(colour = "Ginger", food = "Chips")
val quentin = Cat(colour = "Tabby and white", food = "Curry")

case class Counter(count: Int) {
  def inc(amount: Int = 1): Counter = copy(count + amount)
  def dec(amount: Int = 1): Counter = copy(count - amount)
}

case class Person(firstName: String, lastName: String) {
  def name = firstName + " " + lastName
}

