package chapters_cookBook



object chap3_controlStructures {
  println("Welcome to the Scala worksheet chapter 3 Control Structures")
                                                  //> Welcome to the Scala worksheet chapter 3 Control Structures
  
// looping until a limit
val a = Array("apple", "banana", "orange")        //> a  : Array[String] = Array(apple, banana, orange)
for (i <- 0 until a.length)
	println(s"$i is ${a(i)}")                 //> 0 is apple
                                                  //| 1 is banana
                                                  //| 2 is orange
//for loop with Guard
for (i <- 1 to 10 if i < 4) println(i)            //> 1
                                                  //| 2
                                                  //| 3
                                                  
                                                  
//looping over a Map
val names = Map("fname" -> "Robert","lname" -> "Goren")
                                                  //> names  : scala.collection.immutable.Map[String,String] = Map(fname -> Robert
                                                  //| , lname -> Goren)
for ((k,v) <- names) println(s"key: $k, value: $v")
                                                  //> key: fname, value: Robert
                                                  //| key: lname, value: Goren
/**
Some rules:
1. A simple for loop that iterates over a collection is translated to a foreach method
call on the collection.
2. A for loop with a guard (see Recipe 3.3) is translated to a sequence of a withFilter
method call on the collection followed by a foreach call.
3. A for loop with a yield expression is translated to a map method call on the col‐
lection.
4. A for loop with a yield expression and a guard is translated to a withFilter
method call on the collection, followed by a map method call.
*/
for (i <- 1 to 3) println(i)  //gets converted to //> 1
                                                  //| 2
                                                  //| 3
1.to(3).foreach(((i) => println(i)))              //> 1
                                                  //| 2
                                                  //| 3

for {
i <- 1 to 6
if i % 2 == 0
} println(i)   //gets converted to                //> 2
                                                  //| 4
                                                  //| 6
(1 to(6)).withFilter(_%2==0).foreach(println )    //> 2
                                                  //| 4
                                                  //| 6
for {
i <- 1 to 6
if i % 2 == 0
} yield i   //gets converted to                   //> res0: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6)
(1 to 6).withFilter(_%2==0).map(i => i)           //> res1: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6)



//breakable/break  methods in place of java break and continue keywords
//scala doesnt have break/continue keywords, both breakable and break are methods. break throws exception which is caught by breakable
//breakable inside for loop before if: acts as continue
//breakable outside for loop : acts as break
import util.control.Breaks._
println("\n=== BREAK EXAMPLE ===")                //> 
                                                  //| === BREAK EXAMPLE ===
breakable {
for (i <- 1 to 10) {
println(i)
if (i >=2) break // break out of the for loop
}
}                                                 //> 1
                                                  //| 2
println("\n=== CONTINUE EXAMPLE ===")             //> 
                                                  //| === CONTINUE EXAMPLE ===
val searchMe = "peter piper picked a peck of pickled peppers"
                                                  //> searchMe  : String = peter piper picked a peck of pickled peppers
var numPs = 0                                     //> numPs  : Int = 0
for (i <- 0 until searchMe.length) {
breakable {
if (searchMe.charAt(i) != 'p') {
break // break out of the 'breakable', continue the outside loop
} else {
numPs += 1
}
}
}
println("Found " + numPs + " p's in the string.") //> Found 9 p's in the string.
//smart way: doing count as above can be achieved smartly in one line as below
val count = searchMe.count(_ == 'p')              //> count  : Int = 9


////Tail recursion
def factorial(n: Int): Int = {
if (n == 1) 1
else n * factorial(n - 1)
}                                                 //> factorial: (n: Int)Int
factorial(5)                                      //> res2: Int = 120
import scala.annotation.tailrec
def factorialTail(n: Int): Int = {
@tailrec def factorialTailHelper(n:Int, acc:Int):Int = {
n match
	{
	  case 0|1 => acc
	  case num => factorialTailHelper(num-1, num*acc)
	}
}
factorialTailHelper(n,1)
}                                                 //> factorialTail: (n: Int)Int
factorialTail(5)                                  //> res3: Int = 120



////Pattern Matching types in match expression : some examples for reference
def echoWhatYouGaveMe(x: Any): String = x match {
// constant patterns
case 0 => "zero"
case true => "true"
case "hello" => "you said 'hello'"
case Nil => "an empty List"
// sequence patterns
case List(0, _, _) => "a three-element list with 0 as the first element"
case List(1, _*) => "a list beginning with 1, having any number of elements"
case Vector(1, _*) => "a vector starting with 1, having any number of elements"
// tuples
case (a, b) => s"got $a and $b"
case (a, b, c) => s"got $a, $b, and $c"
// constructor patterns
case Person(first, "Alexander") => s"found an Alexander, first name = $first"
case Dog("Suka") => "found a dog named Suka"
// typed patterns
case s: String => s"you gave me this string: $s"
case i: Int => s"thanks for the int: $i"
case f: Float => s"thanks for the float: $f"
case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
case d: Dog => s"dog: ${d.name}"
case list: List[_] => s"thanks for the List: $list"
case m: Map[_, _] => m.toString
// the default wildcard pattern
case _ => "Unknown"
}                                                 //> echoWhatYouGaveMe: (x: Any)String

case class Person(firstName: String, lastName: String)
case class Dog(name: String)
// trigger the constant patterns
println(echoWhatYouGaveMe(0))                     //> zero
println(echoWhatYouGaveMe(true))                  //> true
println(echoWhatYouGaveMe("hello"))               //> you said 'hello'
println(echoWhatYouGaveMe(Nil))                   //> an empty List
// trigger the sequence patterns
println(echoWhatYouGaveMe(List(0,1,2)))           //> a three-element list with 0 as the first element
println(echoWhatYouGaveMe(List(1,2)))             //> a list beginning with 1, having any number of elements
println(echoWhatYouGaveMe(List(1,2,3)))           //> a list beginning with 1, having any number of elements
println(echoWhatYouGaveMe(Vector(1,2,3)))         //> a vector starting with 1, having any number of elements
// trigger the tuple patterns
println(echoWhatYouGaveMe((1,2)))                 //> got 1 and 2
println(echoWhatYouGaveMe((1,2,3)))               //> got 1, 2, and 3
// two element tuple
// three element tuple
// trigger the constructor patterns
println(echoWhatYouGaveMe(Person("Melissa", "Alexander")))
                                                  //> found an Alexander, first name = Melissa
println(echoWhatYouGaveMe(Dog("Suka")))           //> found a dog named Suka
// trigger the typed patterns
println(echoWhatYouGaveMe("Hello, world"))        //> you gave me this string: Hello, world
println(echoWhatYouGaveMe(42))                    //> thanks for the int: 42
println(echoWhatYouGaveMe(42F))                   //> thanks for the float: 42.0
println(echoWhatYouGaveMe(Array(1,2,3)))          //> an array of int: 1,2,3
println(echoWhatYouGaveMe(Array("coffee", "apple pie")))
                                                  //> an array of strings: coffee,apple pie
println(echoWhatYouGaveMe(Dog("Fido")))           //> dog: Fido
println(echoWhatYouGaveMe(List("apple", "banana")))
                                                  //> thanks for the List: List(apple, banana)
println(echoWhatYouGaveMe(Map(1->"Al", 2->"Alexander")))
                                                  //> Map(1 -> Al, 2 -> Alexander)



  
}