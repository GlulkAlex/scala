package week6
// from: http://www.vasinov.com/blog/16-months-of-functional-programming/
object typeVarianceExample {
  case class InvariantContainer[ A ]()
  case class CovariantContainer[ +A ]()
  case class ContravariantContainer[ -A ]()

  class Person
  class User extends Person
  class Admin extends User

  val inv1: InvariantContainer[ User ] = InvariantContainer[ User ] // works

  //val inv2: InvariantContainer[ User ] = InvariantContainer[ Admin ] // doesn't work

  val cov1: CovariantContainer[ User ] = CovariantContainer[ User ] // works

  val cov2: CovariantContainer[ User ] = CovariantContainer[ Admin ] // works

  val con1: ContravariantContainer[ User ] = ContravariantContainer[ User ] // works

  //val con2: ContravariantContainer[ User ] = ContravariantContainer[ Admin ] // doesn't work

  val con3: ContravariantContainer[ User ] = ContravariantContainer[ Person ] // works
}