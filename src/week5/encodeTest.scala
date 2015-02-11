package week5

object encodeTest {
  /* to go @tail-recursive
   * must be:
   * 1) base case - final return value
   * 2) iteration - increment step / recursion
   * to preserve result in / from previous iterations
   * must be - accumulator / overall store parameter in the iterator
   */
  def encode[T](xs: List[T] //, 
  /*elemCounter: Int = 1*/ ): List[List[ /*Tuple2[T, Int]*/ (T, Int)]] = {
    def iterator(accumulator: List[List[(T, Int)]] = Nil,
      nextIteration: List[T]): List[List[(T, Int)]] = {
      /* use as 'pattern' for condition accumulator.tail
       * as I can not find a way to pass empty / undefined value
       * as parameter  
       * pattern: T = 
       * scala.Nothing no companion object exist,
       *       there exist no instances of this type
       *       and no constructor to create one
       */
      /*
       * '()' - stands for unit
       * 
       * def isEmpty: Boolean  
       * def exists(p: (A) => Boolean): Boolean
       * def isDefined: Boolean
       * def orNull
       * 
       * Instance Constructors:
       * new Some(x: A)*/
      nextIteration match {
        //1. basic case
        case Nil => {
          //*println("'xs' is: empty list")
          //final value
          accumulator //empty list
        }
        //2. iterative step
        case x :: xs1 => {
          accumulator match {
            //first step or .isEmpty & Nil
            case Nil => {
              // To debug: 
              //*Console.print(xs1)
              //*Console.err.println("Debug messages...")

              // %[argument_index$][flags][width][.precision]conversion
              // for arguments ('xs') placed in / within text
              //*printf("printf text: %1$3s %1$13S", xs)
              //*System.err.println("System.err msg test.")
              //*println("'xs1' is: empty list")//standart sys out
              //accumulator //final value

              //counter starts from 1 - first occurrence of element 
              iterator(accumulator = ((x, 1) :: Nil) :: Nil,
                nextIteration = xs1)
            }
            case y :: ys1 => {
              val (value, couter) = accumulator.last.last

              if (x == value) {
                //element count + 1
                //println(x + " = " + y + ", elemCounter = " + elemCounter)
                /*must 'drop' accumulator.last.last &
                append / postfix tail with new counter = counter + 1 */
                //Returns all elements except last n ones.  
                iterator(accumulator.dropRight(1) ::: ((x, couter + 1) :: Nil) :: Nil,
                  nextIteration = xs1)
              } else {
                //println(x + " !== " + y + ", elemCounter = " + elemCounter)
                //counter starts from 1 - first occurrence of element in sequence
                return iterator(accumulator = accumulator ::: ((x, 1) :: Nil) :: Nil,
                  nextIteration = xs1)
              }
            }
          }
        }
      }
    }

    iterator(nextIteration = xs) //initialization
  } //work as expected

  def pack[T](xs: List[T]): List[List[T]] = {
    xs match {
      case Nil => Nil
      case x :: xs1 => {
        val (first, rest) = xs span (y => y == x)
        first :: pack(rest)
      }
    }
  }

  def packEncode[T](xs: List[T]): List[(T, Int)] = {
    //val packed = pack(xs) //List(List(a, a, a, a), List(b), List(c, c, c))
    //val counterF = (c: Int) => c + 1
    //list length 
    //def countElements(counter: Int = 0): Int = counter + 1

    /** length of list */  
    def loop[T](yS: List[T], counter: Int = 0): Int = {
      /*yS match {
        case Nil => counter
        case z :: zS => {
          loop(zS, counter + 1)
        }
      }*/
      if (yS.isEmpty) {
        counter
      } else {
        loop(yS.tail, counter + 1)
      }
    }

    def firstElement[T](yS2: List[T]): T = {
      if (yS2.isEmpty) {
        Console.err.println("no head of [T]")
        //may be it is possible to use it as 'empty' value 
        None.asInstanceOf[T]
        //Nothing.asInstanceOf[T]
      } else {
        yS2.head
      }
    }

    def iterator[T](yS1: List[List[T]], pairStoreL: List[(T, Int)] = Nil): List[(T, Int)] = {
      if (yS1.isEmpty) {
        pairStoreL
      } else {
        iterator(yS1.tail, pairStoreL ::: (firstElement(yS1.head), loop(yS1.head)) :: Nil)
      }
    }

    iterator(pack(xs)) //initialization 
  }//work as expected

  /*test output*/
  def main(args: Array[String]) {
    val consecList = List("a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a")

    println("encode(consecList) gain: ");
    println(encode(consecList))    
    println("Expected: List(('a', 4), ('b', 1), ('c', 3), ('d', 1)), ('a', 2))")
    println(packEncode(consecList))
    println(packEncode(Nil))
  }
}