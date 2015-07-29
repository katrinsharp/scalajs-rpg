package ui

import japgolly.scalajs.react.{React, ReactEventI, BackendScope, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^._

object TodoListComponent {


    val TodoList = ReactComponentB[List[String]]("TodoList")
      .render(props => {

      def createItem(itemText: String) = <.li(itemText)
      <.ul(props map createItem)
    })
      .build

    case class State(items: List[String], text: String)

    class Backend(be: BackendScope[Unit, State]) {
      def onChange(e: ReactEventI) =
        be.modState(_.copy(text = e.target.value))

      def handleSubmit(e: ReactEventI) = {
        e.preventDefault()
        be.modState(s => State(s.items :+ s.text, ""))
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
          <.button("Add #", S.items.length + 1)
        )
      )
      ).buildU

  }
