package day6

import scalaz._
import Scalaz._

object WriterObj {

  def main(args: Array[String]): Unit = {
    val res1 = (3, "Smallish gang.").applyLog(isBigGang)
    println(res1) // (false,Smallish gang.Compared gang size to 9.)

    val res2 = ("foo", 3).applyLog(_ => ("bar", 5))
    println(res2) // (bar, 8)

    val res3 = 3.set("something")
    println(res3) // WriterT((something,3))

    val res4 = "foo".set(false)
    println(res4) // WriterT((false,foo))

    val res5 = 3.tell
    println(res5) // WriterT((3,()))

    val res6 = MonadTell[Writer, String].point(3)
    println(res6) // WriterT((,3))

    val res7 = res6.run
    println(res7) // (,3)

    val res8 = logNumber(3)
    println(res8) // WriterT((List(Got number: 3),3))

    val res9 = multWithLog
    println(res9) // WriterT((List(Got number: 3, Got number: 5),15))

    val res10 = res9.run
    println(res10) // (List(Got number: 3, Got number: 5),15)
  }

  def isBigGang(x: Int): (Boolean, String) = (x > 9, "Compared gang size to 9.")

  implicit class PairOps[A, B: scalaz.Monoid](pair: (A, B)) {
    def applyLog[C](f: A => (C, B)): (C, B) = {
      val (x, log) = pair
      val (y, newLog) = f(x)
      (y, log |+| newLog) // same as (y, log mappend newLog)
    }
  }

  def logNumber(x: Int): Writer[List[String], Int] = x.set(List("Got number: " + x.shows))

  def multWithLog: Writer[List[String], Int] = for {
    a <- logNumber(3)
    b <- logNumber(5)
  } yield a * b
}
