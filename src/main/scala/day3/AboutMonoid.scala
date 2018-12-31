package day3

import scalaz._
import Scalaz._

object AboutMonoid {

  def main(args: Array[String]): Unit = {

    (3 * 2) * (8 * 2) assert_=== 3 * (2 * (8 * 2)) // associativity(結合的)

    List("la") ++ (List("di") ++ List("da")) assert_=== (List("la") ++ List("di")) ++ List("da") // associativity(結合的)

    3 |+| (4 |+| 5) assert_=== (3 |+| 4) |+| 5
    3.mappend(4.mappend(5)) assert_=== (3.mappend(4)).mappend(5)
  }
}
