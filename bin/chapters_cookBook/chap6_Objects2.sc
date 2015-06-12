package chapters_cookBook

object chap6_Objects2 {
  println("Welcome to the chapter6 Objects 2nd worksheet")
                                                  //> Welcome to the chapter6 Objects 2nd worksheet
  
//creating Object instnace without NEW keyword:   1. apply method as factory method to invoke constructor    2. case class
class Person {
var name: String = _
}
object Person {
def apply(name: String): Person = {
var p = new Person
p.name = name
p
}
}
val dawn = Person("Dawn");                        //> dawn  : chapters_cookBook.chap6_Objects2.Person = chapters_cookBook.chap6_Ob
                                                  //| jects2$$anonfun$main$1$Person$2@58cdd92e

case class Person2(var name: String)
val p = Person2("chandan")                        //> p  : chapters_cookBook.chap6_Objects2.Person2 = Person2(chandan)
val p2= Person2.apply("prakash")                  //> p2  : chapters_cookBook.chap6_Objects2.Person2 = Person2(prakash)

//
}