package day0

object FoldLeftList {
  def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)

  implicit val multiMonoid: Monoid[Int] = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 * a2
    override def mzero: Int = 1
  }

  def main(args: Array[String]): Unit = {
    def sum[A: Monoid](xs: List[A]): A = {
      val m = implicitly[Monoid[A]]
      FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
    }

    val intRes = sum(List(1,2,3,4))(IntMonoid)
    assert(intRes == 10)

    val stringRes = sum(List("a", "b", "c"))
    assert(stringRes == "abc")

    val multiRes = sum(List(1,2,3,4))(multiMonoid)
    assert(multiRes == 24)
  }
}

