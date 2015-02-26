package week6

object forQueriesTest {
  //println("Welcome to the Scala worksheet")
  case class Book(title: String, authors: List[String])

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
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")))
                                                  //> books  : List[week6.forQueriesTest.Book] = List(Book(Structure and Interpret
                                                  //| ation of Computer Programs,List(Abelson, Harald, Sussman, Gerald J.)), Book(
                                                  //| Introduction to Functional Programming,List(Bird, Richard, Wadler, Phil)), B
                                                  //| ook(Effective Java,List(Bloch, Joshua)), Book(3rd book of / by Bloch, Joshua
                                                  //| ,List(Bloch, Joshua)), Book(Java Puzzlers,List(Bloch, Joshua, Gafter, Neal))
                                                  //| , Book(Programming in Scala,List(Odersky, Martin, Spoon, Lex, Venners, Bill)
                                                  //| ))

  /*To find the 'titlae' of books whose author's name is "Bird":
  */
  for {
    b <- books;
    a <- b.authors if a startsWith "Bird,"
  } yield b.title                                 //> res0: List[String] = List(Introduction to Functional Programming)

  /*
  To find all the books which have the word "Program" in the title:
  */
  for (b <- books if (b.title indexOf "Program") >= 0)
    yield b.title                                 //> res1: List[String] = List(Structure and Interpretation of Computer Programs
                                                  //| , Introduction to Functional Programming, Programming in Scala)
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
  } yield (b1.title, a1)                          //> res2: List[(String, String)] = List((Effective Java,Bloch, Joshua), (Effect
                                                  //| ive Java,Bloch, Joshua), (3rd book of / by Bloch, Joshua,Bloch, Joshua), (3
                                                  //| rd book of / by Bloch, Joshua,Bloch, Joshua), (Java Puzzlers,Bloch, Joshua)
                                                  //| , (Java Puzzlers,Bloch, Joshua))

  /*Modiﬁed Query*/
  for {
    b1 <- books
    b2 <- books
    /*lecsikagraphicaly smaller then*/
    if b1.title < b2.title
    //if b1.title != b2.title //not work
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res3: List[String] = List(Bloch, Joshua, Bloch, Joshua, Bloch, Joshua)

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
    } yield a1).distinct                          //> res4: List[String] = List(Bloch, Joshua)

  /*Better alternative:
  Useing appropriate / relevant data structure
  Compute with sets (only unique values)
  instead of sequences:*/
  val bookSet = books.toSet                       //> bookSet  : scala.collection.immutable.Set[week6.forQueriesTest.Book] = Set(
                                                  //| Book(3rd book of / by Bloch, Joshua,List(Bloch, Joshua)), Book(Programming 
                                                  //| in Scala,List(Odersky, Martin, Spoon, Lex, Venners, Bill)), Book(Structure 
                                                  //| and Interpretation of Computer Programs,List(Abelson, Harald, Sussman, Gera
                                                  //| ld J.)), Book(Effective Java,List(Bloch, Joshua)), Book(Introduction to Fun
                                                  //| ctional Programming,List(Bird, Richard, Wadler, Phil)), Book(Java Puzzlers,
                                                  //| List(Bloch, Joshua, Gafter, Neal)))
  books.size                                      //> res5: Int = 6
  bookSet.size                                    //> res6: Int = 6
  for {
    b1 <- bookSet
    b2 <- bookSet
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1                                      //> res7: scala.collection.immutable.Set[String] = Set(Bloch, Joshua)

}