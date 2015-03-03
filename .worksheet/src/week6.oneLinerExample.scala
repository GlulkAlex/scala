package week6
//from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object oneLinerExample {
  case class Participant( name: String, score: Int, active: Boolean );import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(302); 

  val ps = Seq( Participant( "Jack", 34, true ), Participant( "Tom", 51, true ),
    Participant( "Bob", 90, false ) );System.out.println("""ps  : Seq[week6.oneLinerExample.Participant] = """ + $show(ps ));$skip(355); val res$0 = 
  /*When you create a 'case class' in Scala,
  a 'copy' method is generated for your 'case class'.
  it lets you
  make a copy of an object, where
  a "copy" is different than a clone, because
  with a 'copy' you can
  change fields as desired
  during the copying process.*/
  ps.filter( _.score < 50 ).filter( _.active ).map( _.copy( active = false ) );System.out.println("""res0: Seq[week6.oneLinerExample.Participant] = """ + $show(res$0));$skip(423); val res$1 = 
  /*If a 'one-liner' becomes too dense,
	you can always
	break it down with
	a technique called 'for comprehensions'.
	The above example could be rewritten as
	this equivalent statement:*/
  for {
    loser <- ps if loser.score < 50
    /*? 'Option' in pattern matching ?*/
    activeLoser <- Some( loser ) if activeLoser.active
    deactivatedLoser <- Some( activeLoser.copy( active = false ) )
  } yield deactivatedLoser;System.out.println("""res1: Seq[week6.oneLinerExample.Participant] = """ + $show(res$1))}
  /*This is
	much more verbose than
	a 'one-liner' but
	in cases when
	logic gets dense,
	this can really help code readability, yet
	keep all the benefits of
	immutability and function chaining.*/
}
