package shared

import shared.Api.{Todo, Suggestion}

trait Api {

  def suggestions(s: String = ""): Seq[Suggestion]
  def todos(): Seq[Todo]
  def addTodo(todo: Todo): Unit
}

object Api {
  type Suggestion = String
  type Todo = String
}
