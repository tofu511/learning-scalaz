package day0

trait FoldLeft[F[_]] {
  def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
}

object FoldLeft {
  implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
    override def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}

object FoldLeftMain {
  def sum[M[_]: FoldLeft, A: Monoid](xs: M[A]): A= {
      val m = implicitly[Monoid[A]]
      val fl = implicitly[FoldLeft[M]]
      fl.foldLeft(xs, m.mzero, m.mappend)
  }

  def main(args: Array[String]): Unit = {

    val res = sum(List(1,2,3,4))
    assert(res == 10)

    val res2 = sum(List("a", "b", "c"))
    assert(res2 == "abc")
  }
}

