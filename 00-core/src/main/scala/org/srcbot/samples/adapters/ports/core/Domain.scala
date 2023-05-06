package org.srcbot.samples.adapters.ports.core

object Domain:

    def litersPerKm(litersPer100Km: Double): Double = litersPer100Km / 100

    def litersForDistance(distance: Double, litersPerKm: Double): Double = distance * litersPerKm

end Domain