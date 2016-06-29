package messages.responses

import models.entities.UserTest
import play.api.libs.json.{JsValue, Json, Writes}

/**
 * Created by Nicolas on 26-06-2016.
 */

trait Response {
  def code: Int
  def content: Content

}
