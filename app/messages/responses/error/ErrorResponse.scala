package messages.responses.error

import messages.responses.Response
import play.api.libs.json.{Json, JsValue, Writes}

/**
  * Created by Nicolas on 26-06-2016.
  */
object ErrorResponse{
  implicit def responseWrite: Writes[ErrorResponse]= new Writes[ErrorResponse] {
    override def writes(o:ErrorResponse): JsValue  = Json.obj(
      "code" ->  o.code,
      "content" -> Json.toJson(o.content)
    )
  }
}
case class ErrorResponse(content: ErrorContent) extends Response{
  val code = 400
 }
