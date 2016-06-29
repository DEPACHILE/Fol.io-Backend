package messages.responses.error

import messages.responses.Content
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * Created by Nicolas on 26-06-2016.
 */
object ErrorContent{
  implicit def contentWrite: Writes[ErrorContent]= new Writes[ErrorContent] {
    override def writes(o:ErrorContent): JsValue  = Json.obj(
      "error" ->  o.body
    )
  }
}
case class ErrorContent(body: String) extends Content