package chapters_cookBook

object chap20_idioms {
  println("Welcome to the Scala worksheet chapter 20 worksheet")
                                                  //> Welcome to the Scala worksheet chapter 20 worksheet
   
   //Option: Some/None
  def toInt(s: String): Option[Int] = {
try {
Some(Integer.parseInt(s.trim))
} catch {
case e: Exception => None
}
}                                                 //> toInt: (s: String)Option[Int]

val x = toInt("1").get                            //> x  : Int = 1
val x2 = toInt("foo")                             //> x2  : Option[Int] = None
val x3 = toInt("1").getOrElse(0)                  //> x3  : Int = 1
val x4 = toInt("foo").getOrElse(0)                //> x4  : Int = 0

 toInt("1").foreach{ i =>
println(s"Got an int: $i")
}                                                 //> Got an int: 1
toInt("1") match {
case Some(i) => println(i)
case None => println("That didn't work.")
}                                                 //> 1

val bag = List("1", "2", "foo", "3", "bar")       //> bag  : List[String] = List(1, 2, foo, 3, bar)
bag.flatMap(toInt)                                //> res0: List[Int] = List(1, 2, 3)
bag.map(toInt)                                    //> res1: List[Option[Int]] = List(Some(1), Some(2), None, Some(3), None)
bag.map(toInt) flatten                            //> res2: List[Int] = List(1, 2, 3)



//TRY: success/failure
import scala.util.{Try,Success,Failure}
def divideXByY(x: Int, y: Int): Try[Int] = {
Try(x / y)
}                                                 //> divideXByY: (x: Int, y: Int)scala.util.Try[Int]
divideXByY(1,1)                                   //> res3: scala.util.Try[Int] = Success(1)
divideXByY(1,0)                                   //> res4: scala.util.Try[Int] = Failure(java.lang.ArithmeticException: / by zero
                                                  //| )
divideXByY(1, 1).foreach(println)                 //> 1
divideXByY(1, 0).foreach(println)
divideXByY(1, 1) match {
case Success(i) => println(s"Success, value is: $i")
case Failure(s) => println(s"Failed, message is: $s")
}                                                 //> Success, value is: 1
divideXByY(1, 0) match {
case Success(i) => println(s"Success, value is: $i")
case Failure(s) => println(s"Failed, message is :: $s")
}                                                 //> Failed, message is :: java.lang.ArithmeticException: / by zero


  
  
  
  
  
  
}