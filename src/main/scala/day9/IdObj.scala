package day9

import scalaz._
import Scalaz._

object IdObj {
  def main(args: Array[String]): Unit = {
    val res1 = 0: Id[Int]
    println(res1) // 0

    val res2 = 1 + 2 + 3 |> {_.point[List]}
    println(res2) // List(6)

    val res3 = 1 + 2 + 3 |> {_.point[Option]}
    println(res3) // Some(6)

    val res4 = 1 + 2 + 3 |> {_ * 6}
    println(res4) // 36

    val res5 = 2 visit { case x@(2|3) => List(x * 2)}
    println(res5) // List(4)

  }
}
