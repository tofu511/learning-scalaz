package day1

import scalaz._
import Scalaz._

object Show {

  def main(args: Array[String]): Unit = {
    println(3.show) // 3
    println(3.show.getClass) // scalaz.Cord

    println(2.shows) // 2
    println(2.shows.getClass) // java.lang.String

    "hello".println // "hello"
  }
}
