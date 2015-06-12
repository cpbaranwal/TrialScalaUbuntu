package chapters_cookBook

object chap19_Types {
  println("Welcome to the Scala worksheet chapter 19 Types")
                                                  //> Welcome to the Scala worksheet chapter 19 Types
  
  ////Types: Invariant   Covariant(+)   ContraVariant(-)
  //Invaraiant : Array[T]  used when elements in the container are mutable.example: Can only pass Array[String] to a method expecting Array[String]
  //covariant: Seq[+A]   Used when elements in the container are immutable. This makes the container more flexible. Example: Can pass a Seq[String] to a method expected Seq[Any] .
  //contravariant: Foo[-A]   Contravariance is essentially the opposite of covariance, and is rarely used. See Scala’s  Function1[-A, +B]
class Grandparent
class Parent extends Grandparent
class Child extends Parent
class InvariantClass[A]
class CovariantClass[+A]
class ContravariantClass[-A]
def invarMethod(x: InvariantClass[Parent]) {}     //> invarMethod: (x: chapters_cookBook.chap19_Types.InvariantClass[chapters_cook
                                                  //| Book.chap19_Types.Parent])Unit
def covarMethod(x: CovariantClass[Parent]) {}     //> covarMethod: (x: chapters_cookBook.chap19_Types.CovariantClass[chapters_cook
                                                  //| Book.chap19_Types.Parent])Unit
def contraMethod(x: ContravariantClass[Parent]) {}//> contraMethod: (x: chapters_cookBook.chap19_Types.ContravariantClass[chapters
                                                  //| _cookBook.chap19_Types.Parent])Unit
//invarMethod(new InvariantClass[Child])  // ERROR - won't compile
invarMethod(new InvariantClass[Parent])
//invarMethod(new InvariantClass[Grandparent]) // ERROR - won't compile
covarMethod(new CovariantClass[Child])    //success
covarMethod(new CovariantClass[Parent])
//covarMethod(new CovariantClass[Grandparent]) // ERROR - won't compile
//contraMethod(new ContravariantClass[Child])  // ERROR - won't compile
contraMethod(new ContravariantClass[Parent])
contraMethod(new ContravariantClass[Grandparent])   //success



////Bound:   UpperBound   LowerBound
//UpperBound: A <: B , I think, “ A is less than B ... A is under B ... A is a subtype of B



////Duck Typing: Structural Type (if u quack like a duck, u r a duck)
//below A is a subtype of a type which has a speak method
//note: it uses reflection so performance wise it will be slow
def callSpeak[A <: { def speak(): Unit }](obj: A) {
obj.speak()
}                                                 //> callSpeak: [A <: AnyRef{def speak(): Unit}](obj: A)Unit
class Dog2 { def speak() { println("woof") } }
class Klingon { def speak() { println("Qapla!") } }
callSpeak(new Dog2)                               //> woof
callSpeak(new Klingon)                            //> Qapla!


////Invariant-Covariant : an example
//below when we use mutable Array/ArrayBuffer, we are more restricted than when we use immutable Seq
trait Animal { def speak }
class Dog(var name: String) extends Animal {
def speak { println("woof") }
override def toString = name
}
class SuperDog(name: String) extends Dog(name) {
def useSuperPower { println("Using my superpower!") }
}
val fido = new Dog("Fido")                        //> fido  : chapters_cookBook.chap19_Types.Dog = Fido
val wonderDog = new SuperDog("Wonder Dog")        //> wonderDog  : chapters_cookBook.chap19_Types.SuperDog = Wonder Dog
val shaggy = new SuperDog("Shaggy")               //> shaggy  : chapters_cookBook.chap19_Types.SuperDog = Shaggy
import collection.mutable.ArrayBuffer
val dogs = ArrayBuffer[Dog]()                     //> dogs  : scala.collection.mutable.ArrayBuffer[chapters_cookBook.chap19_Types
                                                  //| .Dog] = ArrayBuffer()
dogs += fido                                      //> res0: chapters_cookBook.chap19_Types.dogs.type = ArrayBuffer(Fido)
dogs += wonderDog                                 //> res1: chapters_cookBook.chap19_Types.dogs.type = ArrayBuffer(Fido, Wonder D
                                                  //| og)
def makeDogsSpeak(dogs: ArrayBuffer[Dog]) {
dogs.foreach(_.speak)
}                                                 //> makeDogsSpeak: (dogs: scala.collection.mutable.ArrayBuffer[chapters_cookBoo
                                                  //| k.chap19_Types.Dog])Unit
makeDogsSpeak(dogs)    //ok to pass a super dog in dog list
                                                  //> woof
                                                  //| woof
val superDogs = ArrayBuffer[SuperDog]()           //> superDogs  : scala.collection.mutable.ArrayBuffer[chapters_cookBook.chap19_
                                                  //| Types.SuperDog] = ArrayBuffer()
superDogs += shaggy                               //> res2: chapters_cookBook.chap19_Types.superDogs.type = ArrayBuffer(Shaggy)
superDogs += wonderDog                            //> res3: chapters_cookBook.chap19_Types.superDogs.type = ArrayBuffer(Shaggy, W
                                                  //| onder Dog)
//makeDogsSpeak(superDogs) // ERROR: won't compile NOTok to pass a super dog  list as in the function a superdog might be replaced bya normal dog since the array is mutable,  this is what invariant means

/** Alternate approach for above,using Seq in place of ArrayBuffer
message: always consider using immutable container instead of mutable container and declare them covariant A+ **/
def makeDogsSpeak2(dogs: Seq[Dog]) {  //changing definition
dogs.foreach(_.speak)
}                                                 //> makeDogsSpeak2: (dogs: Seq[chapters_cookBook.chap19_Types.Dog])Unit
val dogs2 = Seq(new Dog("Fido"), new Dog("Tanner"))
                                                  //> dogs2  : Seq[chapters_cookBook.chap19_Types.Dog] = List(Fido, Tanner)
makeDogsSpeak2(dogs2)  //this works as before     //> woof
                                                  //| woof
val superDogs2 = Seq(new SuperDog("Wonder Dog"), new SuperDog("Scooby"))
                                                  //> superDogs2  : Seq[chapters_cookBook.chap19_Types.SuperDog] = List(Wonder Do
                                                  //| g, Scooby)
makeDogsSpeak2(superDogs2)  //but now this one also works !!
                                                  //> woof
                                                  //| woof

//// implicit functions:   TODO need to explore about this
def add[A](x: A, y: A)(implicit numeric: Numeric[A]): A = numeric.plus(x, y)
                                                  //> add: [A](x: A, y: A)(implicit numeric: Numeric[A])A
println(add(1, 1))                                //> 2
println(add(1.0, 1.5))                            //> 2.5
println(add(1, 1.5F))                             //> 2.5
//add("1", 2.0)   //wont compile: numeric.plus method is implemented for all the different numeric types but not String



////Call By Name approach  (: =>) :lazy evaluation, function passed as argument evaluated only at time when used
//note: Try/Success/Failure uses call by name  (page-631)
//e.g.  val (result, time) = timer(someLongRunningAlgorithm)
def timer[A](blockOfCode: => A) = {   // : =>    call by name
val startTime = System.nanoTime
val result = blockOfCode
val stopTime = System.nanoTime
val delta = stopTime - startTime
(result, delta/1000000d)
}                                                 //> timer: [A](blockOfCode: => A)(A, Double)
val (result, time) = timer(println("Hello"))      //> Hello
                                                  //| result  : Unit = ()
                                                  //| time  : Double = 0.137729



  
}