package server

import shared.{Api, Suggestion, Todo}

object ApiImpl extends Api {

  //TODO: should not be hardcoded
  private val suggestionsList = Seq(
    Suggestion("Hi from server"),
    Suggestion("Wake up early tomorrow"),
    Suggestion("Walk the dog"),
    Suggestion("Talk to kids"),
    Suggestion("Do more Scala"),
    Suggestion("Have fun at ScalaDays"))

  def suggestions(s: String) =
    suggestionsList.filter(_.text.toLowerCase.contains(s.toLowerCase))

  def todos(): Seq[Todo] =
    TodoRepo.getTodos()

  def addTodo(todo: Todo) =
    TodoRepo.addTodo(todo)
}