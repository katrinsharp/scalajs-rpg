import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.Keys._

lazy val root = project.in(file(".")).
  aggregate(jsProject, jvmProject).
  settings(
    version := Settings.versions.version,
    publish := {},
    publishLocal := {}
  )

lazy val cross = crossProject.in(file(".")).
  settings(
    name := Settings.name,
    version := Settings.versions.version,
    scalaVersion := Settings.versions.scala,
    persistLauncher in Compile := true
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    libraryDependencies ++= Settings.jsDependencies.value
  )

lazy val jvmProject = cross.jvm
lazy val jsProject = cross.js
