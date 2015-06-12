package chapters_cookBook

object chap4_classesAndProperties2 {
  println("Welcome to the Scala worksheet chapter 4: Classes and Properties 2")
                                                  //> Welcome to the Scala worksheet chapter 4: Classes and Properties 2
////lazy fields in class
class Foo {
lazy val text = io.Source.fromFile("/etc/passwd").getLines().foreach( print)  //lazy defers computation till accessed
// val text = io.Source.fromFile("/etc/passwd").getLines().foreach( print)
}
val f = new Foo                                   //> f  : chapters_cookBook#21.chap4_classesAndProperties2#927605.Foo#1437481 = c
                                                  //| hapters_cookBook.chap4_classesAndProperties2$$anonfun$main$1$Foo$1@49a8b29c


////uninitialized var Fields: use Option/None as initial value
case class Person(var username: String, var password: String) {
var age = 0
var firstName = ""
var lastName = ""
var address = None: Option[Address]   //when unsure of initial value, Use Option and initialize with None
}
case class Address(city: String, state: String, zip: String)



////abstract class: use it in place of trait when 1.want to create base class with constructor argument     2. if code is to be called from java code(java code cant use Trait APIs)
// can declare both abstract var and val fields in abstract class
abstract class Pet (name: String) {
val greeting: String   //abstract val field
var age: Int					 //abstract val field
def sayHello { println(greeting) }
override def toString = s"I say $greeting, and I'm $age"
}
class Dog (name: String) extends Pet (name) {
val greeting = "Woof"
var age = 2
}




  
  
}