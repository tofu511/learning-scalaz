package day5

import day5.Pole.Birds
import scalaz._
import Scalaz._

case class Pole(left: Birds, right: Birds) {
  def landLeft(n: Birds): Option[Pole] = {
    if (math.abs((left + n) - right) < 4) copy(left = left + n).some
    else none
  }

  def landRight(n: Birds): Option[Pole] = {
    if (math.abs(left - (right + n)) < 4) copy(right = right + n).some
    else none
  }

  def banana: Option[Pole] = none
}

object Pole {
  type Birds = Int

  def main(args: Array[String]): Unit = {
    println(Pole(0,0).landLeft(1)) //Some(Pole(1,0))
    println(Pole(3,3).landRight(10)) // None

    val res1 = Pole(0,0).landLeft(2).flatMap(_.landRight(2))
    println(res1) // Some(Pole(2,2))

    val res2 = (none: Option[Pole]).flatMap(_.landLeft(2))
    println(res2) // None

    val res3 = scalaz.Monad[Option].point(Pole(0,0)).flatMap {_.landLeft(2)}.flatMap{_.landRight(3)}.flatMap{_.landLeft(1)}
    println(res3) // Some(Pole(3,3))

    // >>= は flatMap のエイリアス
    val res4 = scalaz.Monad[Option].point(Pole(0,0)) >>= {_.landLeft(2)} >>= {_.landRight(3)} >>= {_.landLeft(1)}
    println(res4) // Some(Pole(3,3))

    val res5 = Pole(0,0).landLeft(2).map(_.landRight(3))
    println(res5) // Some(Some(Pole(2,3)))  // map を使うとSome(Some(...))のようにファンクターが2重になってしまう

    val res6 = scalaz.Monad[Option].point(Pole(0,0)) >>= (_.landLeft(2)) >>= (_.banana) >>= (_.landRight(4))
    println(res6) // None

    val res7 = (none: Option[Int]) >> 3.some
    println(res7) // None

    val res8 = 4.some >> 3.some
    println(res8) // Some(3)

    val res9 = 2.some >> 5.some
    println(res9) // Some(5)

    val res10 = for {
      x <- 3.some
      y <- "!".some
    } yield x.shows + y
    println(res10) // Some(3!)

    val res11 = for {
      start <- scalaz.Monad[Option].point(Pole(0,0))
      first <- start.landLeft(2)
      second <- first.landRight(2)
      third <- second.landLeft(2)
    } yield third
    println(res11) // Some(Pole(4,2))

    val res12 = for {
      x :: xs <- "hello".toList.some
    } yield x
    println(res12) // Some(h)

    val res13 = for {
      x :: xs <- "".toList.some
    } yield x
    println(res13) // None
  }
}

