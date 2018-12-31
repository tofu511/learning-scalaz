package day3

import scalaz.{@@, Tag}

object TaggedType {

  def KiloGram[A](a: A):A @@ KiloGram = Tag[A, KiloGram](a)

  def main(args: Array[String]): Unit = {

    val mass = KiloGram(20.0)
    println(mass) // 20.0

    println(2 * Tag.unwrap(mass)) // 40.0
//    println(2 * mass) // コンパイルエラー

    println(Tag.unwrap(mass)) // 20.0
  }
}
