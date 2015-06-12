package chapters_cookBook

object Strings_wc {

  println("Welcome to the Strings worksheet")     //> Welcome to the Strings worksheet
  
  val str="chandan"                               //> str  : String = chandan
  //String as seq of char
  str.foreach(print)                              //> chandan
 // for(c<-str) println(c)
  //str.getBytes().foreach(println)
  
  
  val result = "hello world".filter(_!='l')       //> result  : String = heo word
  //multiline string
  val str2= """this is
  							a multiline string"""
                                                  //> str2  : String = this is
                                                  //|   							a multiline string
  //split method- overloaded java String + scala StringLike class
  "hello world".split(" ");                       //> res0: Array[String] = Array(hello, world)
  //"hello world".split(' ');
  
  
  //String interpolation scala2.10 onwards: s is method, other interpolators:f,raw
  println(s"hi eveyone my name is $str");         //> hi eveyone my name is chandan
  
  
  //patterns in string: return option
  val numPattern = "[0-9]+".r                     //> numPattern  : scala.util.matching.Regex = [0-9]+
  numPattern.findFirstIn("1234 main street suite 101")
                                                  //> res1: Option[String] = Some(1234)
  
  
  
  //implicit : add new functionalities without overriding existing classes like String
  implicit class StringImprovements(val s: String) {
def increment = s.map(c => (c + 1).toChar)
}
println("HAL".increment)                          //> IBM
  
  
  
  
  
  
  
  
}