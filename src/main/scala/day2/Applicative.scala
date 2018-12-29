package day2

import scalaz._
import Scalaz._

object Applicative {

  def main(args: Array[String]): Unit = {

    println(1.point[List]) // List(1)
    println(1.point[Option]) // Some(1)

    println(((x: Int) => x + 1).point[List]) // List(<function1>)
    println(((x: Int) => x + 1).point[Option]) // Some(<function1>)

    println(1.point[Option] map {_ + 2}) // Some(3)
    println(1.point[List] map {_ + 2}) // List(3)

    println(9.some <*> {(_: Int) + 3}.some) // Some(12)
    println(3.point[List] <*> {(_: Int) * 3}.point[List]) // List(9)

    println(1.some <* 3.some) // Some(1)
    println(1.some *> 3.some) // Some(3)

    println(1.point[List] <* 2.point[List]) // List(1)
    println(1.point[List] *> 2.point[List]) // List(2)

    println(^(1.some, 2.some) {_ + _}) // Some(3)
    println(^(1.some, none[Int]) {_ + _}) // None

    val list1 = List(1,2)
    val list2 = List(6,7)
    println(^(list1, list2) {_ + _}) // List(7,8,8,9)
    println(^(list1, list2) {_ * _}) // List(6,7,12,14)

    println(^^(1.some, 2.some, 3.some) {_ + _ + _}) // Some(6)

    println((2.some |@| 3.some) {_ + _}) // Some(5)
    println((2.some |@| none) {(_: Int) + (_: Int)}) // None

    val res1 = List(1,2,3) <*> List((_: Int) * 0, (_: Int) + 100, (x: Int) => x * x)
    println(res1) // List(0,0,0,101,102,103,1,4,9)

    val res2 = (List("ha", "heh", "hmm") |@| List("?", "!", ".")) {_ + _}
    println(res2) // List(ha?, ha!, ha., heh?, heh!, heh., hmm?, hmm!, hmm.)

    println(sequenceA(List(1.some, 3.some))) // Some(List(1, 3))
    println(sequenceA(List(2.some, none, 1.some))) // None
    println(sequenceA(List(List(1,2), List(3,4)))) // List(List(1,3), List(1,4), List(2,3), List(2,4))
  }

  def sequenceA[F[_]: scalaz.Applicative, A](list: List[F[A]]): F[List[A]] = list match {
    case Nil => (Nil: List[A]).point[F]
    case x :: xs => (x |@| sequenceA(xs)) {_ :: _}
  }
}
