package day8

import scalaz._
import Scalaz._

case class Prob[A](list: List[(A, Double)])

trait ProbInstances {
  def flatten[B](xs: Prob[Prob[B]]): Prob[B] = {
    def multall(innerxs: Prob[B], p: Double) =  {
      innerxs.list.map { case (x, r) => (x, p * r)}
    }
    Prob(xs.list.map { case (innerxs, p) => multall(innerxs, p)}.flatten)
  }

  implicit val probInstance: Functor[Prob] with Monad[Prob] = new Functor[Prob] with Monad[Prob] {
    def point[A](a: => A): Prob[A] = Prob((a, 1.0) :: Nil)
    def bind[A, B](fa: Prob[A])(f: A => Prob[B]): Prob[B] = flatten(map(fa)(f))
    override def map[A, B](fa: Prob[A])(f: A => B): Prob[B] = {
      Prob(fa.list.map { case (x, p) => (f(x), p)})
    }
  }
  implicit def probShow[A]: Show[Prob[A]] = Show.showA
}

case object Prob extends ProbInstances

object MakingMonad {
  def main(args: Array[String]): Unit = {
    val p1 = Prob((3, 0.5) :: (5, 0.25) :: (9, 0.25) :: Nil)
    val p2 = Prob((5, 0.3) :: (10, 0.2) :: (3, 0.7) :: Nil)
    val res1 = p1.map {-_}
    println(res1) // Prob(List((-3,0.5), (-5,0.25), (-9,0.25)))

    val res2 = p2.point[Prob]
    println(res2) // Prob(List((Prob(List((5,0.3), (10,0.2), (3,0.7))),1.0)))

    val res3 = for {
      p <- p1
      pp <- p2
    } yield p + pp
    println(res3) // Prob(List((8,0.15), (13,0.1), (6,0.35), (10,0.075), (15,0.05), (8,0.175), (14,0.075), (19,0.05), (12,0.175))

    val res4 = p1 >>= (x => p2 >>= (y => (x * y).point[Prob]))
    println(res4) // Prob(List((15,0.15), (30,0.1), (9,0.35), (25,0.075), (50,0.05), (15,0.175), (45,0.075), (90,0.05), (27,0.175)))

    val res5 = p1 map (x => p2 >>= (y => (x * y).point[Prob]))
    println(res5) // Prob(List((Prob(List((15,0.3), (30,0.2), (9,0.7))),0.5), (Prob(List((25,0.3), (50,0.2), (15,0.7))),0.25), (Prob(List((45,0.3), (90,0.2), (27,0.7))),0.25)))

    val res6 = p1 >>= (x => p2 map (y => (x * y).point[Prob]))
    println(res6) // Prob(List((Prob(List((15,1.0))),0.15), (Prob(List((30,1.0))),0.1), (Prob(List((9,1.0))),0.35), (Prob(List((25,1.0))),0.075), (Prob(List((50,1.0))),0.05), (Prob(List((15,1.0))),0.175), (Prob(List((45,1.0))),0.075), (Prob(List((90,1.0))),0.05), (Prob(List((27,1.0))),0.175)))
  }
}
