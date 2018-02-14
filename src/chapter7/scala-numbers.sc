final case class Rational(numerator: Int, denominator: Int)

// Implement an Ordering for Rational to order rationals from smallest to largest.

implicit val rationalOrdering: Ordering[Rational] =
  Ordering.fromLessThan {
    case (Rational(n1, d1), Rational(n2, d2)) => n1*d2 < n2*d1
  }
//  Ordering.fromLessThan((x, y) => x.numerator*y.denominator < y.numerator*x.denominator)

assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
  List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

