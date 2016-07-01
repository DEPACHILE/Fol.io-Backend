package messages.responses.ok

import models.entities.BaseEntity
import play.api.libs.json.{Json, JsValue, Writes}
import messages.responses._

import scala.util.parsing.json.JSONObject

/**
 * Created by Nicolas on 26-06-2016.
 */

object OKContent {
  implicit def contentWrite: Writes[OKContent] = new Writes[OKContent] {
    override def writes(o: OKContent): JsValue = {
      Json.toJson(
        o.body.map { t =>
          Map("entity" -> t.toJson)
        }
      )
    }
  }
}
case class OKContent(body: Seq[BaseEntity]) extends Content