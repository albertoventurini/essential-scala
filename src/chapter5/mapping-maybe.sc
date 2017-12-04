// Implement map for Maybe

// For bonus points, implement map in terms of flatMap.

sealed trait Maybe[+A] {

//  def map[B](fn: A => B): Maybe[B] = this match {
//    case Empty => Empty
//    case Full(value) => Full(fn(value))
//  }

  def flatMap[B](fn: A => Maybe[B]): Maybe[B] = this match {
    case Empty => Empty
    case Full(value) => fn(value)
  }

  def map[B](fn: A => B): Maybe[B] =
    this.flatMap(a => Full(fn(a)))

}

final case object Empty extends Maybe[Nothing]
final case class Full[A](value: A) extends Maybe[A]


val list = List(Full(3), Full(2), Full(1))

list.map(m => m match { case Full(value) => if(value % 2 == 0) Full(value) else Empty })

list.map(m => m.flatMap(x => if(x % 2 == 0) Full(x) else Empty))