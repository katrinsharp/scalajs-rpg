package server

import akka.actor.{ActorLogging, Actor, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.pattern.pipe
import akka.stream.scaladsl.ImplicitMaterializer

import scala.concurrent.Future
import scala.util.{Failure, Success}

class HttpServiceActor(
  host: String,
  port: Int)
    extends Actor with ActorLogging with HttpRoutes with ImplicitMaterializer  {

  import context.dispatcher

  def startHttpServer: Future[ServerBinding] = {
    // Boot the server
    Http(context.system).bindAndHandle(routes(materializer), host, port).pipeTo(self)
  }

  startHttpServer.onComplete {
    case Success(binding) =>
      log.info("HttpService started, ready to service requests on {}", binding.localAddress.toString)
    case Failure(ex) =>
      ex.printStackTrace()
      context.system.shutdown()
      context.system.awaitTermination()
      sys.exit(1)
  }

  def receive = Actor.emptyBehavior

}

object HttpServiceActor {
  def props(host: String,  port: Int): Props = Props(classOf[HttpServiceActor], host, port)
}

