package org.srcbot.samples.adapters.ports

object Language:

    trait FormState:
        def getLitersPer100Km: Double
        def getDistance: Double
        def setLitersPer100Km: Double => Unit
        def setDistance: Double => Unit
        def getState: (Double, Double)
    end FormState

end Language