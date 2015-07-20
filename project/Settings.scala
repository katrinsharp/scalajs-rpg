import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Settings {

  val name = "scalajs-rpg"

  object versions {
    val scala = "2.11.7"
    val version = "0.1-SNAPSHOT"
  }

  val jsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0"
  ))

}
