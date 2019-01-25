package day10

import scalaz._
import Scalaz._

object ReaderTOption extends KleisliInstances with KleisliFunctions {
  type ReaderTOption[A, B] = ReaderT[Option, A, B]
  def apply[A, B](f: A => Option[B]): ReaderTOption[A, B] = kleisli(f)
}
