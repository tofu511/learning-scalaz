package day7

import scalaz._
import Scalaz._

object EitherObj {

  def main(args: Array[String]): Unit = {

    val res1 = 1.right[String]
    println(res1) // \/-(1)

    val res2 = "hoge".right
    println(res2) // \/-(hoge)

    val res3 = "hoge".right[Double]
    println(res3) // \/-(hoge)

    val res4 = "error".left[Int]
    println(res4) // -\/(error)

    val res5 = 10.left[String]
    println(res5) // -\/(10)

    // Scala標準のEitherは単体ではモナドでは無い
    // Left[String, Int]("boom").flatMap{ x => Right[String, Int](x + 1) }

    val res6 = Left[String, Int]("boom").right.flatMap {x => Right[String, Int](x + 1) }
    println(res6) // Left(boom)

    val res7 = Right[String, Int](1).right.flatMap { x => Right[String, Int](x + 1) }
    println(res7) // Right(2)

    val res8 = "boom".left[Int].flatMap { x => (x + 1).right }
    println(res8) // -\/(boom)

    val res9 = 10.right[String].flatMap { x => (x + 1).right }
    println(res9) // \/-(11)

    val res10 = for {
      e1 <- "event 1 ok".right
      e2 <- "event 2 failed!".left[String]
      e3 <- "event 3 failed!".left[String]
    } yield e1 |+| e2 |+| e3
    println(res10) // -\/(event 2 failed!)

    val res11 = for {
      e1 <- "Ok1 ".right
      e2 <- "Ok2 ".right
      e3 <- "Ok3 ".right
    } yield e1 |+| e2 |+| e3
    println(res11) // \/-(Ok1 Ok2 Ok3 )

    val res12 = "Ok".right.isRight
    println(res12) // true

    val res13 = "Ok".right.isLeft
    println(res13) // false

    val res14 = "Ok".right | "Ng" // | は getOrElseのエイリアス
    println(res14) // Ok

    val res15 = "Ok".left[String] | "something bad!"
    println(res15) // something bad!

    val res16 = "ok".right.map(_ + "!!")
    println(res16) // \/-(ok!!)
  }
}
