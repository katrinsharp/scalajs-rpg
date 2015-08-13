import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._

// Command for building a release
lazy val ReleaseJsCmd = Command.command("releaseJs") {
  state =>
    "crossJS/fullOptJS" ::
    "crossJS/webStage" ::
    state
}

lazy val root = project.in(file(".")).
  aggregate(jsProject, jvmProject).
  settings(
    version := Settings.version,
    publish := {},
    publishLocal := {}
  )

lazy val cross = crossProject.in(file(".")).
  settings(
    name := Settings.name,
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    persistLauncher in Compile := true,
    libraryDependencies ++= Settings.sharedDependencies.value
  ).
  jvmSettings(
    libraryDependencies ++= Settings.jvmDependencies.value
  ).
  jsSettings(
    libraryDependencies ++= Settings.jsDependencies.value,
    jsDependencies ++= Settings.jsScriptDependencies.value,
    pipelineStages := Seq(cssCompress),
    commands += ReleaseJsCmd
  )

lazy val jvmProject = cross.jvm
lazy val jsProject = cross.js.enablePlugins(SbtWeb)
