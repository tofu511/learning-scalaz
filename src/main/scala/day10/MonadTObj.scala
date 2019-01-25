package day10

import scalaz._
import Scalaz._
import day10.ReaderTOption.ReaderTOption

object MonadTObj {
  def main(args: Array[String]): Unit = {
    val res1 = localExample("Fred")
    println(res1) // (First, I am Fred,Second, I am Freddy,Third, I am Fred)

    val goodConfig = Map(
      "host" -> "example.com",
      "user" -> "tofu511",
      "password" -> "***"
    )

    val res2 = setupConnection(goodConfig)
    println(res2) // Some((example.com,tofu511,***))

    val badConfig = Map(
      "host" -> "example.com",
      "user" -> "tofu511"
    )

    val res3 = setupConnection(badConfig)
    println(res3) // None
  }

  def myName(step: String): Reader[String, String] = Reader { step + ", I am " + _}

  def localExample: Reader[String, (String, String, String)] = for {
    a <- myName("First")
    b <- myName("Second") >=> Reader { _ + "dy" } // `>=>` is alias of `andThen`
    c <- myName("Third")
  } yield  (a, b, c)

  def configure(key: String): ReaderTOption[Map[String, String], String] = ReaderTOption[Map[String, String], String] {_.get(key)}

  def setupConnection: ReaderT[Option, Map[String, String], (String, String, String)] = for {
    host <- configure("host")
    user <- configure("user")
    password <- configure("password")
  } yield (host, user, password)

}
