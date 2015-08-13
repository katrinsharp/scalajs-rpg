import japgolly.scalajs.react.React
import org.scalajs.dom
import ui.TodoListComponent
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

object Main extends js.JSApp {

  @JSExport
  def main(): Unit = {
    val doc = dom.document
    val divInMainEl = doc.getElementById("main-el")

    val todoEl = div(`class` := "col-xs-4 col-xs-offset-4", `id` := "todo")

    val htmlFragment = div(`class` := "row", todoEl)

    divInMainEl.appendChild(htmlFragment.render)

    React.render(TodoListComponent(), doc.getElementById("todo"))

  }
}
