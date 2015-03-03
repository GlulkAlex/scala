package week6
//from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object optionUseExample {
  case class Project( id: String,
                      name: String,
                      priority: Int,
                      description: Option[ String ] )

  /*Here
  we are trying to
  retrieve a 'project' record from
  the database but
  we don’t know if
  the 'project' with a specific ID exists.
  Instead of
  returning 'null' or
  throwing an 'exception',
  we are either going to
  return 'Some[Project]' or 'None'
  as defined by
  the 'Option[Project]' return type of the 'find' method.*/
  object ProjectsAccessor {
    def find( id: String ): Option[ Project ] = ???
  };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(987); 

  /*You can also
	match your result directly
	based on
	the actual structure of the object:*/
  def descriptionWrapper( p: Project ) = p match {
    case Project( _, _, _, None )       => "No description."
    case Project( id, _, _, Some( d ) ) => s"Project $id's description: $d"
  };System.out.println("""descriptionWrapper: (p: week6.optionUseExample.Project)String""");$skip(48); 

  val project = ProjectsAccessor.find( "123" );System.out.println("""project  : Option[week6.optionUseExample.Project] = """ + $show(project ));$skip(422); val res$0 = 

  /*Basically,
  we are matching
  the result of 'find' to see if
  the 'project' exists.
  If it exists then
  we return its name, otherwise
  we return an empty string.
  At first,
  it might look like a switch-case structure from Java but
  it’s actually very different.
  With pattern matching you can
  add custom logic to your patterns:*/
  project match {
    case Some( p ) => p.name
    case None      => ""
  };System.out.println("""res0: String = """ + $show(res$0));$skip(265); val res$1 = 

  project match {
    case Some( p ) if 1 until 5 contains p.priority => p.name
    case Some( p ) if p.name == "Default Project"   => p.name
    case Some( p )                                  => None
    case None                                       => ""
  };System.out.println("""res1: java.io.Serializable = """ + $show(res$1))}
}
