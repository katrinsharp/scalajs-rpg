import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport


@JSExport
object Main extends js.JSApp{

  @JSExport
  def main(): Unit = {
    val lib = new MyLibrary
    val res = lib.sq(2)
    println(res)
    val target = dom.document.getElementById("main-el")
    target.textContent = s"From shared: $res"
  }
}
