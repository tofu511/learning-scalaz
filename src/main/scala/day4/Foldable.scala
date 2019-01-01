package day4

import scalaz._
import Scalaz._

object Foldable {

  def main(args: Array[String]): Unit = {

    println(List(1, 2, 3).foldRight(1) {_ * _}) // 6

    println(9.some.foldLeft(2){_ + _}) // 11
    println(9.some.foldLeft(2){_ * _}) // 18

    println(List(1, 2, 3).foldMap(identity)) // 6
    println(9.some.foldMap(identity)) // 9
  }
}
