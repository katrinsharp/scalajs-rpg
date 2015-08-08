package server

import shared.Api
import scala.concurrent.ExecutionContext

object AutowireServer extends autowire.Server[String, upickle.default.Reader, upickle.default.Writer] {
  def read[Result: upickle.default.Reader](p: String) = upickle.default.read[Result](p)
  def write[Result: upickle.default.Writer](r: Result) = upickle.default.write(r)

  def routes(implicit ex: ExecutionContext) = AutowireServer.route[Api](ApiImpl)
}
