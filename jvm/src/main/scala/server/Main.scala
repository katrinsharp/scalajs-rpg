package server

import akka.actor.ActorSystem

object Main extends App {

  val system = ActorSystem("MainSystem")

  system.actorOf(HttpServiceActor.props("127.0.0.1", 8080), "HttpServiceActor")

}
