package day8

import scalaz._
import Scalaz._

object UsefulMonadicFunctions {

  def main(args: Array[String]): Unit = {

    val res1 = (Some(9.some): Option[Option[Int]]).join
    println(res1) // Some(9)

    val res2  = (Some(none): Option[Option[Int]]).join
    println(res2) // None

    val res3 = List(List(1,2,3,4), List(5,6,7,8)).join
    println(res3)

    val res4 = 9.right[String].right[String].join
    println(res4) // \/-(9)

    val res5 = "boom".left[Int].right[String].join
    println(res5) // -\/(boom)

    val res6 = List(1,2,3) filterM { x => List(true, false)} // なぜかIDEAでエラーメッセージがでる
    println(res6) // List(List(1, 2, 3), List(1, 2), List(1, 3), List(1), List(2, 3), List(2), List(3), List())

    val res7 = List(1,2,3) filterM { x => List(false)}
    println(res7) // List(List())

    val res8 = List(1,2,3,4).foldLeftM(0)(binSmalls)
    println(res8) // Some(10)

    val res9 = List(1,2,3).foldLeftM(0)(binSmalls)
    println(res9) // Some(6)

    val res10 = List(1,10,5).foldLeftM(0)(binSmalls)
    println(res10) // None

  }

  def binSmalls(acc: Int, x: Int): Option[Int] = {
    if (x > 9) none: Option[Int]
    else (acc + x).some
  }
}
