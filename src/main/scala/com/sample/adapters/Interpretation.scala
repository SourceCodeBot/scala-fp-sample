package com.sample.adapters

import ports.*
import Language.*
import core.Domain.*

object Interpretation:

  import cats.effect.IO

  given Calculators[IO] with
    def literPerKm(litersPer100Km: Double, distance: Double) = IO {
      litersPer100Km / 100
    }

    def litersForDistance(distance: Double, litersPerKm: LitersTotal) = IO {
      distance * litersPerKm
    }
