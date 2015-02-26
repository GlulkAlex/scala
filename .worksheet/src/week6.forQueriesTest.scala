package week6

object forQueriesTest {
  //println("Welcome to the Scala worksheet")
  case class Book(title: String, authors: List[String]);import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(832); 

  val books: List[Book] = List(
    /*Using: named fields parameters*/
    Book(title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harald", "Sussman, Gerald J.")),
    Book(title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java",
      authors = List("Bloch, Joshua")),
    Book(title = "3rd book of / by Bloch, Joshua",
      authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")),
    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")));System.out.println("""books  : List[week6.forQueriesTest.Book] = """ + $show(books ));$skip(157); val res$0 = 

  /*To find the 'titlae' of books whose author's name is "Bird":
  */
  for {
    b <- books;
    a <- b.authors if a startsWith "Bird,"
  } yield b.title;System.out.println("""res0: List[String] = """ + $show(res$0));$skip(153); val res$1 = 

  /*
  To find all the books which have the word "Program" in the title:
  */
  for (b <- books if (b.title indexOf "Program") >= 0)
    yield b.title;System.out.println("""res1: List[String] = """ + $show(res$1));$skip(411); val res$2 = 
  /*To find
  the names of all authors who
  have written at least
  two books
  present in the database.*/
  for {
    b1 <- books
    b2 <- books
    if b1 != b2
    /*Gains:
    "Effective Java", "3rd book of / by Bloch, Joshua", "Java Puzzlers"
    and its reverse
    */
    //if b1 < b2 //'<' not defined for 'Book' class
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield (b1.title, a1);System.out.println("""res2: List[(String, String)] = """ + $show(res$2));$skip(240); val res$3 = 

  /*Modi?ed Query*/
  for {
    b1 <- books
    b2 <- books
    /*lecsikagraphicaly smaller then*/
    if b1.title < b2.title
    //if b1.title != b2.title //not work
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1;System.out.println("""res3: List[String] = """ + $show(res$3));$skip(321); val res$4 = 

  /*Solution:
  We must
  remove duplicate authors who are
  in the results list twice.
  This is achieved using
  the distinct method on sequences:*/
  (
    for {
      b1 <- books
      b2 <- books
      if b1.title < b2.title
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1).distinct;System.out.println("""res4: List[String] = """ + $show(res$4));$skip(168); 

  /*Better alternative:
  Useing appropriate / relevant data structure
  Compute with sets (only unique values)
  instead of sequences:*/
  val bookSet = books.toSet;System.out.println("""bookSet  : scala.collection.immutable.Set[week6.forQueriesTest.Book] = """ + $show(bookSet ));$skip(13); val res$5 = 
  books.size;System.out.println("""res5: Int = """ + $show(res$5));$skip(15); val res$6 = 
  bookSet.size;System.out.println("""res6: Int = """ + $show(res$6));$skip(131); val res$7 = 
  for {
    b1 <- bookSet
    b2 <- bookSet
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1;System.out.println("""res7: scala.collection.immutable.Set[String] = """ + $show(res$7))}

}
