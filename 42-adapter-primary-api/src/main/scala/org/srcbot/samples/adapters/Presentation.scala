package org.srcbot.samples.adapters

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContext
import akka.stream.SystemMaterializer
import akka.stream.Materializer
import scala.util.Failure
import scala.util.Success
import org.srcbot.samples.adapters.ports.Program
import org.srcbot.samples.adapters.ports.Language.FormState
import org.srcbot.samples.adapters.Interpretation

object Presentation:

    lazy val route =
        encodeResponse(
            pathPrefix("calculate") {
                concat(
                    path("perkm") {
                        concat(
                            get {
                                parameter("liters".as[Double]) { (liters) =>
                                    import Interpretation.given
                                    Program.setLiters(liters)
                                    complete(Program.calculatePerKm.toString())
                                }
                            },
                            post {
                                formFields("liters".as[Double]) { (liters) =>
                                    import Interpretation.given
                                    Program.setLiters(liters)
                                    complete(Program.calculatePerKm.toString())
                                }
                            }
                        )
                    },
                    path("total") {
                        concat(
                            get {
                                parameters("liters".as[Double], "distance".as[Double]) { (liters, distance) =>
                                    import Interpretation.given
                                    Program.setLiters(liters)
                                    Program.setDistance(distance)
                                    complete(Program.calculateLitersForDistance.toString())
                                }
                            },
                            post {
                                formFields("liters".as[Double], "distance".as[Double]) { (liters, distance) =>
                                    import Interpretation.given
                                    Program.setLiters(liters)
                                    Program.setDistance(distance)
                                    complete(Program.calculateLitersForDistance.toString())
                                }
                            }
                        )
                    }
                )
            }
        )

    def start(): Unit = 
        val system = ActorSystem.create(Behaviors.empty, "calc-fp-api")
        import system.executionContext
        given Materializer = SystemMaterializer(system).materializer
        
        val http = Http(system.classicSystem)
        
        http
            .newServerAt("0.0.0.0", 8080)
            .bindFlow(route)
            .onComplete {
                case Failure(exception) =>
                    system.log.error("can't bind at 0.0.0.0:8080", exception)
                case Success(value) => system.log.info("bind at 0.0.0.0:8080")
            }
        system.whenTerminated

end Presentation