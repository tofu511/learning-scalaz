package day9

import scalaz._
import Scalaz._

object ZipperObj {
  def main(args: Array[String]): Unit = {
    val res1 = Stream(1, 2, 3, 4)
    println(res1) // Stream(1, ?)

    val res2 = Stream(1, 2, 3, 4).toZipper
    println(res2) // Some(Zipper(<lefts>, 1, <rights>))

    val res3 = Stream(1, 2, 3, 4).toZipper >>= {_.next}
    println(res3) // Some(Zipper(<lefts>, 2, <rights>))

    val res4 = res3 >>= {_.previous}
    println(res4) // Some(Zipper(<lefts>, 1, <rights>))

    val res5 = Stream(1, 2, 3, 4).toZipper >>= {_.next} >>= {_.modify {_ => 7}.some}
    println(res5) // Some(Zipper(<lefts>, 7, <rights>))

    val res6 = res5.get.toStream.toList
    println(res6) // List(1, 7, 3, 4)
  }
}
