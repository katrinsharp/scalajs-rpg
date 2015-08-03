package ui

import org.scalajs.dom
import upickle.default._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

object AjaxClient extends autowire.Client[String, Reader, Writer]{
  override def doCall(req: Request) = {
    dom.ext.Ajax.post(
      url = "/api/" + req.path.mkString("/")//,
      //data = write(req.args)
    ).map(_.responseText)
  }

  def read[Result: Reader](p: String) = read[Result](p)
  def write[Result: Writer](r: Result) = write(r)
}
