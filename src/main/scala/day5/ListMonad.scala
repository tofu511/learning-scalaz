package day5

import scalaz._
import Scalaz._

object ListMonad {

  def main(args: Array[String]): Unit = {
    val res1 = ^(List(1,2,3), List(10, 100, 1000)) {_ * _}
    println(res1) // List(10, 100, 1000, 20, 200, 2000, 30, 300, 3000)

    val res2 = List(3,4,5) >>= {x => List(x, -x)}
    println(res2) // List(3, -3, 4, -4, 5, -5)

    val res3 = for {
      n <- List(1, 2)
      ch <- List('a', 'b')
    } yield (n, ch)
    println(res3) // List((1,a), (1,b), (2,a), (2,b))

  }
}
