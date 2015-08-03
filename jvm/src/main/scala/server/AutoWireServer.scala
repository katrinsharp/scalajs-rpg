package server

import upickle.default._
import shared.Api
import scala.concurrent.ExecutionContext

object AutowireServer extends autowire.Server[String, Reader, Writer] {
  def read[Result: Reader](p: String) = read[Result](p)
  def write[Result: Writer](r: Result) = write(r)

  def routes(implicit ex: ExecutionContext) = AutowireServer.route[Api](ApiImpl)
}
