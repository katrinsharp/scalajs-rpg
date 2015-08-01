package ui

import japgolly.scalajs.react.{React, ReactEventI, BackendScope, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._
import rx._

object TodoListComponent {

  private val currentText = Var("")
  def text: Rx[String] = currentText

  val suggestionList = SuggestionsComponent(text)


  val TodoList = ReactComponentB[List[String]]("TodoList")
    .render(props => {

    def createItem(itemText: String) = <.li(itemText)
    <.ul(props map createItem)
  })
    .build

  case class State(items: List[String], text: String)

  class Backend(be: BackendScope[Unit, State]) {
    def onChange(e: ReactEventI) = {
      val text = e.currentTarget.value
      be.modState(_.copy(text = text))
      currentText() = text
    }

    def handleSubmit(e: ReactEventI) = {
      e.preventDefault()
      be.modState(s => State(s.items :+ s.text, ""))
      currentText() = ""
    }
  }

  val component = ReactComponentB[Unit]("TodoListComponent")
    .initialState(State(List.empty[String], ""))
    .backend(new Backend(_))
    .render((_, S, B) =>
    <.div(
      <.h3("TODO"),
      TodoList(S.items),
      <.form(^.onSubmit ==> B.handleSubmit,
        <.input(^.onChange ==> B.onChange, ^.value := S.text),
        <.button("Add")
      ),
      <.h3("Possible suggestions"),
      suggestionList
    )
    ).buildU

}
