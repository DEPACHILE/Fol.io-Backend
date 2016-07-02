package models.daos

import javax.inject.{Singleton, Inject}

import models.entities.BaseEntity
import models.persistence.SlickTables
import models.persistence.SlickTables.BaseTable
import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.lifted.{CanBeQueryCondition}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext



import models.entities._

@Singleton
class EventDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.driver.api._
  import dbConfig.db

  protected val tableQ = SlickTables.eventQ

  def all: Future[Seq[Event]] = {
    db.run(tableQ.result)
  }

  def insert(obj: Event): Future[Long] = {
    db.run(tableQ returning tableQ.map(_.id) += obj)
  }


  def byId(id: Long): Future[Option[Event]] = {
    db.run(tableQ.filter(_.id === id).result.headOption)
  }

  /*def byDate(id: Long): Future[Seq[Event]] = {
    db.run(tableQ.filter(_.dateInit < id).result)
  }*/

}
@Singleton
class ParticipationDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {


  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.driver.api._
  import dbConfig.db

  protected val tableQ = SlickTables.participationQ

  def all: Future[Seq[Participation]] = {
    db.run(tableQ.result)
  }

  def insert(obj: Participation): Future[Long] = {
    db.run(tableQ returning tableQ.map(_.id) += obj)
  }
  def add(obj: Participation): Future[String] = {
    dbConfig.db.run(tableQ returning tableQ.map(_.id) += obj).map(res => "User successfully added").recover {
      case ex: Exception => ex.getCause.getMessage
    }
  }
  def deleteAll(): Future[Int] = {
    db.run(tableQ.delete)
  }


  def byId(id: Long): Future[Option[Participation]] = {
    db.run(tableQ.filter(_.id === id).result.headOption)
  }
  def byTuiId(tuiId: String): Future[Option[Participation]] = {
    db.run(tableQ.filter(_.tuiId === tuiId).result.headOption)
  }
  /*def hasParticipatedById(tuiId: Long):Future[Option[Participation]] = {
    db.run(tableQ.filter( x=> x.tuiId === tuiId && x.participated).result.headOption)
  }*/

  /*def byRut(rut: Long): Future[Option[Participation]] = {
    db.run(tableQ.filter(_.rut < rut).result.headOption)
  }*/

  def byCareer(career: String, eventId: Long): Future[Seq[Participation]] = {
    db.run(tableQ.filter( x=> {x.career === career}).result)
  }
}

@Singleton
class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.driver.api._
  import dbConfig.db

  protected val tableQ = SlickTables.userQ

  def all: Future[Seq[UserTest]] = {
    db.run(tableQ.result)
  }

  def insert(obj: UserTest): Future[Long] = {
    db.run(tableQ returning tableQ.map(_.id) += obj)
  }


  def byId(id: Long): Future[Option[UserTest]] = {
    db.run(tableQ.filter(_.id === id).result.headOption)
  }

  def byRut(rut: String): Future[Option[UserTest]] = {
    db.run(tableQ.filter(_.rut === rut).result.headOption)
  }

  def byCareer(career: String, eventId: Long): Future[Seq[UserTest]] = {
    db.run(tableQ.filter( x=> {x.career === career}).result)
  }
}

@Singleton
class TuiRutDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig.driver.api._
  import dbConfig.db

  protected val tableQ = SlickTables.tuiRutQ

  def all: Future[Seq[TuiRut]] = {
    db.run(tableQ.result)
  }

  def insert(obj: TuiRut): Future[Long] = {
    db.run(tableQ returning tableQ.map(_.id) += obj)
  }


  def byId(id: Long): Future[Option[TuiRut]] = {
    db.run(tableQ.filter(_.id === id).result.headOption)
  }

  def byRut(rut: String): Future[Option[TuiRut]] = {
    db.run(tableQ.filter(_.rut === rut).result.headOption)
  }
}


























trait AbstractBaseDAO[T,A] {
  def insert(row : A): Future[Long]
  def insert(rows : Seq[A]): Future[Seq[Long]]
  def update(row : A): Future[Int]
  def update(rows : Seq[A]): Future[Unit]
  def findById(id : Long): Future[Option[A]]
  def findByFilter[C : CanBeQueryCondition](f: (T) => C): Future[Seq[A]]
  def deleteById(id : Long): Future[Int]
  def deleteById(ids : Seq[Long]): Future[Int]
  def deleteByFilter[C : CanBeQueryCondition](f:  (T) => C): Future[Int]
}


abstract class BaseDAO[T <: BaseTable[A], A <: BaseEntity]() extends AbstractBaseDAO[T,A] with HasDatabaseConfig[JdbcProfile] {
  protected lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import dbConfig.driver.api._

  protected val tableQ: TableQuery[T]

  def insert(row : A): Future[Long] ={
    insert(Seq(row)).map(_.head)
  }

  def insert(rows : Seq[A]): Future[Seq[Long]] ={
    db.run(tableQ returning tableQ.map(_.id) ++= rows.filter(_.isValid))
  }

  def update(row : A): Future[Int] = {
    if (row.isValid)
      db.run(tableQ.filter(_.id === row.id).update(row))
    else
      Future{0}
  }

  def update(rows : Seq[A]): Future[Unit] = {
    db.run(DBIO.seq((rows.filter(_.isValid).map(r => tableQ.filter(_.id === r.id).update(r))): _*))
  }

  def findById(id : Long): Future[Option[A]] = {
    db.run(tableQ.filter(_.id === id).result.headOption)
  }

  def findByFilter[C : CanBeQueryCondition](f: (T) => C): Future[Seq[A]] = {
    db.run(tableQ.withFilter(f).result)
  }

  def deleteById(id : Long): Future[Int] = {
    deleteById(Seq(id))
  }

  def deleteById(ids : Seq[Long]): Future[Int] = {
    db.run(tableQ.filter(_.id.inSet(ids)).delete)
  }

  def deleteByFilter[C : CanBeQueryCondition](f:  (T) => C): Future[Int] = {
    db.run(tableQ.withFilter(f).delete)
  }
  def deleteByFilter[C : CanBeQueryCondition](f:  (T) => C): Future[Int] = {
    db.run(tableQ.withFilter(f).delete)
  }

}