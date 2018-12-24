package day1

import scalaz._
import Scalaz._

object Enum {

  def main(args: Array[String]): Unit = {
    println('a' to 'e') // NumericRange(a, b, c, d, e)
    println('a' |-> 'e') // List(a, b, c, d, e)
    println(3 |=> 5) // scalaz.EphemeralStreamFunctions$$anon$4@327514f
    println('B'.succ) // C
    println(10.succ) // 11
  }
}
