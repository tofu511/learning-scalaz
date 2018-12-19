package day0

object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0

  def main(args: Array[String]): Unit = {
    def sum1(xs: List[Int]): Int = xs.foldLeft(mzero)(mappend)
    def sum2[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

    implicit val intMonoid = IntMonoid
    def sum3[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

    def sum4[A: Monoid](xs: List[A]): A = {
      val m = implicitly[Monoid[A]]
      xs.foldLeft(m.mzero)(m.mappend)
    }

    val res = Monoid.sum(List("a", "b", "c"))
    assert(res == "abc")

    val res1 = sum1(List(1,2,3,4))
    assert(res1 == 10)

    val res2 = sum2(List(1,2,3,4), IntMonoid)
    assert(res2 == 10)

    val res3 = sum3(List(1,2,3,4))
    assert(res3 == 10)

    val res4 = sum4(List(1,2,3,4))
    assert(res4 == 10)
  }
}
