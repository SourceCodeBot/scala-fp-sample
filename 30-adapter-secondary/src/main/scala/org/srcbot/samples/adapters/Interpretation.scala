package org.srcbot.samples.adapters

import org.srcbot.samples.adapters.ports.Language.FormState

object Interpretation:
    case class State(liters: Double, distance: Double):
        def tuple: (Double, Double) = (liters, distance)
    end State
    object State:
        def empty = State(0.0, 0.0)
    end State

    given FormState with
        var state = State.empty
        def getLitersPer100Km: Double = state.liters
        def getDistance: Double = state.distance
        def setLitersPer100Km = (liters) =>
            state = state.copy(liters)
        def setDistance = (distance) => 
            state = state.copy(distance = distance)
        def getState: (Double, Double) = state.tuple

end Interpretation