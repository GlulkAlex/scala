package week3

object intsets {
  //new IntSet /*'abstract' class can not be initiated*/
  1 //at least one expression required,           //> res0: Int(1) = 1
  //also 'main' method needed

  //'new' works with classes
  //not with objects
  //as object is an instance of class &
  //class is like blueprint for object instance of it
  //? (actual implementation) ?
  //because 'type' needed
  /*new NonEmpty(3, new Empty, new Empty)
  val t1 = new NonEmpty(3, new Empty, new Empty)*/
  new NonEmpty( 3, Empty, Empty )                 //> res1: week3.NonEmpty = {.3.}
  //NonEmpty( 7, Empty, Empty )
  val t1 = new NonEmpty( 3, Empty, Empty )        //> t1  : week3.NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : week3.IntSet = {.3{.4.}}
  val t3 = t1 union t2                            //> t3  : week3.IntSet = {.3{.4.}}
  t2 contains 4                                   //> res2: Boolean = true
  t2.contains( 3 )                                //> res3: Boolean = true
  t2 contains 1                                   //> res4: Boolean = false

  //standalone applications *.scala files
  /** Unit test */
  //def main(args: Array[String]): Unit = {
  //or
  def main( args: Array[ String ] ) = {
    println( "Binary tree t2 is: " + t2 )
  }                                               //> main: (args: Array[String])Unit
}

/* The Laws of IntSet
What does it mean to
prove the correctness of this implementation?
One way to define and show
the correctness of an implementation consists of
proving the laws that it respects.
In the case of 'IntSet', we have
the following three laws:
  For any set s, and elements x and y:
    (1.) Empty contains x = false
    (2.) (s incl x) contains x = true
    (3.) (s incl x) contains y = s contains y if x != y
(In fact,
we can show that
these laws completely characterize the desired data type)
*/

/*Exercise (Hard)
The correctness of union can be translated into the following law:
Proposition 4:
  (xs union ys) contains x = xs contains x || ys contains x
Show proposition 4 by using structural induction on xs.
*/

/** non trivial implementation of Set
  * as binary tree
  * (? tree like structure ?)
  */
abstract class IntSet {
  //in 'abstract' class methods allowed without bodies
  //or exact method definitions

  //include (? more like 'insert' or 'add' as prefix ?) set in self
  def incl( x: Int ): IntSet
  def contains( x: Int ): Boolean
  //exersise: implement
  def union( other: IntSet ): IntSet
}

//subClasses - like (used as) substitude for superClass 'IntSet'
//'Object' is root ('base' / 'parent') class of all classes
/*class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  //end(ing) 'nod' with empty left and right 'leafs' / 'subTrees'
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)

  //as itself is empty, so union gains 'other'
  def union(other: IntSet): IntSet = {
    other
  }

  //visual representation of tree node
  override def toString = {
    "."
  }
}*/

/*or for simplisity
//use / defines a 'singleton' object
//created by first reference to it
//Singleton objects are values, so 'Empty' evaluates to itself.*/
//?object and class with same name - conflicts ?
//?or object not able / override replace class ?
/** 'Empty' as singleton
  */
object Empty extends IntSet {
  def contains( x: Int ): Boolean = false
  def incl( x: Int ): IntSet =
    //here 'Empty' already a value,
    //so no evaluation needed
    //no 'new' key word on 'Empty' as it is a reference, not a type
    new NonEmpty( x, Empty, Empty )

  //as itself is empty, so union gains 'other'
  def union( other: IntSet ): IntSet = other

  //visual representation of tree node
  override def toString = {
    "."
  }
}

/** assumption on tree structure:
  * @left < @elem <@right
  * where @elem is root node
  * @left & @right is subtree or leafs
  */
class NonEmpty( elem: Int,
                left: IntSet,
                right: IntSet ) extends IntSet {
  /*traversal*/
  def contains( x: Int ): Boolean =
    if ( x < elem )
      //Any method with a parameter can be used like
      //an infix operator.
      left contains x //<=> to: left.contains(x)
    //recursion
    //if 'IntSet' 'Empty' then return 'false'
    else if ( x > elem )
      right contains x
    //exact match found / exist already
    else
      true

  //persistent data structure is
  //a data structure that
  //always preserves
  //the previous version of itself
  //when it is modified
  /*
  no mutations here
  pure functional aproach
  means
  every time, when 'changes' occure,
  it is created new larger copy of tree
  that include previous tree version
  */
  def incl( x: Int ): IntSet =
    if ( x < elem )
      new NonEmpty( elem, left incl x, right )
    else if ( x > elem )
      new NonEmpty( elem, left, right incl x )
    //exact match found / element exist already
    else
      this

  //as itSelf is noNempty, so union gains it's constituents
  //a parts of a whole - self + other
  def union( other: IntSet ): IntSet = {
    //recursion
    //when it is terminate ?
    //every following / successing 'union' call
    //operates with smaller arguments then it is starts
    //so it is converges to zero
    //((left union right) union other) incl elem
    ( left union ( right union ( other ) ) ) incl elem
    //or another / other notation
    /*( ( this.left.union( right ) )
      .union( other ) )
      .incl( elem )*/
  }

  //visual representation of tree node
  override def toString = {
    "{" + left + elem + right + "}"
  }
}

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  //additional explicity for error protection
  //overrides actual existing / present definition / implementation
  override def foo = 2
  //only abstract method was defined,
  //so no 'override' needed,
  //but posible
  def bar = 3
}