package server

import shared.Api
import shared.Api.Todo

object ApiImpl extends Api {

  //TODO: should not be hardcoded
  private val suggestionsList = Seq(
    "Wake up early tomorrow",
    "Walk the dog",
    "Talk to kids",
    "Do more Scala")

  def suggestions(s: String) =
    suggestionsList.filter(_.toLowerCase.contains(s.toLowerCase))

  def todos(): Seq[Todo] =
    TodoRepo.getTodos()

  def addTodo(todo: Todo) =
    TodoRepo.addTodo(todo)
}