package day7

import scalaz._
import Scalaz._

object StateObj {
  type Stack = List[Int]

//  def pop(stack: Stack): (Int, Stack) = stack match {
//    case x :: xs => (x, xs)
//  }
//
//  def push(a: Int, stack: Stack): (Unit, Stack) = ((), a :: stack)
//
//  def stackManip(stack: Stack): (Int, Stack) = {
//    val (_, newStack1) = push(3, stack)
//    val (a, newStack2) = pop(newStack1)
//    pop(newStack2)
//  }

  val pop: State[Stack, Int] = State[Stack, Int] {
    case x :: xs => (xs, x)
  }

  def push(a: Int): State[Stack, Unit] = State[Stack, Unit] {
    case xs => (a :: xs, ())
  }

  def stackManip: State[Stack, Int] = for {
    // モナディックに連鎖できる！
    _ <- push(3)
    a <- pop
    b <- pop
  } yield b

  def main(args: Array[String]): Unit = {

    val res1 = stackManip(List(5, 8, 2, 1))
    println(res1) // (List(8, 2, 1),5)

    val res2 = State[List[Int], Int] { case x :: xs => (xs, x) }
    println(res2) // scalaz.package$State$$anon$3@531be3c5
  }
}
