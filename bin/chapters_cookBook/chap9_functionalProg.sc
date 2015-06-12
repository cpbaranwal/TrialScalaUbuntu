package chapters_cookBook

object chap9_functionalProg {
  println("Welcome to chapter 9 functional programming  worksheet....")
                                                  //> Welcome to chapter 9 functional programming  worksheet....
//anonymous function is referred as function literal: can be passed as argument to other methods/functions
//scala follows EOP (expression oriented programming), every expression returns a value, even the try/catch block

val x = List.range(1, 10)                         //> x  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
//function literal inside filter, symbol => is a transformer
val evens = x.filter((i: Int) => i % 2 == 0)      //> evens  : List[Int] = List(2, 4, 6, 8)
// when parameter say i appears only once in the function,we can replace it by wildcard _
val evens2 = x.filter(_ % 2 == 0)                 //> evens2  : List[Int] = List(2, 4, 6, 8)

//all are same below as scala can infer types (Type inference)
//for last statemet we dont even need wildcard _ because function literal takes only single argument and that is same as the element passed.
x.foreach((i:Int) => print("  "+i))               //>   1  2  3  4  5  6  7  8  9
x.foreach(i => print("  "+i))                     //>   1  2  3  4  5  6  7  8  9
x.foreach(print(_))                               //> 123456789
//x.foreach(println)
  
  
////functions as variable values
val f = (i: Int) => { i % 2 == 0 }                //> f  : Int => Boolean = <function1>
f(5)                                              //> res0: Boolean = false
  
////2 ways of declaring function: 1. implicit approach    2. explicit approach
val f1 = (i: Int) => { i % 2 == 0 }   // i prefer this one, short and concise
                                                  //> f1  : Int => Boolean = <function1>
val f11: Int=> Boolean = i => i%2==0              //> f11  : Int => Boolean = <function1>



////Partially applied function
val c = scala.math.cos _                          //> c  : Double => Double = <function1>
c(0)                                              //> res1: Double = 1.0
  



////Passing function/method as parameters of other method/function: same base function can be used with different implementation functions
val sum = (x: Int, y: Int) => x + y               //> sum  : (Int, Int) => Int = <function2>
val multiply = (x: Int, y: Int) => x * y          //> multiply  : (Int, Int) => Int = <function2>
def executeAndPrint(f:(Int, Int) => Int, x: Int, y: Int) {
val result = f(x, y)
println(result)
}                                                 //> executeAndPrint: (f: (Int, Int) => Int, x: Int, y: Int)Unit
executeAndPrint(sum, 2, 9) //cool, simply pass different implementation methods
                                                  //> 11
executeAndPrint(multiply, 3, 9)                   //> 27




//// Closures: function passed as a variable keeps reference to a variable when the function was created
class Foo {
// a method that takes a function and a string, and passes the string into
// the function, and then executes the function
def exec(f:(String) => Unit, name: String) {
f(name)
}
}
var hello = "Hello"                               //> hello  : String = Hello
def sayHello(name: String) { println(s"$hello, $name") }
                                                  //> sayHello: (name: String)Unit
// execute sayHello from the exec method foo
val foo = new Foo                                 //> foo  : chapters_cookBook.chap9_functionalProg.Foo = chapters_cookBook.chap9
                                                  //| _functionalProg$$anonfun$main$1$Foo$1@4aa0a82c
foo.exec(sayHello, "Al")                          //> Hello, Al
// change the local variable 'hello', then execute sayHello from
// the exec method of foo, and see what happens
hello = "Hola"
foo.exec(sayHello, "Lorenzo")                     //> Hola, Lorenzo




//// Partial Function: function that does not provide an answer for every possible input value it can be given
//normal function
val divide = (x: Int) => 42 / x                   //> divide  : Int => Int = <function1>
//making the above function as partial function: we can check whether operation shud be done or not in advance
//many existing library function like collect already takes partial function as parameter
//1st way of declaring partial function
val divide2 = new PartialFunction[Int, Int] {
def apply(x: Int) = 42 / x
def isDefinedAt(x: Int) = x != 0
}                                                 //> divide2  : PartialFunction[Int,Int] = <function1>
divide2.isDefinedAt(1)                            //> res2: Boolean = true
if (divide2.isDefinedAt(1)) divide(1)             //> res3: AnyVal = 42
//2nd way of Partial function: isDefinedAt method is defined automatically
val divide3: PartialFunction[Int, Int] = {
case d: Int if d != 0 => 42 / d
}                                                 //> divide3  : PartialFunction[Int,Int] = <function1>
divide3.isDefinedAt(0)                            //> res4: Boolean = false



}