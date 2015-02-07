package week4
/*import week3.IntSet 
import week3.NonEmpty
import week3.Empty
import week3.intsets.t1*/
import week3._
//import week4.Nat

/*
 * for invariant type 'T'
 */
//trait List[T] {
/* for covariant 'T' */
trait List[+T] {
  //with type parameter [T]
  def isEmpty: Boolean
  //for non empty list
  def head: T
  //for remaining list
  def tail: List[T]
  //add new elem at the begining of the list
  //then add current list at the end as 'tail'
  //so, method creates a new list from 'elem' and 'this'
  /* must be covariant type error 
   * to prevent mutation in unmutable (covariant) objects
  def prepend(elem: T): List[T] = new Cons(elem, this)*/
  /*
   * lower bound
   * for covariant parameters types 
   */
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

//implementation
//'cons' stands for construction operation
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  //head, tail - are fields 
  //cause it never empty
  //val definitions - is special cases of methods
  //can implement and overwrite / override abstract methods in 'traits'

  /*used for passing parameters, that not allowed in 'trait', and <=> to:
  class Cons(_head: Int, _tail: IntList) extends IntList {
    val head = _head
    val tail = _tail*/
  //?so 'fields' in 'trait' are redefined by 'val' as parameters ? 
  //?with the same names ?

  def isEmpty = false

  override def toString = {
    "[" + head + "|" + tail + "]"
  }
}
/*
class Nil[T] extends List[T] {
  def isEmpty = true
  //Can not take a head of Nil 
  def head = throw new NoSuchElementException("Nil.head")
  //if type explicitly stated / mentioned
  //'Nothing' is subtype of any other type
  //so 'Nothing' is a 'T' - that is example of 'subtyping' in polymorphism 
  //mostly from OOP languages
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  
  //visual representation of tree node
  override def toString = {
    "Nil"
  }
}
*/
/**
 * refactor 'Nil' as object as it is a single instance of it needed
 */
object Nil extends List[Nothing] {
  //'object' can not have parametrized type parameters
  //because only one instance of particular object may exist
  //'Nothing' is subType of every other type
  //so it is universal and can express anything else
  def isEmpty = true
  //Can not take a head of Nil 
  def head = throw new NoSuchElementException("Nil.head")
  //if type explicitly stated / mentioned
  //'Nothing' is subtype of any other type
  //so 'Nothing' is a 'T' - that is example of 'subtyping' in polymorphism 
  //mostly from OOP languages
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")

  //visual representation of tree node
  override def toString = {
    "Nil"
  }
}

object test {
  //because I am failed to import this class from week3 package
  //and it is not in the scope 
  abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    //exersise: implement
    def union(other: IntSet): IntSet
  }
  //*object Empty extends IntSet {
  class Empty extends IntSet {
    def contains(x: Int): Boolean = false
    //*def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)  
    def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
    def union(other: IntSet): IntSet = other
    override def toString = "."
  }
  class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean =
      if (x < elem)
        left contains x //<=> to: left.contains(x)
      else if (x > elem)
        right contains x
      else
        true
    def incl(x: Int): IntSet =
      if (x < elem)
        new NonEmpty(elem, left incl x, right)
      else if (x > elem)
        new NonEmpty(elem, left, right incl x)
      else
        this
    def union(other: IntSet): IntSet = {
      ((this.left.union(right)).union(other)).incl(elem)
    }
    override def toString = "{" + left + elem + right + "}"
  }
  //to see what type is will be returned
  //hover mouse cursor over the function name
  def f(xs: List[NonEmpty], x: Empty) = xs prepend x
  //*var x: List[String] = Nil
  //*val xs: List[IntSet] = xs.prepend(Empty)
  /* error: type mismatch
  val ys: List[NonEmpty] = ys.prepend(Empty)*/
  //val xs: List[Nat]
  //val xs: List[String] = xs.prepend(Empty)
  def main(args: Array[String]) = {
    val whatTypeIs =
      //println("Type of f is: " +           
      f(
        new Cons(new NonEmpty(1, new Empty, new Empty), Nil),
        new Empty)
    //.isInstanceOf[Any] //true
    //.isInstanceOf[IntSet] //false - must be true 
    //.isInstanceOf[Empty] //false
    //.isInstanceOf[NonEmpty] //false
    println("Type of f is: " + {
      whatTypeIs match {
        case _: Any => "Any"//true
        /*case _: List[IntSet] => "List[IntSet]"//true
        case _: Empty => "Empty"//true
        case _: NonEmpty => "NonEmpty"//true
        case _ => false
        case _: IntSet => "IntSet"//true*/
      }
    })
  }
}

//Generic functions
//that is example of 'Generics' (form of) in polymorphism
//mostly from functional languages 
object lists {
  //if 'Nil' is a class
  //def singleton[T](elem: T) = new Cons[T](elem, new Nil[T]) 
  //if 'Nil' is an object
  def singleton[T](elem: T) = new Cons[T](elem, Nil)

  singleton[Int](1)
  val s1 = singleton[Boolean](true)

  //or
  //compiler deduction infers right type from function parameter
  //as they have the same type both  
  val s2 = singleton(1)
  //in general type parameter do not store anywhere in the program
  //they have meaning for compiler
  //for type correctness validation

  //List(1, 2, 3)
  //if 'Nil' is a class
  //val l1 = new Cons(1, new Cons(2, new Cons(3, new Nil)))
  val l1 = new Cons(1, new Cons(2, new Cons(3, Nil)))
  //List(true, false)
  //if 'Nil' is a class
  //val l2 = new Cons(true, new Cons(false, new Nil))
  val l2 = new Cons(true, new Cons(false, Nil))
  //List(List(true, false), List(3))
  //in current trait definition T is invariant -- never changing
  //use +T instead of T ?
  //if 'Nil' is a class
  //val l3 = new Cons[Any](l2, new Cons(3, new Nil))
  val l3 = new Cons[Any](l2, new Cons(3, Nil))

  // TODO Write a function nth 
  /* that takes 
   * an integer 'n' and 
   * a list and 
   * selects the n'th element of the list.
   * Elements are numbered from 0.
   * If index is 
   * outside the range 
   * from (0 up to the length of the list minus one), 
   * a 'IndexOutOfBoundsException' should be thrown.
   */
  /**
   * 'n' as index - point to 'head' of list
   * nth return type is 'T'
   */
  def nth[T](n: Int, l: List[T]): T = {
    //short circuit to the most important condition
    //so actually full length of the initial list is unknown
    //only the properties of passing parameters matters 
    //functions runs until n !== 0 and
    //stops when either
    // 'n' became == 0 -- desired result or
    // l.tail is Nil -- exception
    if (l.isEmpty || n < 0) {
      //substituted / evaluated values are before exception, so it did no good 
      //throw new IndexOutOfBoundsException("Index " + n + " is outside the range of list " + l)
      throw new IndexOutOfBoundsException("Index is outside the range of list")
    } else if (n == 0) {
      //Executes when the Boolean expression 1 is true
      l.head //return value
      /*} else if(Boolean_expression 2){
       //Executes when the Boolean expression 2 is true
    } else if(Boolean_expression 3){
       //Executes when the Boolean expression 3 is true
        */
    } else {
      //Executes when the none of the above condition is true.
      //recursion / iteration
      nth(n - 1, l.tail)
    }
  }

  //expected exception: NoSuchElementException("Nil.tail")
  //nth(-1, l1)

  //standalone applications *.scala files
  /** Unit test */
  //def main(args: Array[String]): Unit = {
  //or
  def main(args: Array[String]) = {
    println("0 element of List(1, 2, 3) is: " + nth(0, l1))
    println("2 element of List(1, 2, 3) is: " + nth(2, l1))
    //println("5 element of List(1, 2, 3) is: " + nth(5, l1))
    println("List(1, 2, 3) is: " + l1)
    println("List(true, false) is: " + l2)
    println("List(List(true, false), List(3)) is: " + l3)
    println("singleton(true) is: " + s1)
    println("singleton(1) is: " + s2)
    //println("-3 element of List(1, 2, 3) is: " + nth(-3, l1))
  } //> main: (args: Array[String])Unit
}

object ListExe {
  // TODO Define an object List{...} with 
  /* 3 functions in it 
   * so that 
   * users can 
   * create lists of length 0-2 using syntax
   */
  //List() //the empty list
  //List(1) //the list with single element 1.
  //List(2, 3) // the list with elements 2 and 3
  /* if 'Nil' is a class
  def f(): List[Nothing] = new Nil
  def f1[T](elem: T): List[T] = new Cons(elem, new Nil)
  def f2[T](elem1: T, elem2: T): List[T] = new Cons(elem1, new Cons(elem2, new Nil))*/
  def f(): List[Nothing] = Nil
  def f1[T](elem: T): List[T] = new Cons(elem, Nil)
  def f2[T](elem1: T, elem2: T): List[T] = new Cons(elem1, new Cons(elem2, Nil))

  //standalone applications *.scala files
  /** Unit test */
  //def main(args: Array[String]): Unit = {
  //or
  def main(args: Array[String]) = {
    println("List() the empty list is: " + f)
    println("List(1) the list with single element 1 is: " + f1(1))
    println("List(2, 3) the list with elements 2 and 3 is: " + f2(2, 3))
  }
}

/**
 * Functions and Methods Implementation
 * by Martin Odersky
 */
object ListMO {
  /*if 'Nil' is a class
  def apply[T]() = new Nil
  def apply[T](x: T): List[T] = new Cons(x, new Nil)
  //List(2, 3) <=> List.apply(2, 3)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))*/
  def apply[T]() = Nil
  def apply[T](x: T): List[T] = new Cons(x, Nil)
  //List(2, 3) <=> List.apply(2, 3)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, Nil))

  def main(args: Array[String]) = {
    println("MO List() the empty list is: " + apply)
    println("MO List(1) the list with single element 1 is: " + apply(1))
    println("MO List(2, 3) the list with elements 2 and 3 is: " + apply(2, 3))
  }
}