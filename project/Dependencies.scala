import sbt._

object Dependencies {

  lazy val osName: String = System.getProperty("os.name") match {
    case n if n.startsWith("Linux") => "linux"
    case n if n.startsWith("Mac") => "mac"
    case n if n.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
  }

  object Versions {
    val scala = "3.1.1"
    val test = "3.2.11"
    val cats = "3.3.8"
  }

  val scalaTest =  "org.scalatest" %% "scalatest" % Versions.test % "test"
  val scalaWordTest =  "org.scalatest" %% "scalatest-wordspec" % Versions.test % "test"
  val scalaFunTest =  "org.scalatest" %% "scalatest-funspec" % Versions.test % "test"

  val catsEffects = "org.typelevel" %% "cats-effect" % Versions.cats
  val catsKernel = "org.typelevel" %% "cats-effect-kernel" % Versions.cats
  val catsStd = "org.typelevel" %% "cats-effect-std" % Versions.cats
  
  val cats: Seq[ModuleID] = Seq(
    catsEffects,
    catsKernel,
    catsStd
  )

  val bundled: Seq[ModuleID] = cats ++ Seq(
    "org.scalafx" %% "scalafx" % "16.0.0-R24"
    ) ++ Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName) :+ scalaFunTest

}
