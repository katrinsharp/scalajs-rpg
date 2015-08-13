package ui

import japgolly.scalajs.react.extra.OnUnmount
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import rx._
import autowire._
import shared.Api
import shared.Api.{Suggestion, Todo}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

object TodoListComponent {

  private val currentText = Var("")

  def text: Rx[String] = currentText

  private val currentTodos = Var(Seq.empty[Todo])

  def todosRx: Rx[Seq[Todo]] = currentTodos

  val suggestionList = SuggestionsComponent(text)

  case class Props()
  case class State(items: Seq[String] = Seq.empty[String], text: String = "")

  val TodoList = ReactComponentB[Seq[Todo]]("TodoList")
    .render(props => ul(props.map(li(`class` := "todo", _))))
    .build

  class Backend(be: BackendScope[Props, State]) extends RxObserver(be) {
    def onChange(e: ReactEventI) = {
      val text = e.currentTarget.value
      currentText() = text
      be.modState(_.copy(text = text))
    }

    def handleSubmit(e: ReactEventI) = {

      e.preventDefault()
      AjaxClient[Api].addTodo(text()).call()
      currentTodos() = currentTodos() :+ text()
      currentText() = ""
      be.modState(s => State(currentTodos(), currentText()))
    }

    def mounted(): Unit = {
      observe(todosRx)
      AjaxClient[Api].todos().call().foreach(r => currentTodos() = r)
    }
  }

  val component = ReactComponentB[Props]("TodoListComponent")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, S, B) =>
    div(
      h3("TODOS"),
      TodoList(currentTodos()),
      form(onSubmit ==> B.handleSubmit,
        input(onChange ==> B.onChange, value := S.text),
        button("ADD")
      ),
      h3("Suggestions"),
      suggestionList
    ))
    .componentDidMount(_.backend.mounted())
    .configure(OnUnmount.install)
    .build

  def apply() = component(Props())

}
