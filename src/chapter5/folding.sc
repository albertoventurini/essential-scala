sealed trait Maybe[A] {

  def fold[B](empty: B, full: A => B): B = this match {
    case Empty() => empty
    case Full(value) => full(value)
  }

}

final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]


sealed trait Sum[A, B] {

  def fold[C](left: A => C, right: B => C): C = this match {
    case Left(value) => left(value)
    case Right(value) => right(value)
  }

}

final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]

