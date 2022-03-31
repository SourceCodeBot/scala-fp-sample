package com.sample.adapters.ports

import core.Domain.*

object Language:
  import cats.Monad
  trait Calculators[F[_]: Monad]:
    def literPerKm(litersPer100Km: Double, distance: Double): F[LitersTotal]
    def litersForDistance(distance: Double, litersPerKm: LitersTotal): F[LitersTotal]