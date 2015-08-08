package server

import akka.http.scaladsl.marshalling.{PredefinedToEntityMarshallers, ToEntityMarshaller}
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{`Access-Control-Allow-Headers`, `Access-Control-Allow-Methods`, `Access-Control-Allow-Credentials`, `Access-Control-Allow-Origin`}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{RequestContext, Route}
import akka.stream.ActorMaterializer
import shared.Api
import upickle.default._
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

trait HttpRoutes {

  def httpRequest2String(req: RequestContext)(implicit mat: ActorMaterializer): Future[String] =
    req.request.entity.withContentType(ContentTypes.`application/json`).toStrict(1 second).map(b => new String(b.data.toArray))

  def routes(implicit mat: ActorMaterializer): Route = {
    val baseRoutes = getRoutes ~ postRoutes ~ getOptions
    pathPrefix("api") {
      requestContext => respondWithHeaders(corsHeaders(requestContext.request))(baseRoutes)(requestContext)
    }
  }

  def getRoutes(implicit mat: ActorMaterializer): Route =
    get {
      path(Segments) { segments =>
        parameterMap { params =>
          complete {
            val req = autowire.Core.Request(segments, params)
            AutowireServer.route[Api](ApiImpl)(req).map(HttpEntity(_))
          }
        }
      }
    }

  def postRoutes(implicit mat: ActorMaterializer): Route =
    post {
      path(Segments) { segments =>
        extract(httpRequest2String) { dataExtractor =>
          complete {
            for {
              data <- dataExtractor
              response <- {
                val req = autowire.Core.Request(segments, read[Map[String, String]](data))
                AutowireServer.route[Api](ApiImpl)(req)
              }
            } yield HttpEntity(response)
          }
        }
      }
    }

  def getOptions: Route =
    options {
      complete("")
    }

  def corsHeaders(request: HttpRequest): List[HttpHeader] =
    List(
      `Access-Control-Allow-Origin`.`*`,
      `Access-Control-Allow-Credentials`(true),
      `Access-Control-Allow-Methods`(GET, POST, DELETE, OPTIONS),
      `Access-Control-Allow-Headers`("AUTHORIZATION")
    )
}

