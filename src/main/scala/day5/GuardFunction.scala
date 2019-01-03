package day5

import scalaz._
import Scalaz._

object GuardFunction {

  def main(args: Array[String]): Unit = {
    val res1 = for {
      x <- 1 |-> 50 if x.shows contains '7'
    }  yield x
    println(res1) // List(7, 17, 27, 37, 47)

    val res2 = for {
      x <- 0 |-> 10
    } yield x
    println(res2) // List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val res3 = for {
      x <- 'a' |-> 'z'
    } yield x
    println(res3) // List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z)

    val res4 = List(1,2,3) <+> List(4,5,6)
    println(res4) // List(1, 2, 3, 4, 5, 6)

    val res5 = 9.some <+> 10.some
    println(res5) // Some(9)

    val res6 = (none: Option[Int]) <+> 10.some
    println(res6) // Some(10)

    val res7 = (1 |-> 50) filter {x => x.shows.contains('7')}
    println(res7) // List(7, 17, 27, 37, 47)

  }
}
