//A binary tree of integers can be defined as follows:
//A Tree is a Node with a leaf and right Tree or a Leaf with an element of type Int.
//Implement this algebraic data type.
//Implement sum and double on Tree using polymorphism and pattern matching.



sealed trait Tree {

  def sum: Int = this match {
    case Leaf(value) => value
    case Node(left, right) => left.sum + right.sum
  }

  def double: Tree = this match {
    case Leaf(value) => Leaf(value*2)
    case Node(left, right) => Node(left.double, right.double)
  }

}

final case class Leaf(element: Int) extends Tree
final case class Node(left: Tree, right: Tree) extends Tree

val tree1 = Node(Node(Leaf(1), Leaf(2)), Leaf(3))

assert(tree1.sum == 6)

tree1.double