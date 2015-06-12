package chapters_cookBook

object chap8_traits {
  println("Welcome to the Scala worksheet: chapter 8 Traits")
 //Trait: unlike java interface, they can have implemented methds e.g. class c1 extends trait1 with trait2 with trait3....and so on
 //				unlike abstract class, multiple traits can be mixed in a class and we can control which classes a particular Trait can be mixed into
 //abstract class shud be preferred over trait only when u need some parameterized constructor in abstract class, which is not possible in trait
 
 
 //Using Abstract and Concrete Fields in Traits
 trait PizzaTrait {
var numToppings: Int // abstract
var size = 14 // concrete
val maxNumToppings = 10 // concrete
}
class Pizza extends PizzaTrait {
var numToppings = 0 // 'override' not needed
size = 16 // 'var' and 'override' not needed
override val maxNumToppings = 10 // 'override' is required
}


// Marking Traits So They Can Only Be Used by Subclasses of a Certain Type
trait StarfleetWarpCore {
this: Starship =>
// more code here ...
}
class Starship
class Enterprise extends Starship with StarfleetWarpCore
class RomulanShip
//class Warbird extends RomulanShip with StarfleetWarpCore  // this won't compile



//Structural approach: Ensuring a Trait Can Only Be Added to a Type That Has a Specific Method/structure
trait WarpCore2 {
this: { def ejectWarpCore(password: String): Boolean } =>
}
class Starship2 {
// code here ...
}
class Enterprise2 extends Starship2 with WarpCore2 {
def ejectWarpCore(password: String): Boolean = {  //wont compile without this method
if (password == "password") {
println("ejecting core")
true
}
false
}
}


//On the go: Adding a Trait to an Object Instance
class DavidBanner
trait Angry {
println("You won't like me ...")
}
object Test extends App {
val hulk = new DavidBanner with Angry
}
Test    //will print the the above line included in constructor


// rarely used: Limiting Which Classes Can Use a Trait by Inheritance
class StarfleetComponent3
trait StarfleetWarpCore3 extends StarfleetComponent3
class Starship3 extends StarfleetComponent3 with StarfleetWarpCore3
  
  
}