package controllers

import java.util.Dictionary
import javax.inject._

import messages.MessagesActors
import MessagesActors.VoteWithFolio
import actors.{ActorDBUsers, ActorNode}
import models.daos.{UserDAO, EventDAO, ParticipationDAO}
import models.entities._
import play.api.data.Forms._
import play.api.data._
import play.api.libs.json.Json

import play.api.libs.json.{Json, Writes, JsValue}

import play.api.mvc._
import akka.actor._

import scala.concurrent.ExecutionContext
import MessagesActors._



@Singleton
class FolIOController @Inject()(eventDAO: EventDAO, participationDAO: ParticipationDAO, userDAO: UserDAO)(implicit ec: ExecutionContext, system: ActorSystem, mat: akka.stream.Materializer) extends Controller {

  import akka.pattern.ask
  import scala.concurrent.duration._
  import akka.util.Timeout


  //Implicits


  implicit val timeout: Timeout = 10.seconds


  //Actor database
  system.actorOf(ActorDBUsers.props(userDAO,participationDAO,eventDAO), "actorUsers")

  //implicit val timeout: Timeout =5.seconds

  def sendVote(v: Vote) = {
    val senderId = v.senderId
    (system.actorSelection(s"/user/actorUsers/$senderId") ? v).mapTo[JsValue].map { message =>
      Ok(message)
    }
  }

  def vote(tuiId: String, senderId: Long, eventId: Long, folio: Long = -1) = Action.async {
    folio match {
      case -1 =>
        sendVote(VoteWithoutFolio(tuiId, "$a", eventId))
      case _ =>
        sendVote(VoteWithFolio(tuiId, "$a", eventId, folio))

    }
  }

  def addUser(tuiId: String, name: String, lastName: String, rut: String, career: String) = Action { //test
    userDAO.insert(UserTest(0, tuiId, name, lastName, rut, career))
    Ok("user redi")
  }

  def getUsers = Action.async{ implicit request =>
    println("rooms")
    userDAO.all.map{ rooms =>
      Ok(Json.toJson(rooms.toString))
    }  }
}
