
lazy val app = (project in file(".")).settings(
  name := "sample-ui-app",
  scalacOptions := Seq(
    "-indent"
  ),
  scalaVersion := Dependencies.Versions.scala,
  ThisBuild / version := "1",
  libraryDependencies ++= Dependencies.bundled
)
