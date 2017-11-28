class Cat(val colour: String, val food: String)

val oswald = new Cat(colour = "Black", food = "Milk")
val henderson = new Cat(colour = "Ginger", food = "Chips")
val quentin = new Cat(colour = "Tabby and white", food = "Curry")

object ChipShop {
  def willServe(cat: Cat): Boolean = "Chips".equals(cat.food)
}

ChipShop.willServe(oswald)
ChipShop.willServe(henderson)