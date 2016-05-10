package shared

import scala.scalajs.js.annotation.{JSExport, JSExportAll}

trait Api {

  def suggestions(s: String = ""): Seq[Suggestion]
  def todos(): Seq[Todo]
  def addTodo(todo: Todo): Unit
}

@JSExport
@JSExportAll
case class Suggestion(text: String)

case class Todo(text: String)
