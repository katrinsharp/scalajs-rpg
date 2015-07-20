import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

name := "scalajs-real-practical-guide"

lazy val root = project.in(file(".")).
  aggregate(jsProject, jvmProject).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val cross = crossProject.in(file(".")).
  settings(
    name := "scalajs-rpg",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.7"
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    // Add JS-specific settings here
  )

lazy val jvmProject = cross.jvm
lazy val jsProject = cross.js
