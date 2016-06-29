package models.entities
import play.api.libs.json._
import play.api.libs.functional.syntax._


trait BaseEntity{
  val id : Long
  def isValid = true
  def toJson:JsValue
}