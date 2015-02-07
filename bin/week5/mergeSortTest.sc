package week5

object mergeSortTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

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
    }                                             //> merge1: (xs: List[Int], ys: List[Int])List[Int]

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
    }                                             //> merge2: (xs: List[Int], ys: List[Int])List[Int]

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
  }                                               //> mSort: (xs: List[Int])List[Int]
  
  val unsortedNums = 7 :: 1 :: 2 :: 9 :: 4 :: 6 :: 5 :: 0 :: 3 :: Nil
                                                  //> unsortedNums  : List[Int] = List(7, 1, 2, 9, 4, 6, 5, 0, 3)
  val fst1 = 7 :: 1 :: 2 :: 9 :: 4 :: Nil         //> fst1  : List[Int] = List(7, 1, 2, 9, 4)
  val snd2 = 6 :: 5 :: 0 :: 3 :: Nil              //> snd2  : List[Int] = List(6, 5, 0, 3)
  
  unsortedNums splitAt 4                          //> res0: (List[Int], List[Int]) = (List(7, 1, 2, 9),List(4, 6, 5, 0, 3))
  '(' + (unsortedNums take 4).toString() +
  ',' + (unsortedNums drop 4).toString() + ')'    //> res1: String = (List(7, 1, 2, 9),List(4, 6, 5, 0, 3))
  ((unsortedNums take 4), (unsortedNums drop 4))  //> res2: (List[Int], List[Int]) = (List(7, 1, 2, 9),List(4, 6, 5, 0, 3))
  mSort(unsortedNums) //works with merge1 & 2     //> res3: List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 9)
  merge1(fst1, snd2)                              //> res4: List[Int] = List(6, 5, 0, 3, 7, 1, 2, 9, 4)
  merge2(fst1, snd2)                              //> res5: List[Int] = List(6, 5, 0, 3, 7, 1, 2, 9, 4)
}