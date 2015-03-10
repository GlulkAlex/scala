package week6

object viewBoundsTest {
  /*'implicit' converter for some types*/
  implicit def strToInt( x: String ) = x.toInt    //> strToInt: (x: String)Int
  
  /*'[A <% Int]' is a 'view bound' that
  either asks for an 'Int' or
  something that
  can be viewed as an 'Int'.
  In our case
  it's a 'String' that can be
  implicitly converted to an 'Int'.*/
  def halfIt[A <% Int](x: A) = x / 2              //> halfIt: [A](x: A)(implicit evidence$1: A => Int)Int
  
  /*Here
	Scala will search for
	an 'implicit' conversion
	from 'String' to 'Int'.
	After finding 'strToInt',
	based on its signature,
	it will apply this conversion to
	all 'Strings' that are passed to 'math.min'
	without you explicitly invoking 'strToInt'.
	If you didn't define an 'implicit' conversion
	the compiler would throw an exception.*/
  math.min( "10", 1 )                             //> res0: Int = 1
  math.max( "10", 1 )                             //> res1: Int = 10
  
  halfIt(20)                                      //> res2: Int = 10
  halfIt("20")                                    //> res3: Int = 10
}