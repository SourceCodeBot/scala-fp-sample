package com.sample.adapters.test

import com.sample.adapters.ports.Program
import org.scalatest.funspec.AnyFunSpec

class InterpretationTest extends AnyFunSpec:

  import com.sample.adapters.Interpretation.given
  import cats.effect.unsafe.implicits.global

  describe("basic test") {
    it("shoud return 1 for 100 and 100") {
      assert(Program.litersPerKm(100, 100).unsafeRunSync() == 1)
    }
  }
