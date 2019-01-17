package day8

import scalaz._
import Scalaz._

sealed trait Coin
case object Heads extends Coin
case object Tails extends Coin

object Coin {
  implicit val coinEqual: Equal[Coin] = Equal.equalA

  def coin: Prob[Coin] = Prob(Heads -> 0.5 :: Tails -> 0.5 :: Nil)
  def loadedCoin: Prob[Coin] = Prob(Heads -> 0.1 :: Tails -> 0.9 :: Nil)

  def flipThree: Prob[Boolean] = for {
    a <- coin
    b <- coin
    c <- loadedCoin
  } yield { List(a, b, c) all {_ === Tails }}
}

