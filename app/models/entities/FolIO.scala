package models.entities

import play.api.libs.json.{Json, JsValue, Writes}

object Event{
  implicit val entityWrite: Writes[Event]= new Writes[Event] {
    override def writes(o:Event): JsValue  = Json.obj(
      "id" ->  o.id,
      "content" -> o.desc,
      "dateInit" -> o.dateInit,
      "dateEnd" -> o.dateEnd
    )
  }

}
case class Event(id: Long, desc: String, dateInit: String, dateEnd: String) extends BaseEntity{
  override def toJson=
    Json.toJson(this)
}


object Participation{
  implicit val entityWrite: Writes[Participation]= new Writes[Participation] {
    override def writes(o:Participation): JsValue  = Json.obj(
      "id" ->  o.id,
      "name" -> o.name,
      "lastName" -> o.lastName,
      "rut" -> o.rut,
      "participated" -> o.participated
    )
  }
}

case class Participation(id: Long, name: String, lastName: String, rut: String,
                         eventId: Long, dispId: Long, career: String,
                         date: String, sender: String,tuiId: String, folio: Long, participated: Long) extends BaseEntity{
  override def toJson=
  Json.toJson(this)
}


object UserTest{
  implicit val entityWrite: Writes[UserTest]= new Writes[UserTest] {
    override def writes(o:UserTest): JsValue  = Json.obj(
      "id" ->  o.id,
      "name" -> o.name.split('/')(0),
    "lastname" -> o.name.split('/')(1),
      "rut" -> o.rut,
      "career" -> o.career
    )
  }
}

case class UserTest(id: Long, name: String, rut: String, career: String) extends BaseEntity{
  override def toJson=
    Json.toJson(this)
}

object TuiRut{
  implicit val entityWrite: Writes[TuiRut]= new Writes[TuiRut] {
    override def writes(o:TuiRut): JsValue  = Json.obj(
      "id" ->  o.id,
      "tuiId" -> o.tuiId,
      "rut" -> o.rut
    )
  }
}

case class TuiRut(id: Long,tuiId: String, rut: String) extends BaseEntity{
  override def toJson=
    Json.toJson(this)
}
