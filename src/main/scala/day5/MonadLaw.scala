package day5

import scalaz._
import Scalaz._

object MonadLaw {

  def main(args: Array[String]): Unit = {
    (scalaz.Monad[Option].point(3) >>= { x => (x + 100).some }) assert_=== 3 |> { x => (x + 100).some}

    val res1 = 3 |> { x => (x + 100).some}
    println(res1) // Some(103)

    val res2 = 10 |> { x => List(x, -x)}
    println(res2) // List(10, -10)

    "move on up".some.flatMap { scalaz.Monad[Option].point(_)} assert_=== "move on up".some

  }
}
