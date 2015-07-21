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
    val doc = dom.document
    val divInMainEl = doc.getElementById("main-el").getElementsByClassName("text")(0)

    val parNode = doc.createElement("p")
    val textNode = doc.createTextNode(s"From shared: $res")
    parNode.appendChild(textNode)
    divInMainEl.appendChild(parNode)

  }
}
