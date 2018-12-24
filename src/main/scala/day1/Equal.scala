package day1

import scalaz._
import Scalaz._

object Equal {
  def main(args: Array[String]): Unit = {
    println(1 === 1) // true
    //  println(1 === "foo") // コンパイルエラー
    println(1 == "foo") // false
    println(1.some =/= 2.some) // true
    println(1 assert_=== 1) // Unit
    println(1 assert_=== 2) // RuntimeException
  }
}
