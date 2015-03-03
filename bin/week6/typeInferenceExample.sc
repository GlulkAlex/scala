package week6
//from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object typeInferenceExample {
  // You always need type annotations in the signature of the method:
  def nameStartsWith( ns: Seq[ String ], n: String ): Seq[ String ] =
    // Scala can't infer types for "generic" collections, so
    // you can't just say `Seq.empty`:
    ns.foldLeft( Seq.empty[ String ] ) {
      // But it doesn't need them in this anonymous function:
      ( l, r ) => if ( r.startsWith( n ) ) r +: l else l
    }                                             //> nameStartsWith: (ns: Seq[String], n: String)Seq[String]

  // Type inference works really well with list declarations:
  val names = Seq( "Bob", "Alice", "Ann" )        //> names  : Seq[String] = List(Bob, Alice, Ann)

  Seq.empty[ String ]                             //> res0: Seq[String] = List()
  nameStartsWith( names, "A" ) /* returns List(Ann, Alice)*/
                                                  //> res1: Seq[String] = List(Ann, Alice)
}