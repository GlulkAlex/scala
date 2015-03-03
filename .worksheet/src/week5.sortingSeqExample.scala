package week5

object sortingSeqExample {
  case class User( id: Int, firstname: String, lastname: String, active: Boolean );import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(233); 

  def activeById( us: Seq[ User ] ) = us
    .filter( _.active )
    .sortBy( _.id )
    .map( _.lastname );System.out.println("""activeById: (us: Seq[week5.sortingSeqExample.User])Seq[String]""");$skip(166); 

  val activeUsersById = activeById( Seq(
    User( 11, "Nick", "Smith", false ),
    User( 89, "Ken", "Pratt", true ),
    User( 23, "Jack", "Sparrow", true )
  ) );System.out.println("""activeUsersById  : Seq[String] = """ + $show(activeUsersById ))}
}
