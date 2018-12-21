package day0

trait MonoidOp[A] {
  val F: Monoid[A]
  val value: A
  def |+|(a2: A) = F.mappend(value, a2)
}

object MonoidOpMain {
  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    override val F: Monoid[A] = implicitly[Monoid[A]]
    override val value: A = a
  }

  def main(args: Array[String]): Unit = {
    val res1 = 3 |+| 4
    assert(res1 == 7)

    val res2 = toMonoidOp(3).|+|(4)
    assert(res2 == 7)

    val res3 = "a" |+| "b"
    assert(res3 == "ab")

    val res4 = toMonoidOp("a").|+|("b")
    assert(res4 == "ab")
  }
}
