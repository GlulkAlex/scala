package week5

object encodeTest {
  def encode[T](xs: List[T], elemCounter: Int = 1): List[List[Tuple2[T, Int]]] = {
    def counter(elemCounter: Int): Int = elemCounter// + 1

    xs match {
      case Nil => {
        println("'xs' is: empty list")
        Nil //empty list}
      }
      case x :: xs1 => {
        xs1 match {
          case Nil => {
            counter(1)
            println("'xs1' is: empty list")
            List((x, 1) :: Nil)}
          case y :: ys1 =>
            if (x == y) {
              counter(elemCounter + 1)
              println(x + " = " + y + ", elemCounter = " + elemCounter)
              List((y, (elemCounter + 1)) :: Nil) ++
                encode(ys1, (elemCounter + 1))
            } else {
              counter(1)
              println(x + " !== " + y + ", elemCounter = " + elemCounter)
              return List((y, 1) :: Nil) ++ encode(ys1)
            }

        }
      }
    }
  }
  /*test outout*/
  def main(args: Array[String]) {
    val consecList = List("a", "a", "a", "a", "b", "c", "c", "c", "d", "a", "a")
    
    println("encode(consecList) gain: " + encode(consecList));
    println("Expected: List(('a', 4), ('b', 1), ('c', 2), ('d', 1)), ('a', 2))")
  }
}