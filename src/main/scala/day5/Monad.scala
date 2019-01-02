package day5

import scalaz._
import Scalaz._

object Monad {

  def main(args: Array[String]): Unit = {

    println(3.some.flatMap{x => (x + 1).some}) // Some(4)
    println((none: Option[Int]).flatMap(x => (x + 1).some)) // None

    println(scalaz.Monad[Option].point("what")) //Some(what)

  }
}
