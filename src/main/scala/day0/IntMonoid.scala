package day0

object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0

  def main(args: Array[String]): Unit = {
    def sum1(xs: List[Int]): Int = xs.foldLeft(mzero)(mappend)
    def sum2[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)

    val res1 = sum1(List(1,2,3,4))
    assert(res1 == 10)

    val res2 = sum2(List(1,2,3,4), IntMonoid)
    assert(res2 == 10)
  }
}
