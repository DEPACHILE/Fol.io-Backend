package messages.responses.ok

import messages.responses.Response
import play.api.libs.json.{Json, JsValue, Writes}

/**
 * Created by Nicolas on 26-06-2016.
 */
object OKResponse{
  implicit def responseWrite: Writes[OKResponse]= new Writes[OKResponse] {
    override def writes(o:OKResponse): JsValue  = Json.obj(
      "code" ->  o.code,
      "content" -> Json.toJson(o.content)
    )
  }
}
case class OKResponse(content: OKContent) extends Response{
  def code= 200

}
