import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {

  val name = "scalajs-rpg"
  val version = "0.1-SNAPSHOT"

  object versions {
    val scala = "2.11.7"
    val jQuery = "1.11.1"
    val bootstrap = "3.3.2"
    val fontAwesome = "4.3.0-1"
    val scalajs = "0.8.0"
    val scalarx = "0.2.8"
    val scalatags = "0.5.2"
    val scalajsReact = "0.9.0"
    val akka = "2.3.12"
    val akkaHttp = "1.0"
    val logBack = "1.1.2"
    val autowire = "0.2.5"
    val upickle = "0.3.4"
    val react = "0.12.2"
  }

  val sharedDependencies = Def.setting(Seq(
    "org.webjars" % "font-awesome" % versions.fontAwesome % Provided,
    "org.webjars" % "bootstrap" % versions.bootstrap % Provided,
    "com.lihaoyi" %%% "autowire" % versions.autowire,
    "com.lihaoyi" %%% "upickle" % versions.upickle,
    "org.scala-js" %% "scalajs-stubs" % scalaJSVersion % Provided
  ))

  val jsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % versions.scalajs,
    "com.lihaoyi" %%% "scalatags" % versions.scalatags,
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
    "com.lihaoyi" %%% "scalarx" % versions.scalarx
  ))


  val jsScriptDependencies = Def.setting(Seq(
    "org.webjars" % "jquery" % versions.jQuery / "jquery.js" minified "jquery.min.js",
    "org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" minified "bootstrap.min.js" dependsOn "jquery.js",
    "org.webjars" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React"
  ))

  val jvmDependencies = Def.setting(Seq(
    "com.typesafe.akka" %% "akka-actor" % versions.akka,
    "com.typesafe.akka" %% "akka-http-experimental" % versions.akkaHttp,
    "com.typesafe.akka" %% "akka-slf4j" % versions.akka,
    "ch.qos.logback" % "logback-classic" % versions.logBack
  ))

}
