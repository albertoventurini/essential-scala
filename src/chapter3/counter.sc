class Counter(val count: Int) {
  def inc(amount: Int = 1): Counter = new Counter(count + amount)
  def dec(amount: Int = 1): Counter = new Counter(count - amount)

  def adjust(adder: Adder): Counter = new Counter(adder.add(count))
}

class Adder(amount: Int) {
  def add(in: Int): Int = in + amount
}

val counter = new Counter(10).inc().dec().inc().inc()
counter.adjust(new Adder(5)).count
