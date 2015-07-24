import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport


object Main extends js.JSApp {

  @JSExport
  def main(): Unit = {
    val lib = new MyLibrary
    val res = lib.sq(2)
    println(res)
    val doc = dom.document
    val divInMainEl = doc.getElementById("main-el")

    divInMainEl.innerHTML = s"""
       |<div class="col-md-4"></div>
       |<div class="col-md-8">
       |  <p>From shared: $res</p>
       |</div>
     """.stripMargin

  }
}
