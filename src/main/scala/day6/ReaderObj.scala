package day6

import scalaz._
import Scalaz._

object ReaderObj {

  def main(args: Array[String]): Unit = {

    val f = (_: Int) * 5
    val g = (_: Int) + 3

    val res1 = (g map f)(8)
    println(res1) // 55

    val ff = ({(_: Int) * 2} |@| {(_: Int) + 10}) {_ + _}
    println(ff(3)) // 19

    println(addStuff(3)) // 19
  }

  def addStuff: Int => Int = for {
    a <- (_: Int) * 2
    b <- (_: Int) + 10
  } yield a + b


}
