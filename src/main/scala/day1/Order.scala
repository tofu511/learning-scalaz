package day1

import scalaz._
import Scalaz._

object Order {
  def main(args: Array[String]): Unit = {
    println(1 > 2.0) // false
//    println(1 gt 2.0) // コンパイルエラー
    println(1 gt 2) // false

    println(1.0 ?|? 2.0) // LT
    println(2.0 ?|? 1.0) // GT
    println(2.0 ?|? 2.0) // EQ
//    println(1 ?|? 2.0) // コンパイルエラー

    println(1.0 max 2.0) // 2.0
//    println(1 max 2.0) // コンパイルエラー
  }
}
