package chapters_cookBook

object sample1 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val i=10                                        //> i  : Int = 10
  val name = "chandan"                            //> name  : String = chandan

val convert1to5 = new PartialFunction[Int, String] {
val nums = Array("one", "two", "three", "four", "five")
def apply(i: Int) = nums(i-1)
def isDefinedAt(i: Int) = i > 0 && i < 6
}                                                 //> convert1to5  : PartialFunction[Int,String]{val nums: Array[String]} = <funct
                                                  //| ion1>

val convert6to10 = new PartialFunction[Int, String] {
val nums = Array("six", "seven", "eight", "nine", "ten")
def apply(i: Int) = nums(i-6)
def isDefinedAt(i: Int) = i > 5 && i < 11
}                                                 //> convert6to10  : PartialFunction[Int,String]{val nums: Array[String]} = <func
                                                  //| tion1>

val handle1to10 = convert1to5 orElse convert6to10 //> handle1to10  : PartialFunction[Int,String] = <function1>
handle1to10(8)                                    //> res0: String = eight
}