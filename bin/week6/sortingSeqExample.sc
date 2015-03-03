package week6
//from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object sortingSeqExample {
  case class User( id: Int, firstname: String, lastname: String, active: Boolean )

  def activeById( us: Seq[ User ] ) = us
    .filter( _.active )
    .sortBy( _.id )
    .map( _.lastname )                            //> activeById: (us: Seq[week6.sortingSeqExample.User])Seq[String]

  val activeUsersById = activeById( Seq(
    User( 11, "Nick", "Smith", false ),
    User( 89, "Ken", "Pratt", true ),
    User( 23, "Jack", "Sparrow", true )
  ) )                                             //> activeUsersById  : Seq[String] = List(Sparrow, Pratt)
  
  //activeUsersById.toList
}