object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int): Int = a + b
  def mzero: Int = 0

  def main(args: Array[String]): Unit = {
    def sum(xs: List[Int]): Int = xs.foldLeft(mzero)(mappend)

    val res = sum(List(1,2,3,4))
    assert(res == 10)
  }
}
