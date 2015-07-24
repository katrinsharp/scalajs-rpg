import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

object Main extends js.JSApp {

  @JSExport
  def main(): Unit = {
    val lib = new MyLibrary
    val res = lib.sq(2)
    println(res)
    val doc = dom.document
    val divInMainEl = doc.getElementById("main-el")

    divInMainEl.appendChild(div(`class` := "col-md-4").render)
    divInMainEl.appendChild(div(`class` := "col-md-8", p(`class` := "red", s"From shared and type safe: $res")).render)

  }
}
