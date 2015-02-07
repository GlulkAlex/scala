package week5

object mergeSortTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 
  println("Welcome to the Scala worksheet");$skip(306); 

  def merge1(xs: List[Int], ys: List[Int]): List[Int] =
    xs match {
      case Nil =>
        ys
      case x :: xs1 =>
        ys match {
          case Nil =>
            xs
          case y :: ys1 =>
            if (x < y) x :: merge1(xs1, ys)
            else y :: merge1(xs, ys1)
        }
    };System.out.println("""merge1: (xs: List[Int], ys: List[Int])List[Int]""");$skip(507); 

  /*The merge function as given uses
  a nested pattern match.
  This does not reflect
  the inherent symmetry of the merge algorithm.
  Rewrite merge using
  a pattern matching over pairs. */

  def merge2(xs: List[Int], ys: List[Int]): List[Int] =
    (xs, ys) match {
      case (Nil, y :: ys1) =>
        ys
      case (x :: xs1, Nil) =>
        xs
      case (x :: xs1, y :: ys1) =>
        if (x < y) {
          x :: merge2(xs1, ys)
        } else {
          y :: merge2(xs, ys1)
        }
    };System.out.println("""merge2: (xs: List[Int], ys: List[Int])List[Int]""");$skip(670); 

  /**
   * First MergeSort Implementation
   * Here is the implementation of that algorithm in Scala:
   */
  def mSort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2

    if (n == 0) {
      xs
    } else {
      //def merge(xs: List[Int], ys: List[Int]) = ???
      /* The SplitAt Function
			* The 'splitAt' function on lists returns
			* two sublists:
			* > the elements up the the given index
			* > the elements from that index
			* The lists are returned in a pair (Tuple2).*/
      val (fst, snd) = xs splitAt n //<=> ((xs take n), (xs drop n))

      //merge1(mSort(fst), mSort(snd))//*works
      merge2(mSort(fst), mSort(snd))//*works
    }
  };System.out.println("""mSort: (xs: List[Int])List[Int]""");$skip(74); 
  
  val unsortedNums = 7 :: 1 :: 2 :: 9 :: 4 :: 6 :: 5 :: 0 :: 3 :: Nil;System.out.println("""unsortedNums  : List[Int] = """ + $show(unsortedNums ));$skip(42); 
  val fst1 = 7 :: 1 :: 2 :: 9 :: 4 :: Nil;System.out.println("""fst1  : List[Int] = """ + $show(fst1 ));$skip(37); 
  val snd2 = 6 :: 5 :: 0 :: 3 :: Nil;System.out.println("""snd2  : List[Int] = """ + $show(snd2 ));$skip(29); val res$0 = 
  
  unsortedNums splitAt 4;System.out.println("""res0: (List[Int], List[Int]) = """ + $show(res$0));$skip(90); val res$1 = 
  '(' + (unsortedNums take 4).toString() +
  ',' + (unsortedNums drop 4).toString() + ')';System.out.println("""res1: String = """ + $show(res$1));$skip(49); val res$2 = 
  ((unsortedNums take 4), (unsortedNums drop 4));System.out.println("""res2: (List[Int], List[Int]) = """ + $show(res$2));$skip(46); val res$3 = 
  mSort(unsortedNums);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(21); val res$4 =  //works with merge1 & 2
  merge1(fst1, snd2);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(21); val res$5 = 
  merge2(fst1, snd2);System.out.println("""res5: List[Int] = """ + $show(res$5))}
}
