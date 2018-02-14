// Write a method to find the smallest element of a Seq[Int].

def smallest(seq: Seq[Int]): Int =
  seq.foldLeft(Int.MaxValue)((min, cur) => math.min(min, cur))

smallest(List(7,1,8,23,-83,39,-1982))

// Given Seq(1, 1, 2, 4, 3, 4) create the sequence containing each number only once.

Seq(1, 1, 2, 4, 3, 4)
  .foldLeft(Seq[Int]())((seq, i) => if(seq.contains(i)) seq else seq :+ i)

// Write a func􀦞on that reverses the elements of a sequence
// Note: foldRight would be inefficient with linked lists

def reverse(seq: Seq[Int]): Seq[Int] =
  seq.foldLeft(Seq[Int]())((seq, i) => i +: seq)

reverse(List(1,6,2,4,3))

// Write map in terms of foldRight.
// Note: foldLeft would be inefficient with linked lists

def map(seq: Seq[Int])(f: Int => Int): Seq[Int] =
  seq.foldRight(Seq[Int]())((i, seq) => f(i) +: seq)

map(List(1,2,3,4,5)) {
  i => i * 2
}

// Write your own implementa􀦞on of foldLeft that uses foreach and mutable state.
// Remember: foldLeft applies f to the zero and the first element (starts from the left)
// whereas foldRight would apply f to the last element and the zero (starts from the right)

def foldLeft[A, B](seq: Seq[A], zero: B)(f: (B, A) => B): B = {
  var acc: B = zero
  seq.foreach(i => acc = f(acc, i))
  acc
}

foldLeft(List(1,2,3,4,5), 0)(_ + _)