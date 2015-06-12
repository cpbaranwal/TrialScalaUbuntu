package chapters_cookBook

object partialFn {
  val a1 = Map(1->2,2->4,3->9,4->16)              //> a1  : scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 2 -> 4, 3 -> 9, 4
                                                  //|  -> 16)
  val a2:PartialFunction[Int,Int]  =
  {
  	case 1 => 1
  	case 2 => 4
  	case 3 => 9
  	case 4 => 16
  }                                               //> a2  : PartialFunction[Int,Int] = <function1>
  val a3 =a2.lift                                 //> a3  : Int => Option[Int] = <function1>
  a3(5)                                           //> res0: Option[Int] = None
  val a4= Function.unlift(a3)                     //> a4  : PartialFunction[Int,Int] = <function1>
  a4.isDefinedAt(5)                               //> res1: Boolean = false
  
  def getSquare(i:Int):Int = i match { case 1 =>1; case 2=> 4;}
                                                  //> getSquare: (i: Int)Int
  getSquare(2)                                    //> res2: Int = 4
  
  //val getSquare2:PartialFunction[Int,Int] = i match{ case 1 =>1; case 2=> 4;}
  //getSquare(2)
  
  a1.isDefinedAt(3)                               //> res3: Boolean = true
  a2.isDefinedAt(3)                               //> res4: Boolean = true
  
  a1(3)                                           //> res5: Int = 9
  a2(3)                                           //> res6: Int = 9
  
  
  
  val l1 = List(1,2,3,4,5,6,7,8,9)                //> l1  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  l1.isDefinedAt(5)                               //> res7: Boolean = true
  l1(5)                                           //> res8: Int = 6
  
  val pf1:PartialFunction[Int,Int] = { case i:Int if(i<10) =>i*i}
                                                  //> pf1  : PartialFunction[Int,Int] = <function1>
  l1 map ( i=>i*i)                                //> res9: List[Int] = List(1, 4, 9, 16, 25, 36, 49, 64, 81)
  l1 map pf1                                      //> res10: List[Int] = List(1, 4, 9, 16, 25, 36, 49, 64, 81)
   //l1 collect { i => i*i}
   l1 collect { pf1}                              //> res11: List[Int] = List(1, 4, 9, 16, 25, 36, 49, 64, 81)
  
  
}