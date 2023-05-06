ThisBuild / organization := "org.srcbot.samples"
ThisBuild / scalaVersion := "3.2.2"

lazy val cli = (project in file("50-app-cli"))
  .settings(
    name := "calc-fp-sample-cli"
  ).aggregate(core, secondaryPort, primaryPort, secondaryAdapter, primaryAdapterCli)
  .dependsOn(core, secondaryPort, primaryPort, secondaryAdapter, primaryAdapterCli)
  .enablePlugins(JavaAppPackaging)

lazy val gui = (project in file("51-app-gui"))
  .settings(
    name := "calc-fp-sample-gui"
  ).aggregate(core, secondaryPort, primaryPort, secondaryAdapter, primaryAdapterGui)
  .dependsOn(core, secondaryPort, primaryPort, secondaryAdapter, primaryAdapterGui)
  .enablePlugins(JavaAppPackaging)

lazy val api = (project in file("52-app-api"))
  .settings(
    name := "calc-fp-sample-api",
    maintainer := "dev@nils-heinemann.de"
  ).aggregate(core, secondaryPort, primaryPort, secondaryAdapter, primaryAdapterApi)
  .dependsOn(core, secondaryPort, primaryPort, secondaryAdapter, primaryAdapterApi)
  .enablePlugins(DockerPlugin, JavaServerAppPackaging)

lazy val core = (project in file("00-core"))
  .settings(
    name := "calc-fp-sample-core"
  )

lazy val secondaryPort = (project in file("10-port-secondary"))
  .settings(
    name := "calc-fp-sample-secondary-port"
  )
  .dependsOn(core)

lazy val primaryPort = (project in file("20-port-primary"))
  .settings(
    name := "calc-fp-sample-primary-port"
  )
  .dependsOn(core, secondaryPort)

lazy val secondaryAdapter = (project in file("30-adapter-secondary"))
  .settings(
    name := "calc-fp-sample-secondary-adapter"
  )
  .dependsOn(secondaryPort)

lazy val primaryAdapterCli = (project in file("40-adapter-primary-cli"))
  .settings(
    name := "calc-fp-sample-primary-adapter-cli"
  )
  .dependsOn(secondaryAdapter, primaryPort)

lazy val primaryAdapterGui = (project in file("41-adapter-primary-gui"))
  .settings(
    name := "calc-fp-sample-primary-adapter-gui",
    libraryDependencies ++= Bundles.gui
  )
  .dependsOn(secondaryAdapter, primaryPort)

lazy val primaryAdapterApi = (project in file("42-adapter-primary-api"))
  .settings(
    name := "calc-fp-sample-primary-adapter-api",
    libraryDependencies ++= Bundles.api
  )
  .dependsOn(secondaryAdapter, primaryPort)
