// Write a method that takes two sets and returns a set containing the union of the elements.

def union[A](s1: Set[A], s2: Set[A]): Set[A] =
  s2.foldLeft[Set[A]](s1)((s, i) => s + i)

def mapUnion[A](m1: Map[A, Int], m2: Map[A, Int]): Map[A, Int] =
  m1.map { case (k, v) => k -> (v + m2.getOrElse(k, 0)) }


val map1 = Map("a" -> 1, "b" -> 2, "c" -> 3)
val map2 = Map("a" -> 3, "b" -> 5)

mapUnion(map1, map2)