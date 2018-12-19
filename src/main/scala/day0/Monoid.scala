package day0

trait Monoid[A] {
  def mappend(a1: A, a2: A): A
  def mzero: A
}
