package week6
//from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object oneLinerExample {
  case class Participant( name: String, score: Int, active: Boolean )

  val ps = Seq( Participant( "Jack", 34, true ), Participant( "Tom", 51, true ),
    Participant( "Bob", 90, false ) )             //> ps  : Seq[week6.oneLinerExample.Participant] = List(Participant(Jack,34,true
                                                  //| ), Participant(Tom,51,true), Participant(Bob,90,false))
  /*When you create a 'case class' in Scala,
  a 'copy' method is generated for your 'case class'.
  it lets you
  make a copy of an object, where
  a "copy" is different than a clone, because
  with a 'copy' you can
  change fields as desired
  during the copying process.*/
  ps.filter( _.score < 50 ).filter( _.active ).map( _.copy( active = false ) )
                                                  //> res0: Seq[week6.oneLinerExample.Participant] = List(Participant(Jack,34,fals
                                                  //| e))
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
  } yield deactivatedLoser                        //> res1: Seq[week6.oneLinerExample.Participant] = List(Participant(Jack,34,fal
                                                  //| se))
  /*This is
	much more verbose than
	a 'one-liner' but
	in cases when
	logic gets dense,
	this can really help code readability, yet
	keep all the benefits of
	immutability and function chaining.*/
}