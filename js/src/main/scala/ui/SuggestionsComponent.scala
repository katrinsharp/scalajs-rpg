package ui

import japgolly.scalajs.react.extra.OnUnmount
import japgolly.scalajs.react.vdom.all._
import japgolly.scalajs.react.{BackendScope, ReactComponentB}
import rx._
import shared.Api
//this is line that bring call() into scope
import autowire._
import shared.Suggestion

import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

object SuggestionsComponent {

  private val currentSuggestions = Var(Seq.empty[Suggestion])

  def suggestionsRx: Rx[Seq[Suggestion]] = currentSuggestions

  case class Props(text: Rx[String])
  case class State()

  private def format(s: String, txt: String) =
    if (txt.isEmpty)
      li(s)
    else
      s.toLowerCase.indexOf(txt.toLowerCase) match {
        case -1 => li(`class` := "suggestion", s)
        case i =>
          li(`class` := "suggestion", s.substring(0, i))(span(`class` := "highlight")(txt))(s.substring(i + txt.length, s.length))
      }

  class Backend(be: BackendScope[Props, Unit]) extends RxObserver(be) {
    def mounted(): Unit = {
      react(be.props.text, refreshSuggestions)
      observe(suggestionsRx)
    }

    def refreshSuggestions(text: String) = {
      if (!text.isEmpty)
        AjaxClient[Api].suggestions(text).call().foreach(r => currentSuggestions() = r)
      else
        currentSuggestions() = Seq.empty[Suggestion]
    }
  }

  val component = ReactComponentB[Props]("SuggestionsComponent")
    .stateless
    .backend(new Backend(_))
    .render((P, S, B) => {

      val nextText = P.text()

      val nextSuggestions = nextText match {
        case "" => List.empty[Suggestion]
        case t => currentSuggestions().filter(_.text.toLowerCase.contains(t.toLowerCase))
      }

      ul(nextSuggestions.map(s => format(s.text, nextText)))

    })
    .componentDidMount(_.backend.mounted())
    .configure(OnUnmount.install)
    .build

  def apply(text: Rx[String]) = component(Props(text))
}
