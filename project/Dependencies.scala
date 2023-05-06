import sbt._

object Dependencies {
    val AkkaVersion = "2.7.0"
    val AkkaHttpVersion = "10.5.1"
    val scalaFx = "org.scalafx" %% "scalafx" % "20.0.0-R31"

    lazy val osName = System.getProperty("os.name") match {
        case n if n.startsWith("Linux") => "linux"
        case n if n.startsWith("Mac") => 
            if (System.getProperty("os.arch").contentEquals("aarch64")) {
                "mac-aarch64"
            } else {
                "mac"
            }
        case n if n.startsWith("Windows") => "win"
        case t => throw new Exception(s"Unknown platform! '$t'")
    }

    val fx = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "20" classifier osName)

    val actors = "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
    val stream = "com.typesafe.akka" %% "akka-stream" % AkkaVersion
    val http = "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
}