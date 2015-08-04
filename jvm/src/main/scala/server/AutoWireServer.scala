package server

import upickle.default._
import shared.Api
import scala.concurrent.ExecutionContext

object AutowireServer extends autowire.Server[String, Reader, Writer] {
  def read[Result: Reader](p: String) = upickle.default.read[Result](p)
  def write[Result: Writer](r: Result) = upickle.default.write(r)

  def routes(implicit ex: ExecutionContext) = AutowireServer.route[Api](ApiImpl)
}
