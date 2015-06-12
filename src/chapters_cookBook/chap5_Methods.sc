package chapters_cookBook

object chap5_Methods {
  println("Welcome to the Scala worksheet chapter 5: Methods")
                                                  //> Welcome to the Scala worksheet chapter 5: Methods
  
//object private scope:
class Foo {
private[this] def isFoo = true
def doFoo(other: Foo) {
		//if (other.isFoo) { }  //uncomment theline: wont compile as isFoo method has been defined Object private
 }
 }
  
  
//package private scope
class Foo2 {
private[chapters_cookBook] def isFoo2 = true
def doFoo2(other: Foo2) {
		if (other.isFoo2) { }  // wont be available to use outside the given package
 }
 }


//Controlling which trait you call a method from
trait Human {
def hello = "the Human trait"
}
trait Mother extends Human {
override def hello = "Mother"
}
trait Father extends Human {
override def hello = "Father"
}
class Child extends Human with Mother with Father {
def printSuper = super.hello
def printMother = super[Mother].hello   //will call hello from Mother
def printFather = super[Father].hello
def printHuman = super[Human].hello
}
val ch = new Child                                //> ch  : chapters_cookBook.chap5_Methods.Child = chapters_cookBook.chap5_Method
                                                  //| s$$anonfun$main$1$Child$1@6b573f80
ch.printMother                                    //> res0: String = Mother
ch.printFather                                    //> res1: String = Father



// Default values as Method parameters
class Connection {
def makeConnection(timeout: Int = 5000, protocol: String= "unknown") {
println("timeout = %d, protocol = %s".format(timeout, protocol))
}
}
val c = new Connection                            //> c  : chapters_cookBook.chap5_Methods.Connection = chapters_cookBook.chap5_M
                                                  //| ethods$$anonfun$main$1$Connection$1@1eb59fd9
c.makeConnection()                                //> timeout = 5000, protocol = unknown
c.makeConnection(2000)                            //> timeout = 2000, protocol = unknown
c.makeConnection(protocol="https",timeout = 3000)  //with names provided, order of params can be shuffled
                                                  //> timeout = 3000, protocol = https
//c.makeConnection   //not allowed although default values present: Partially Applied Functions


//Returning multiple items: Tuples
 class StockInfo {
 def getStockInfo = {
// other code here ...
("NFLX", 100.00, 101.00) // this is a Tuple3
}
}
val obj = new StockInfo                           //> obj  : chapters_cookBook.chap5_Methods.StockInfo = chapters_cookBook.chap5_
                                                  //| Methods$$anonfun$main$1$StockInfo$1@4e0b495e
val (symbol, currentPrice, bidPrice) =  obj.getStockInfo
                                                  //> symbol  : String = NFLX
                                                  //| currentPrice  : Double = 100.0
                                                  //| bidPrice  : Double = 101.0

//Forcing to leave parantheses
class Pizza {
def crustSize = 12  // no parentheses after crustSize
}
val p = new Pizza                                 //> p  : chapters_cookBook.chap5_Methods.Pizza = chapters_cookBook.chap5_Method
                                                  //| s$$anonfun$main$1$Pizza$1@38ffdb99
//p.crustSize()  //parantheses () not allowed
p.crustSize                                       //> res2: Int = 12


// Declaring exception handling in method
class Game {
@throws(classOf[Exception])  //optional: throws annotation is used but is not mandatory, nothing called checked exceptions in scala
def play {
throw new Exception("unknown exception");
}
}
val g = new Game                                  //> g  : chapters_cookBook.chap5_Methods.Game = chapters_cookBook.chap5_Methods
                                                  //| $$anonfun$main$1$Game$1@3ddca028
g.play                                            //> java.lang.Exception: unknown exception
                                                  //| 	at chapters_cookBook.chap5_Methods$$anonfun$main$1$Game$1.play(chapters_
                                                  //| cookBook.chap5_Methods.scala:82)
                                                  //| 	at chapters_cookBook.chap5_Methods$$anonfun$main$1.apply$mcV$sp(chapters
                                                  //| _cookBook.chap5_Methods.scala:86)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at chapters_cookBook.chap5_Methods$.main(chapters_cookBook.chap5_Methods
                                                  //| .scala:3)
                                                  //| 	at chapters_cookBook.chap5_Methods.main(chapters_cookBook.chap5_Methods.
                                                  //| scala)
  
  
}