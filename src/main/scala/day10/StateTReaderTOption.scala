package day10

import scalaz._
import Scalaz._
import day10.ReaderTOption.ReaderTOption

object StateTReaderTOption extends StateTInstances with StateFunctions {

  type StateTReaderTOption[C, S, A] = StateT[({type l[X] = ReaderTOption[C, X]})#l, S, A]

  def apply[C, S, A](f: S => (S, A)) = new StateT[({type l[X] = ReaderTOption[C, X]})#l, S, A] {
    def apply(s: S) = f(s).point[({type l[X] = ReaderTOption[C, X]})#l]
  }

  def get[C, S]: StateTReaderTOption[C, S, S] = StateTReaderTOption { s => (s, s)}
  def put[C, S](s: S): StateTReaderTOption[C, S, Unit] = StateTReaderTOption { _ => (s, ())}

}
