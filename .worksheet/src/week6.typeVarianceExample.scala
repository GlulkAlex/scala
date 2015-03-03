package week6
// from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object typeVarianceExample {
  case class InvariantContainer[ A ]()
  case class CovariantContainer[ +A ]()
  case class ContravariantContainer[ -A ]()

  class Person
  class User extends Person
  class Admin extends User;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(388); 

  val inv1: InvariantContainer[ User ] = InvariantContainer[ User ];System.out.println("""inv1  : week6.typeVarianceExample.InvariantContainer[week6.typeVarianceExample.User] = """ + $show(inv1 ));$skip(166);  // works

  //val inv2: InvariantContainer[ User ] = InvariantContainer[ Admin ] // doesn't work

  val cov1: CovariantContainer[ User ] = CovariantContainer[ User ];System.out.println("""cov1  : week6.typeVarianceExample.CovariantContainer[week6.typeVarianceExample.User] = """ + $show(cov1 ));$skip(79);  // works

  val cov2: CovariantContainer[ User ] = CovariantContainer[ Admin ];System.out.println("""cov2  : week6.typeVarianceExample.CovariantContainer[week6.typeVarianceExample.User] = """ + $show(cov2 ));$skip(86);  // works

  val con1: ContravariantContainer[ User ] = ContravariantContainer[ User ];System.out.println("""con1  : week6.typeVarianceExample.ContravariantContainer[week6.typeVarianceExample.User] = """ + $show(con1 ));$skip(184);  // works

  //val con2: ContravariantContainer[ User ] = ContravariantContainer[ Admin ] // doesn't work

  val con3: ContravariantContainer[ User ] = ContravariantContainer[ Person ] // works;System.out.println("""con3  : week6.typeVarianceExample.ContravariantContainer[week6.typeVarianceExample.User] = """ + $show(con3 ))}
}
