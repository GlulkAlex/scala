package week5

object equationalProofForListsTest {
  //*println("Welcome to the Scala worksheet")
  /**
   * Law of reverse:
   * Nil.reverse = Nil // 1st clause
   * (x :: xs).reverse = xs.reverse ++ List(x) // 2nd clause
   * this properties / features connected to: foldLeft
   */
  /*
  !even a comment placment matters!
  make place to right for evaluation results
  keep comments in separate with expressions strings
  */
  Nil.reverse.reverse                             //> res0: List[Nothing] = List()
  "x" :: List("xs")                               //> res1: List[String] = List(x, xs)
  ("x" :: List("xs")).reverse.reverse             //> res2: List[String] = List(x, xs)
  (List("xs").reverse ++ List("x")).reverse       //> res3: List[String] = List(x, xs)
  // to show: = x :: Nil.reverse
  (Nil ++ List("x")).reverse                      //> res4: List[String] = List(x)

  /*Exercice:
  distribution law for 'map' over 'concatination'
  (xs ++ ys) map f = (xs map f) ++ (ys map f)
  
  Nil map f = Nil //base case
  (x :: xs) map f = f(x) :: (xs map f) //inductive case
  */
  /*
  Prove
  the following distribution law for map over concatenation.
  For any lists xs, ys, function f:
  ( xs ++ ys) map f  =  (xs map f) ++ (ys map f)
  You will need
  the clauses of '++' as well as
  the following clauses for map:
        Nil map f  =  Nil
  (x :: xs) map f  =  f(x) :: (xs map f)
  */
}