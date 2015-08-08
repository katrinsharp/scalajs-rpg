import japgolly.scalajs.react.React
import org.scalajs.dom
import ui.TodoListComponent
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

    val htmlFragment = div(`class` := "row",
      div(`class` := "col-md-4"),
      div(`class` := "col-md-8",
        p(`class` := "red", s"From shared and type safe: $res")))

    val todoEl = div(`class` := "col-md-10 col-md-offset-2", `id` := "todo")

    val htmlFragment2 = div(`class` := "row", todoEl)

    divInMainEl.appendChild(htmlFragment.render)
    divInMainEl.appendChild(htmlFragment2.render)

    React.render(TodoListComponent(), doc.getElementById("todo"))

  }
}
