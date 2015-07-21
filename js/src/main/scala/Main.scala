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
    val mainEl = doc.getElementById("main-el")

    val parNode = doc.createElement("p")
    val textNode = doc.createTextNode(s"From shared: $res")
    parNode.appendChild(textNode)
    mainEl.appendChild(parNode)
    
  }
}
