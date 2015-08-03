package server

import akka.http.scaladsl.marshalling.{PredefinedToEntityMarshallers, ToEntityMarshaller}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, MediaTypes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RequestContext, Route}
import akka.stream.ActorMaterializer
import upickle.default._
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

trait HttpRoutes {

  def httpRequest2String(req: RequestContext)(implicit mat: ActorMaterializer): Future[String] =
    req.request.entity.withContentType(ContentTypes.`application/json`).toStrict(1 second).map(b => new String(b.data.toArray))


  def routes(implicit mat: ActorMaterializer): Route = suggestionsRoute

  def suggestionsRoute(implicit mat: ActorMaterializer): Route =
    post {
      path("api" / Segments) { segments =>
        extract(httpRequest2String) { dataExtractor =>

          val resultF = for {
            data <- dataExtractor
            response <- {
              println(data)
              val m = read[Map[String, String]](data)
              println(m)
              AutowireServer.routes(global)(
                autowire.Core.Request(segments, m))
            }
          } yield response

          onComplete(resultF) {
            case result => complete(result)
          }
        }
      }
    }
}

