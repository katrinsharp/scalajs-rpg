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
    val scalatags = "0.5.2"
    val scalajsReact = "0.9.0"
  }

  val sharedDependencies = Def.setting(Seq(
    "org.webjars" % "font-awesome" % versions.fontAwesome % Provided,
    "org.webjars" % "bootstrap" % versions.bootstrap % Provided
  ))

  val jsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % versions.scalajs,
    "com.lihaoyi" %%% "scalatags" % versions.scalatags,
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact
  ))


  val jsScriptDependencies = Def.setting(Seq(
    "org.webjars" % "jquery" % versions.jQuery / "jquery.js",
    "org.webjars" % "bootstrap" % versions.bootstrap / "bootstrap.js" dependsOn "jquery.js",
    "org.webjars" % "react" % "0.12.2" / "react-with-addons.js" commonJSName "React"
  ))

}
