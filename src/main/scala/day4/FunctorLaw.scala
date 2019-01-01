package day4

import scalaz._
import Scalaz._

object FunctorLaw {

  def main(args: Array[String]): Unit = {

    // ファンクター第一法則
    // idでファンクター値を写した場合、ファンクター値が変化してはならない
    List(1, 2, 3) map { identity } assert_=== List(1, 2, 3)
    Option("a") map { identity } assert_=== Option("a")

    val f: Int => Int = (x: Int) => x + 1
    val g: Int => Int = (y: Int) => y * 3
    val fg: Int => Int = f map g

    List(1, 2, 3).map(fg) assert_=== List(1, 2, 3).map { f.map { g } }

  }
}
