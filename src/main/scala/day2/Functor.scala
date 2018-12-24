package day2

import scalaz._
import Scalaz._

object Functor {

  def main(args: Array[String]): Unit = {

    val f: Int => Int = ((x: Int) => x + 1) map {_ * 7}
    println(f(3)) // 28

    val ff: List[Int] => List[Int] = scalaz.Functor[List].lift {(_: Int) * 2}
    println(ff(List(2))) // List(4)

    println(List(1, 2, 3) >| "x") // List(x, x, x)

    println(List(1, 2, 3) as "x") // List(x, x, x)

    println(List(1, 2, 3).fpair) // List((1,1), (2,2), (3,3))

    println(List(1, 2, 3).strengthL("x")) // List((x,1), (x,2), (x,3))

    println(List(1, 2, 3).strengthR("x")) // List((1,x), (2,x), (3,x))

    println(List(1, 2, 3).void) // List((), (), ())

  }
}
