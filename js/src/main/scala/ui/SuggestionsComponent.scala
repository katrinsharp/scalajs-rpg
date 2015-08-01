package ui

import japgolly.scalajs.react.extra.OnUnmount
import japgolly.scalajs.react.vdom.all.backgroundColor
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, ReactComponentB}
import rx._

object SuggestionsComponent {

  type Suggestion = String

  //TODO: should come from server
  val suggestions = Seq("Wake up early tomorrow", "Walk the dog", "Talk to kids", "Do more Scala")


  case class Props(text: Rx[String])

  case class State(suggestions: Seq[Suggestion] = suggestions, txt: String = "")

  private def format(s: String, txt: String) =
    if (txt.isEmpty)
      <.li(s)
    else
      s.toLowerCase.indexOf(txt.toLowerCase) match {
        case -1 => <.li(s)
        case i =>
          <.li(s.substring(0, i))(<.span(^.cls := "highlight")(txt))(s.substring(i + txt.length, s.length))
      }

  def apply(text: Rx[String]) = {
    component(Props(text))
  }

  class Backend(t: BackendScope[Props, State]) extends RxObserver(t) {
    def mounted(): Unit = observe(t.props.text)
  }

  val component = ReactComponentB[Props]("SuggestionsComponent")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, S, B) => {

    val nextText = P.text()

    val nextSuggestions = nextText match {
      case "" => suggestions
      case t => suggestions.filter(_.toLowerCase.contains(t.toLowerCase))
    }

    <.ul(nextSuggestions.map(s => format(s, nextText)))

  })
    .componentDidMount(_.backend.mounted())
    .configure(OnUnmount.install)
    .build

}
