package chapters_cookBook

object chap4_classesAndProperties {
  println("Welcome to the Scala worksheet chapter 4: Classes and Properties")
                                                  //> Welcome to the Scala worksheet chapter 4: Classes and Properties

///Primary Constructor: var-mutable,getter setters will be generated ,    val-immumtabl,getter but no setter will be generated,   no var/val- no setter no getter
class Person(var firstName: String, val lastName: String, address:String) {
println("the constructor begins")
// some class fields
private val HOME = System.getProperty("user.home")
var age = 0
// some methods
override def toString = s"$firstName $lastName is $age years old"
def printHome { println(s"HOME = $HOME") }
def printFullName { println(this) } // uses toString
printHome
printFullName
println("still in the constructor")
}
val p = new Person("chandan", "prakash","Gaya")   //> the constructor begins
                                                  //| HOME = /home/priya
                                                  //| chandan prakash is 0 years old
                                                  //| still in the constructor
                                                  //| p  : chapters_cookBook#21.chap4_classesAndProperties#35585.Person#922375 = c
                                                  //| handan prakash is 0 years old
p.firstName                                       //> res0: String#574510 = chandan
p.firstName= "priya"
//p.lastName= "barnwal"  //no setter
p.lastName                                        //> res1: String#574510 = prakash
//p.address    //no setter no getter



//auxiliary constructors
class Pizza (var crustSize: Int, var crustType: String) {  // primary constructor
def this(crustSize: Int) {  // one-arg auxiliary constructor
this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
}
def this(crustType: String) {    // one-arg auxiliary constructor
this(Pizza.DEFAULT_CRUST_SIZE, crustType)
}
def this() {     // zero-arg auxiliary constructor
this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
}
override def toString = s"A $crustSize inch pizza with a $crustType crust"
}
object Pizza {
val DEFAULT_CRUST_SIZE = 12
val DEFAULT_CRUST_TYPE = "THIN"
}
val p1= new Pizza(25, "thick")                    //> p1  : chapters_cookBook#21.chap4_classesAndProperties#35585.Pizza#922378 = 
                                                  //| A 25 inch pizza with a thick crust
val p2= new Pizza(Pizza.DEFAULT_CRUST_SIZE)       //> p2  : chapters_cookBook#21.chap4_classesAndProperties#35585.Pizza#922378 = 
                                                  //| A 12 inch pizza with a THIN crust
val p3= new Pizza(Pizza.DEFAULT_CRUST_TYPE)       //> p3  : chapters_cookBook#21.chap4_classesAndProperties#35585.Pizza#922378 = 
                                                  //| A 12 inch pizza with a THIN crust
val p4= new Pizza                                 //> p4  : chapters_cookBook#21.chap4_classesAndProperties#35585.Pizza#922378 = 
                                                  //| A 12 inch pizza with a THIN crust
    

/// case class: constructor parameters are by default val, u can change it to var but it defeats the purpose of case class
case class Person2(name: String)
val p22 = Person2("Dale Cooper")                  //> p22  : chapters_cookBook#21.chap4_classesAndProperties#35585.Person2#922389
                                                  //|  = Person2(Dale Cooper)
val p33 = Person2.apply("chandan") //same as previous line: case class has inbuilt apply/unapply methods , apply method calls object creatin, unapply for regex match
                                                  //> p33  : chapters_cookBook#21.chap4_classesAndProperties#35585.Person2#922389
                                                  //|  = Person2(chandan)
					//if you need more variants of apply methods, you need to write apply methods in companion object in person.scala file


//Singleton class: primary constructor is private, expose API in the companion object
class Brain private (name: String){
override def toString = "This is the brain."
}
object Brain {
val brain = new Brain("my brain")
def getInstance = brain
}
//val b = new Brain  wont compile
Brain.getInstance                                 //> res2: chapters_cookBook#21.chap4_classesAndProperties#35585.Brain#922396 = 
                                                  //| This is the brain.


  
}