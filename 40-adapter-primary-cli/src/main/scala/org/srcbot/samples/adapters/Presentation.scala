package org.srcbot.samples.adapters

import org.srcbot.samples.adapters.Interpretation.given
import scala.io.StdIn
import org.srcbot.samples.adapters.ports.Program

object Presentation:

    def start(): Unit =
        var runApp = true
        while (runApp)
            printMenu
            StdIn.readLine().toIntOption.getOrElse(0) match
                case 1 => queryLiters.map(Program.setLiters)
                case 2 => queryDistance.map(Program.setDistance)
                case 3 => println(s"You consume ${Program.calculatePerKm}l per KM!")
                case 4 => println(s"You consumed ${Program.calculateLitersForDistance}l on your track!")
                case 5 => printState
                case _ => runApp = false
        println("Exit App . . .")

    private def queryLiters: Option[Double] = 
        StdIn.readLine("How many liters per 100km?").toDoubleOption

    private def queryDistance: Option[Double] = 
        StdIn.readLine("How many km was your track?").toDoubleOption

    private def printMenu: Unit =
        println("> What would you do?")
        println("> (1) Set liter/100km")
        println("> (2) Set distance")
        println("> (3) calculate liters per km")
        println("> (4) calcualte liters of the tour")
        println("> (5) debug (print current state)")

    private def printState: Unit = 
        println(s"l/100km=${Program.getLiters}")
        println(s"km=${Program.getDistance}")
        

end Presentation