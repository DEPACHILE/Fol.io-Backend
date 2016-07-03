package controllers

import java.util.Dictionary
import javax.inject._

import messages.MessagesActors
import MessagesActors.VoteWithFolio
import actors.{ActorDBUsers, ActorNode}
import messages.responses.error.{ErrorContent, ErrorResponse}
import models.daos.{TuiRutDAO, UserDAO, EventDAO, ParticipationDAO}
import models.entities._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json.Json

import play.api.libs.json.{Json, Writes, JsValue}

import play.api.mvc._
import akka.actor._

import scala.concurrent.{Future, ExecutionContext}
import MessagesActors._



@Singleton
class FolIOController @Inject()(eventDAO: EventDAO, participationDAO: ParticipationDAO, userDAO: UserDAO,tuiRutDAO: TuiRutDAO)(implicit ec: ExecutionContext, system: ActorSystem, mat: akka.stream.Materializer) extends Controller {

  import akka.pattern.ask
  import scala.concurrent.duration._
  import akka.util.Timeout


  //Implicits


  implicit val timeout: Timeout = 10.seconds


  //Actor database
  system.actorOf(ActorDBUsers.props(userDAO,participationDAO,eventDAO), "actorUsers")

  //implicit val timeout: Timeout =5.seconds

  private def sendVote(v: Vote):Future[Result]= {
    val senderId = v.senderId
    (system.actorSelection(s"/user/actorUsers/$senderId") ? v).mapTo[JsValue].map { message =>
      Ok(message)
    }
  }

 /* def vote(tuiId: String, senderId: Long, eventId: Long, folio: Long = -1) = Action.async {
   voteAsync(tuiId,senderId,eventId,folio)
  }*/
  private def vote(rut: String, senderId: Long, eventId: Long, folio: Long = -1,tuiId: String = "0"):Future[Result] =
    folio match {
      case -1 =>
        sendVote(VoteWithoutFolio(rut, "$a", eventId,tuiId))
      case _ =>
        sendVote(VoteWithFolio(rut, "$a", eventId, folio,tuiId))
    }
  def voteRut(rut: String, senderId: Long, eventId: Long, folio: Long = -1) = Action.async { implicit request =>
        vote(rut, senderId, eventId, folio)
  }
  def voteTui(tuiId: String, senderId: Long, eventId: Long, folio: Long = -1) = Action.async { implicit request =>
    tuiRutDAO.byTuiId(tuiId).flatMap{
      case Some(tuiRut) => {
        vote(tuiRut.rut, senderId, eventId, folio,tuiId)
      }
      case _ => Future(Ok(""))
    }
  }

  def addUser(tuiId: String, name: String, lastName: String, rut: String, career: String) = Action { //test
    userDAO.insert(UserTest(0, name, rut, career))
    Ok("user redi")
  }

  def getUsers = Action.async{ implicit request =>
    println("rooms")
    userDAO.all.map{ rooms =>
      Ok(Json.toJson(rooms.toString))
    }  }
  def deleteAll =Action.async{ implicit request =>
    println("rooms")
    participationDAO.deleteAll().map{ rooms =>
      Ok(Json.toJson("Ok"))
    }  }
}
