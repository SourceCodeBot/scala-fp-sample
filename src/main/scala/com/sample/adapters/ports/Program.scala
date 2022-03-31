package com.sample.adapters.ports

import Language.*
import core.Domain.*

object Program:

  import cats.Monad

  def litersPerKm[F[_]: Calculators: Monad](litersPer100Km: Double, distance: Double): F[LitersTotal] =
    val calculator = summon[Calculators[F]]
    calculator.literPerKm(litersPer100Km, distance)
  
  def litersForDistance[F[_]: Calculators : Monad](distance: Double, litersPerKm: LitersTotal): F[Double] =
    val calculator = summon[Calculators[F]]
    calculator.litersForDistance(distance, litersPerKm)