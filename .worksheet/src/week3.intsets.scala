package week3

object intsets {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(129); val res$0 = 
  //new IntSet /*'abstract' class can not be initiated*/
  1;System.out.println("""res0: Int(1) = """ + $show(res$0));$skip(227); val res$1 =  //at least one expression required,
  //also 'main' method needed
  //new works with classes not with objects ?
  //because 'type' needed
  /*new NonEmpty(3, new Empty, new Empty)
  val t1 = new NonEmpty(3, new Empty, new Empty)*/
  new NonEmpty(3, Empty, Empty);System.out.println("""res1: week3.NonEmpty = """ + $show(res$1));$skip(41); 
  val t1 = new NonEmpty(3, Empty, Empty);System.out.println("""t1  : week3.NonEmpty = """ + $show(t1 ));$skip(21); 
  val t2 = t1 incl 4;System.out.println("""t2  : week3.IntSet = """ + $show(t2 ));$skip(23); 
  val t3 = t1 union t2;System.out.println("""t3  : week3.IntSet = """ + $show(t3 ));$skip(15); val res$2 = 
	t2 contains 4;System.out.println("""res2: Boolean = """ + $show(res$2));$skip(16); val res$3 = 
	t2.contains(3);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(15); val res$4 = 
	t2 contains 1;System.out.println("""res4: Boolean = """ + $show(res$4));$skip(195); 
	
  //standalone applications *.scala files
  /** Unit test */
  //def main(args: Array[String]): Unit = {
  //or
  def main(args: Array[String]) = {
    println("Binary tree t2 is: " + t2)
  };System.out.println("""main: (args: Array[String])Unit""")}
}

abstract class IntSet {
  //in 'abstract' class methods allowed without bodies
  //or exact method definitions

  //include set in self
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  //exersise: implement
  def union(other: IntSet): IntSet
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
//use / defines a singleton object
//created by first reference to it
//Singleton objects are values, so 'Empty' evaluates to itself.*/
//?object and class with same name - conflicts ?
//?or object not able / override replace class ?
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet =
    //here 'Empty' already a value,
    //so no evaluation needed
    //no 'new' key word on 'Empty' as it is a reference, not a type
    new NonEmpty(x, Empty, Empty)

  //as itself is empty, so union gains 'other'
  def union(other: IntSet): IntSet = {
    other
  }
  
  //visual representation of tree node
  override def toString = {
    "."
  }
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem)
    	//Any method with a parameter can be used like
    	//an infix operator.
      left contains x //<=> to: left.contains(x)
      //recursion
      //if 'IntSet' 'Empty' then return 'false'
    else if (x > elem)
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
  that include previous tree vertion
  */
  def incl(x: Int): IntSet =
    if (x < elem)
      new NonEmpty(elem, left incl x, right)
    else if (x > elem)
      new NonEmpty(elem, left, right incl x)
    //exact match found / element exist already
    else
      this

  //as itSelf is noNempty, so union gains it's constituents
  //a parts of a whole - self + other
  def union(other: IntSet): IntSet = {
    //recursion
    //when it is rerminate ?
    //every following / successing 'union' call
    //operates with smaller arguments then it is starts
    //so it is converges to zero
    //((left union right) union other) incl elem
    //anather / other notation
    ((this.left.union(right)).union(other)).incl(elem)
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
