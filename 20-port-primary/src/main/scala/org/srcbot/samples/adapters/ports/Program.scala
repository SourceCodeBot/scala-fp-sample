package org.srcbot.samples.adapters.ports

import org.srcbot.samples.adapters.ports.Language.FormState
import org.srcbot.samples.adapters.ports.core.Domain._

object Program:

    def calculateLitersForDistance: FormState ?=> Double = litersForDistance(summon[FormState].getDistance, calculatePerKm)
    def calculatePerKm: FormState ?=> Double = litersPerKm(summon[FormState].getLitersPer100Km)

    def setLiters: FormState ?=> Double => Unit = summon[FormState].setLitersPer100Km
    def setDistance: FormState ?=> Double => Unit = summon[FormState].setDistance

    def getLiters: FormState ?=> Double = summon[FormState].getLitersPer100Km
    def getDistance: FormState ?=> Double = summon[FormState].getDistance

end Program