package day8

import scalaz._
import Scalaz._

object MonadicFunctionComposition {

  def main(args: Array[String]): Unit = {
    val f = Kleisli { x: Int => (x + 1).some }
    val g = Kleisli { x: Int => (x * 100).some }
    val ff = Kleisli { x: Int => List(x + 1)}
    val gg = Kleisli { x: Int => List(x * 100)}

    val res1 = 4.some >>= (f <=< g)
    println(res1) // Some(401)

    val res2 = 4.some flatMap (f compose g)
    println(res2) // Some(401)

    val res3 = 4.some flatMap (f >=> g)
    println(res3) // Some(500)

    val res4 = 4.some flatMap (f andThen g)
    println(res4) // Some(500)

    val res5 = List(1,2,3) >>= (ff compose gg)
    println(res5) // List(101, 201, 301)

    val res6 = List(1,2,3) >>= (ff andThen gg)
    println(res6) // List(200, 300, 400)
  }
}
