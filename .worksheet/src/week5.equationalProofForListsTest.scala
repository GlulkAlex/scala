package week5

object equationalProofForListsTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(451); val res$0 = 
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
  Nil.reverse.reverse;System.out.println("""res0: List[Nothing] = """ + $show(res$0));$skip(20); val res$1 = 
  "x" :: List("xs");System.out.println("""res1: List[String] = """ + $show(res$1));$skip(38); val res$2 = 
  ("x" :: List("xs")).reverse.reverse;System.out.println("""res2: List[String] = """ + $show(res$2));$skip(44); val res$3 = 
  (List("xs").reverse ++ List("x")).reverse;System.out.println("""res3: List[String] = """ + $show(res$3));$skip(62); val res$4 = 
  // to show: = x :: Nil.reverse
  (Nil ++ List("x")).reverse;System.out.println("""res4: List[String] = """ + $show(res$4))}

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
