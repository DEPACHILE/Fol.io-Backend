package models.persistence

import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile

import models.entities._
import slick.lifted.ProvenShape

/**
  * The companion object.
  */
object SlickTables extends HasDatabaseConfig[JdbcProfile] {

  protected lazy val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import dbConfig.driver.api._

  abstract class BaseTable[T](tag: Tag, name: String) extends Table[T](tag, name) {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  }
  //case class Event(id: Long, desc: String, dateInit: java.sql.Timestamp, dateEnd: java.sql.Timestamp) extends BaseEntity
  class EventTable(tag: Tag) extends BaseTable[Event](tag, "event"){
    def desc = column[String]("desc")
    def dateInit = column[String]("date_init")
    def dateEnd = column[String]("date_end")

    def * = (id, desc, dateInit, dateEnd) <> ((Event.apply _).tupled, Event.unapply)
  }
  val eventQ = TableQuery[EventTable]
  /**
   *
   * case class Participation(id: Long, name: String, lastName: String, rut: String,
                         eventId: Long, dispId: Long, career: String,
                         date: java.sql.Timestamp, sender: String) extends BaseEntity
   */
  class ParticipationTable(tag: Tag) extends BaseTable[Participation](tag, "participation"){
    def eventId = column[Long]("event_id")
    def dispId = column[Long]("disp_id")
    def name = column[String]("name")
    def lastName = column[String]("last_name")
    def rut = column[String]("rut")
    def career = column[String]("career")
    def sender = column[String]("sender")
    def date= column[String]("date")
    def tuiId = column[String]("tui_id")
    def folio = column[Long]("folio")
    def participated = column[Long]("participated")

    def * = (id, name, lastName, rut,eventId,dispId,career,sender,date,tuiId,folio,participated) <> ((Participation.apply _).tupled, Participation.unapply)
  }

  val participationQ = TableQuery[ParticipationTable]


  class UserTable(tag: Tag) extends BaseTable[UserTest](tag, "user_test"){
    def tuiId= column[String]("tui_id")
    def name = column[String]("name")
    def lastName = column[String]("last_name")
    def rut = column[String]("rut")
    def career = column[String]("career")

    def * = (id, tuiId,name, lastName, rut,career) <> ((UserTest.apply _).tupled, UserTest.unapply)
  }
  val userQ = TableQuery[UserTable]
}


