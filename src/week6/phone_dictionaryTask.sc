package week6
import scala.io.Source

object phone_dictionaryTask {
  /*Task
  Phone keys have 'mnemonics' assigned to them.
  
  val mnemonics = Map(
  '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
  '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")
  
  Assume
  you are given a dictionary 'words' as a list of words.
  Design a method 'translate' such that
  'translate(phoneNumber)'
  produces
  all phrases of words that
  can serve as
  'mnemonics' for
  the phone number.
  
  Example:
  The phone number "7225247386" should have
  the mnemonic 'Scala is fun' as
  one element of
  the set of solution phrases.*/

  /*from: http://lamp.epfl.ch/
  PROGRAMMING METHODS LABORATORY LAMP
  or
  http://lamp.epfl.ch/page-69099-en.html */
  val url = "http://lamp.epfl.ch/" +
    "files/content/sites/lamp/files/teaching/progfun/" +
    "linuxwords.txt"                              //> url  : String = http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/
                                                  //| progfun/linuxwords.txt
  val dataFilePath = "e:\\Java\\coursera-workspace\\progfun\\"
                                                  //> dataFilePath  : String = e:\Java\coursera-workspace\progfun\
  val dataFileName = "linuxwords.txt"             //> dataFileName  : String = linuxwords.txt
  val in = Source
    .fromFile( dataFilePath + dataFileName )      //> in  : scala.io.BufferedSource = non-empty iterator
  //*.fromURL( url )
  /*Note:
  dictionary contains words with '-'
  like 'Bhagavad-Gita', so
  drop them &
  with another non-letter characters*/
  val words = in
    .getLines
    .toList filter ( word => word forall ( char => char.isLetter ) )
                                                  //> words  : List[String] = List(Aarhus, Aaron, Ababa, aback, abaft, abandon, a
                                                  //| bandoned, abandoning, abandonment, abandons, abase, abased, abasement, abas
                                                  //| ements, abases, abash, abashed, abashes, abashing, abasing, abate, abated, 
                                                  //| abatement, abatements, abater, abates, abating, Abba, abbe, abbey, abbeys, 
                                                  //| abbot, abbots, Abbott, abbreviate, abbreviated, abbreviates, abbreviating, 
                                                  //| abbreviation, abbreviations, Abby, abdomen, abdomens, abdominal, abduct, ab
                                                  //| ducted, abduction, abductions, abductor, abductors, abducts, Abe, abed, Abe
                                                  //| l, Abelian, Abelson, Aberdeen, Abernathy, aberrant, aberration, aberrations
                                                  //| , abet, abets, abetted, abetter, abetting, abeyance, abhor, abhorred, abhor
                                                  //| rent, abhorrer, abhorring, abhors, abide, abided, abides, abiding, Abidjan,
                                                  //|  Abigail, Abilene, abilities, ability, abject, abjection, abjections, abjec
                                                  //| tly, abjectness, abjure, abjured, abjures, abjuring, ablate, ablated, ablat
                                                  //| es, ablating, ablation,
                                                  //| Output exceeds cutoff limit.
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI",
    '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS",
    '8' -> "TUV", '9' -> "WXYZ" )                 //> mnem  : scala.collection.immutable.Map[Char,String] = Map(8 -> TUV, 4 -> GH
                                                  //| I, 9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PQRS, 3 -> DEF)

  /** Invert the 'mnem' map to
    * give a map from chars 'A' ... 'Z' to '2' ... '9'
    */
  /*follow DRY: use / process 'mnem' as yet existing data &
  ScalaStyleGuide: #7 Don't Copy-Paste Code!
  ? and what if it is a snippet ?*/
  val charCode: Map[ Char, Char ] = for {
    ( digit, str ) <- mnem
    ltr <- str
  } yield ltr -> digit                            //> charCode  : Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J 
                                                  //| -> 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 
                                                  //| 5, B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, 
                                                  //| Z -> 9, S -> 7)
  /** Maps a word to the digit string it can represent,
    * e.g. "Java" -> "5282"
    *//*time consuming operation*/
  def wordCode( word: String ): String =
    /*using 'charCode' as a function for map method*/
    word.toUpperCase() map charCode               //> wordCode: (word: String)String

  /** A 'map' from digit strings to
    * the words (from given dictionary) that represent them,
    * e,g. "5282" -> List("Java", "Kata", "Lava", ...)
    * Note:
    * A missing number should
    * map to the empty set,
    * e.g. "1111" -> List()
    */
  val wordsForNum: Map[ String, Seq[ String ] ] =
    /*Note:
	  Iterator[String] do not have '.groupBy' method
	  ? as it is Java inhereted object ? */
    /*Making finctions 'total' from 'partial'*/
    words groupBy wordCode withDefaultValue Seq() //> wordsForNum  : Map[String,Seq[String]] = Map(63972278 -> List(newscast), 29
                                                  //| 237638427 -> List(cybernetics), 782754448 -> List(starlight), 2559464 -> Li
                                                  //| st(allying), 862532733 -> List(uncleared), 365692259 -> List(enjoyably), 86
                                                  //| 8437 -> List(unties), 33767833 -> List(deportee), 742533 -> List(picked), 3
                                                  //| 364646489 -> List(femininity), 3987267346279 -> List(extraordinary), 785539
                                                  //| 7 -> List(pulleys), 67846493 -> List(optimize), 4723837 -> List(grafter), 3
                                                  //| 86583 -> List(evolve), 78475464 -> List(Stirling), 746459 -> List(singly), 
                                                  //| 847827 -> List(vistas), 546637737 -> List(lionesses), 28754283 -> List(curl
                                                  //| icue), 84863372658 -> List(thunderbolt), 46767833 -> List(imported), 264374
                                                  //| 64 -> List(angering, cohering), 8872267 -> List(turbans), 77665377 -> List(
                                                  //| spoolers), 46636233 -> List(homemade), 7446768759 -> List(rigorously), 7464
                                                  //| 4647 -> List(ringings), 633738 -> List(offset), 847825 -> List(visual), 772
                                                  //| 832 -> List(Pravda), 47
                                                  //| Output exceeds cutoff limit.

  /** Return all ways to encode a number as
    * a list of words
    */
  def encode( number: String ): Set[ List[ String ] ] =
    /*firstly,
  consider(ing) edge / boundary cases*/
    if ( number.isEmpty() ) {
      Set( List() )
    } else {
      /*amout of characters to form new word*/
      for {
        /*have IndexedSeq[List[String]] but Set[ List[ String ] ] requred*/
        split <- 1 to number.length()
        /*? in dictionary not exist word from '7' -> "PQRS" letters only ?*/
        word <- wordsForNum( number take split )
        /*reccursion */
        rest <- encode( number drop split )
      } yield word :: rest /*first word followed by the rest*/
    }.toSet                                       //> encode: (number: String)Set[List[String]]

  def translate( number: String ): Set[ String ] =
    encode( number ) map ( _ mkString " " )       //> translate: (number: String)Set[String]

  wordCode( "JAVA" )                              //> res0: String = 5282
  wordCode( "Java" )                              //> res1: String = 5282
  //*encode( "7225247386" )
  translate( "7225247386" )                       //> res2: Set[String] = Set(sack air fun, pack ah re to, pack bird to, Scala ir
                                                  //| e to, Scala is fun, rack ah re to, pack air fun, sack bird to, rack bird to
                                                  //| , sack ah re to, rack air fun)
  /*? '0, 1' has no letters, so return empty set
  Set() ?*/
  translate( "689" )/*U -> 8, X -> 9, N -> 6 => NUX & XUN ? google approves*/
                                                  //> res3: Set[String] = Set()
  translate( "912" )                              //> res4: Set[String] = Set()
  translate( "2011" )                             //> res5: Set[String] = Set()
  translate( "364316" )                           //> res6: Set[String] = Set()
  translate( "316" )                              //> res7: Set[String] = Set()
  translate( "364" )                              //> res8: Set[String] = Set(dog, Eng, fog)
  translate( "5282" )                             //> res9: Set[String] = Set(Java, lava)
}