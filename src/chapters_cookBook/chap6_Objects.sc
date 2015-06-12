package chapters_cookBook

object chap6_Objects {
  println("Welcome to the chapter 6 Objects worksheet")
  
  //Object 3 types: 1. class instance(like java)    2. Singleton/Companion (scala only)    3.Package Object (scala only)
  //Object type1: class instance Object
  val a = 10
  val b = a.asInstanceOf[Long]
  //instead of String.class in jva, we uselike below in scala
  val stringClass = classOf[String]
  //stringClass.getMethods()
  
 //2 ways of writing Main class in scala
 //note: App trait is implemented using the DelayedInit functionality, which means that fields of the object will not have been initialized before the main method has been executed.
  object Hello extends App {
println("Hello, world")
}
//Hello
object Hello2 {
def main(args: Array[String]) {
println("Hello, world")
}
}
//Hello2
 
 
//Object Type 2: Singleton Object
object CashRegister {
def open { println("opened now!") }
def close { println("closed now!") }
}
CashRegister.open
CashRegister.close
//Companion Object: Object defined with same name in the same file of a class
//methods in Companion object are like static methods;   methods in class are like non-static instance methods
class Pizza (var crustType: String) {
override def toString = "Crust type is " + crustType
}
// companion object
//note: important to know that a class and its companion object can access each other’s private members
object Pizza {
val CRUST_TYPE_THIN = "thin"
val CRUST_TYPE_THICK = "thick"
def getFoo = "Foo"
}
var p = new Pizza(Pizza.CRUST_TYPE_THICK)
println(p)


//Object type 3: Package Object
//put your code in a file named package.scala in the directory where you want your code to be available
//when want to access this code directly from within other classes, traits, and objects in the package
//read page-182 for details when u want to use it









}