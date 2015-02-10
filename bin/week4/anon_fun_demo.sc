//?package - may be warper (with '{}')
package week4 {

  object anon_fun_demo {
    println("Welcome to the Scala worksheet")     //> Welcome to the Scala worksheet

    //?return type deduced from
    //?function body
    val f1 = (x: Int) => x * x                    //> f1  : Int => Int = <function1>
    f1(3)                                         //> res0: Int = 9

    //?<function1> generic type
    //?arg(0) for input parameter type
    //?arg(1) for return value type
    val f = new Function1[Int, Int] {
      def apply(x: Int) = x * x
    }                                             //> f  : Int => Int = <function1>
    f.apply(7)                                    //> res1: Int = 49

    val f2 = {
      class AnonFun extends Function1[Int, Int] {
        def apply(x: Int) = x * x
      }
      //?return (last expression)
      //?instance of AnonFun
      new AnonFun
    }                                             //> f2  : Int => Int = <function1>
    f2(5)                                         //> res2: Int = 25
  }
}