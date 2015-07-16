package com.twitter.algebird.macros

import org.scalatest._
import com.twitter.algebird._
import com.twitter.algebird.macros.caseclass._

class CaseClassMacrosTest extends WordSpec with Matchers {

  case class Foo(a: Int, b: Int)
  case class DoubleFoo(a: Double, b: Double)

  "SemigroupMacro" should {
    "work" in {
      val sg = implicitly[Semigroup[Foo]]
      val a = Foo(1, 2)
      val b = Foo(4, 3)

      assert(sg.plus(a, b) == Foo(5, 5))
    }
  }

  "MonoidMacro" should {
    "work" in {
      val m = implicitly[Monoid[Foo]]
      val a = Foo(1, 2)
      val b = Foo(4, 3)

      assert(m.plus(a, b) == Foo(5, 5))
      assert(m.zero == Foo(0, 0))
    }
  }

  "GroupMacro" should {
    "work" in {
      val g = implicitly[Group[Foo]]
      val a = Foo(1, 2)
      val b = Foo(4, 3)

      assert(g.plus(a, b) == Foo(5, 5))
      assert(g.zero == Foo(0, 0))
      assert(g.negate(a) == Foo(-1, -2))
    }
  }

  "RingMacro" should {
    "work" in {
      val r = implicitly[Ring[Foo]]
      val a = Foo(1, 2)
      val b = Foo(4, 3)

      assert(r.plus(a, b) == Foo(5, 5))
      assert(r.zero == Foo(0, 0))
      assert(r.negate(a) == Foo(-1, -2))
      assert(r.one == Foo(1, 1))
      assert(r.times(a, b) == Foo(4, 6))
    }
  }
}
