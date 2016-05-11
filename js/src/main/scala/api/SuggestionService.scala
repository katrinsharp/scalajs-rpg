package api

import shared.{Api, Suggestion}
import ui.AjaxClient
import autowire._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.{JSExport, RawJSType}

@JSExport("SuggestionService")
class SuggestionService {

  @JSExport
  def suggestionsFor(text: String) =
    AjaxClient[Api].suggestions(text).call().toJSPromise
}
