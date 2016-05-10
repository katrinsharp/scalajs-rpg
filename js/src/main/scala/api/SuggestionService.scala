package api

import shared.Api
import ui.AjaxClient
import autowire._
import shared.Suggestion

import scala.scalajs.js.annotation.{JSExport, JSExportAll, RawJSType}

// import scala.concurrent.Await
// import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

@RawJSType
trait SuggestionCallback {
  def onSuccess(result: Seq[Suggestion]): Unit
}

@JSExport
@JSExportAll
class SuggestionService {

  @JSExport
  def suggestFor(text: String, callback: SuggestionCallback): Unit = {
    AjaxClient[Api].suggestions(text).call().map(callback.onSuccess(_))
  }

}

//var callback = {onSuccess: function(result){console.log(result.toString())}}
