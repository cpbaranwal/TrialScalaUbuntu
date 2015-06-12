package chapters_cookBook

object chap12_FileAndProcesses {
  println("Welcome to the Scala worksheet chapter 12 FileAndProcesses")
                                                  //> Welcome to the Scala worksheet chapter 12 FileAndProcesses
  
//// reading lines from a file using Source class
import scala.io.Source
val filename = "/home/priya/Desktop/chandan/workspaceChandan/TrialScalaUbuntu/src/chapters_cookBook/finance.txt"
                                                  //> filename  : String = /home/priya/Desktop/chandan/workspaceChandan/TrialScala
                                                  //| Ubuntu/src/chapters_cookBook/finance.txt
for (line <- Source.fromFile(filename).getLines) {
println(line)                                     //> Month, Income, Expenses, Profit
                                                  //| January, 10000.00, 9000.00, 1000.00
                                                  //| February, 11000.00, 9500.00, 1500.00
                                                  //| March, 12000.00, 10000.00, 2000.00
}
//extracting lines from file into a list/array/String
val lines = Source.fromFile(filename).getLines.toList
                                                  //> lines  : List[String] = List(Month, Income, Expenses, Profit, January, 10000
                                                  //| .00, 9000.00, 1000.00, February, 11000.00, 9500.00, 1500.00, March, 12000.00
                                                  //| , 10000.00, 2000.00)
val lines2 = Source.fromFile(filename).getLines.toArray
                                                  //> lines2  : Array[String] = Array(Month, Income, Expenses, Profit, January, 10
                                                  //| 000.00, 9000.00, 1000.00, February, 11000.00, 9500.00, 1500.00, March, 12000
                                                  //| .00, 10000.00, 2000.00)
val fileContents = Source.fromFile(filename).getLines.mkString
                                                  //> fileContents  : String = Month, Income, Expenses, ProfitJanuary, 10000.00, 9
                                                  //| 000.00, 1000.00February, 11000.00, 9500.00, 1500.00March, 12000.00, 10000.00
                                                  //| , 2000.00


////Loan Pattern: Automatic closing of file
//using method: kind of template to accept a resource with close() method and a function to process on resource, nice use of currying
//hint: compare this with synchronized block where unlock method is written in template
def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
try {
f(resource)
} finally {
resource.close()
}                                                 //> using: [A <: AnyRef{def close(): Unit}, B](resource: A)(f: A => B)B
using(io.Source.fromFile(filename)) { source => {
for (line <- source.getLines) {
println(line)
}
}
}                                                 //> Month, Income, Expenses, Profit
                                                  //| January, 10000.00, 9000.00, 1000.00
                                                  //| February, 11000.00, 9500.00, 1500.00
                                                  //| March, 12000.00, 10000.00, 2000.00
  
////Handling Exception while reading a file
def readTextFile(filename: String): Option[List[String]] = {
try {
val lines = using(io.Source.fromFile(filename)) { source =>
(for (line <- source.getLines) yield line).toList
//source.getLines.toList  //same as above line
}
Some(lines)
} catch {
case e: Exception => None
}
}                                                 //> readTextFile: (filename: String)Option[List[String]]
val result = readTextFile(filename)               //> result  : Option[List[String]] = Some(List(Month, Income, Expenses, Profit,
                                                  //|  January, 10000.00, 9000.00, 1000.00, February, 11000.00, 9500.00, 1500.00,
                                                  //|  March, 12000.00, 10000.00, 2000.00))
 

////Processing a csv file
println("Month, Income, Expenses, Profit")        //> Month, Income, Expenses, Profit
val bufferedSource = io.Source.fromFile(filename) //> bufferedSource  : scala.io.BufferedSource = non-empty iterator
for (line <- bufferedSource.getLines.drop(1)) {
val cols = line.split(",").map(_.trim)
// do whatever you want with the columns here
println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
}                                                 //> January|10000.00|9000.00|1000.00
                                                  //| February|11000.00|9500.00|1500.00
                                                  //| March|12000.00|10000.00|2000.00
bufferedSource.close

  
  
////Listing subdirectories : functional way vs imperative way
import java.io.File
//functional way
def getListOfSubDirectories(dir: File): List[String] =
dir.listFiles
.filter(_.isDirectory)
.map(_.getName)
.toList                                           //> getListOfSubDirectories: (dir: java.io.File)List[String]
getListOfSubDirectories(new File("/home/priya"))  //> res0: List[String] = List(Documents, .compiz, Videos, Public, .ivy2, reacti
                                                  //| ve-stocks, .cache, .mozilla, workspace, Templates, .activator, .local, .m2,
                                                  //|  .gconf, Music, .dbus, Desktop, .pki, .sbt, .macromedia, Downloads, .adobe,
                                                  //|  .gnome, Pictures, .eclipse, .config)
//imperative way
def getListOfSubDirectories1(dir: File): List[String] = {
val files = dir.listFiles
val dirNames = collection.mutable.ArrayBuffer[String]()
for (file <- files) {
if (file.isDirectory) {
dirNames += file.getName
}
}
dirNames.toList
}                                                 //> getListOfSubDirectories1: (dir: java.io.File)List[String]
getListOfSubDirectories1(new File("/home/priya")) //> res1: List[String] = List(Documents, .compiz, Videos, Public, .ivy2, reacti
                                                  //| ve-stocks, .cache, .mozilla, workspace, Templates, .activator, .local, .m2,
                                                  //|  .gconf, Music, .dbus, Desktop, .pki, .sbt, .macromedia, Downloads, .adobe,
                                                  //|  .gnome, Pictures, .eclipse, .config)
//improved imperative way
def getListOfSubDirectories2(dir: File): List[String] = {
val files = dir.listFiles
val dirs = for {
file <- files
if file.isDirectory
} yield file.getName
dirs.toList
}                                                 //> getListOfSubDirectories2: (dir: java.io.File)List[String]
getListOfSubDirectories2(new File("/home/priya")) //> res2: List[String] = List(Documents, .compiz, Videos, Public, .ivy2, reacti
                                                  //| ve-stocks, .cache, .mozilla, workspace, Templates, .activator, .local, .m2,
                                                  //|  .gconf, Music, .dbus, Desktop, .pki, .sbt, .macromedia, Downloads, .adobe,
                                                  //|  .gnome, Pictures, .eclipse, .config)
  
  ////Reading/Writing from/to fileInput/OutputStream
import java.io._
var in = None: Option[FileInputStream]            //> in  : Option[java.io.FileInputStream] = None
var out = None: Option[FileOutputStream]          //> out  : Option[java.io.FileOutputStream] = None
try {
in = Some(new FileInputStream(filename))
out = Some(new FileOutputStream("/tmp/filename"))
var c = 0
while ({c = in.get.read; c != -1}) {
out.get.write(c)
}
} catch {
case e: IOException => e.printStackTrace
} finally {
println("entered finally ...")
if (in.isDefined) in.get.close
if (out.isDefined) out.get.close
}                                                 //> entered finally ...
  
  
}