package server

import shared.Todo


object TodoRepo {

  private var todosList = Seq.empty[Todo]

  def getTodos(): Seq[Todo] =
    todosList

  def addTodo(todo: Todo) =
    todosList = todosList :+ todo
}
