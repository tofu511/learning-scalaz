package day7

import scalaz._
import Scalaz._

object ValidationObj {

  def main(args: Array[String]): Unit = {
    val res1 = "event 1 ok".success[String]
    println(res1) // Success(event 1 ok)

    val res2 = "event 1 ok".success[Int]
    println(res2) // Success(event 1 ok)

    val res3 = "event 1 failed!".failure[String]
    println(res3) // Failure(event 1 failed!)

    val res4 = ("event 1 ok".success[String] |@| "event 2 failed!".failure[String] |@| "event 3 failed!".failure[String]) {_ + _ + _}
    // \/と違いValidationは全イベントを検証する
    println(res4) // Failure(event 2 failed!event 3 failed!)

    val res4a = ("event 1 ok".success[String] |@| "event 2 ok".success[String] |@| "event 3 ok".success[String]) {_ + _ + _}
    println(res4a) // Success(event 1 okevent 2 okevent 3 ok)

    val res5 = 1.wrapNel  // Nel = NonEmptyList
    println(res5) // NonEmptyList(1)

    val res6 = "event 1 ok".successNel[String]
    println(res6) // Success(event 1 ok)

    val res7 = "event 1 failed!".failureNel[String]
    println(res7) // Failure(NonEmptyList(event 1 failed!))

    val res8 = ("event 1 ok".successNel[String] |@| "event 2 failed!".failureNel[String] |@| "event 3 failed!".failureNel[String]) {_ + _ + _}
    println(res8) // Failure(NonEmptyList(event 2 failed!, event 3 failed!))

    val res9 = ("event 1 ok".successNel[String] |@| "event 2 ok".successNel[String] |@| "event 3 ok".successNel[String]) {_ + _ + _}
    println(res9) // Success(event 1 okevent 2 okevent 3 ok)
  }
}
