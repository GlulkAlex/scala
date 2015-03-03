package week6
//from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object typeInferenceExample {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(522); 
  // You always need type annotations in the signature of the method:
  def nameStartsWith( ns: Seq[ String ], n: String ): Seq[ String ] =
    // Scala can't infer types for "generic" collections, so
    // you can't just say `Seq.empty`:
    ns.foldLeft( Seq.empty[ String ] ) {
      // But it doesn't need them in this anonymous function:
      ( l, r ) => if ( r.startsWith( n ) ) r +: l else l
    };System.out.println("""nameStartsWith: (ns: Seq[String], n: String)Seq[String]""");$skip(106); 

  // Type inference works really well with list declarations:
  val names = Seq( "Bob", "Alice", "Ann" );System.out.println("""names  : Seq[String] = """ + $show(names ));$skip(23); val res$0 = 

  Seq.empty[ String ];System.out.println("""res0: Seq[String] = """ + $show(res$0));$skip(61); val res$1 = 
  nameStartsWith( names, "A" ) /* returns List(Ann, Alice)*/;System.out.println("""res1: Seq[String] = """ + $show(res$1))}
}
