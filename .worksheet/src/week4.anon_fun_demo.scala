//?package - may be warper (with '{}')
package week4 {

  object anon_fun_demo {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(127); 
    println("Welcome to the Scala worksheet");$skip(86); 

    //?return type deduced from
    //?function body
    val f1 = (x: Int) => x * x;System.out.println("""f1  : Int => Int = """ + $show(f1 ));$skip(10); val res$0 = 
    f1(3);System.out.println("""res0: Int = """ + $show(res$0));$skip(185); 

    //?<function1> generic type
    //?arg(0) for input parameter type
    //?arg(1) for return value type
    val f = new Function1[Int, Int] {
      def apply(x: Int) = x * x
    };System.out.println("""f  : Int => Int = """ + $show(f ));$skip(15); val res$1 = 
    f.apply(7);System.out.println("""res1: Int = """ + $show(res$1));$skip(196); 

    val f2 = {
      class AnonFun extends Function1[Int, Int] {
        def apply(x: Int) = x * x
      }
      //?return (last expression)
      //?instance of AnonFun
      new AnonFun
    };System.out.println("""f2  : Int => Int = """ + $show(f2 ));$skip(10); val res$2 = 
    f2(5);System.out.println("""res2: Int = """ + $show(res$2))}
  }
}
