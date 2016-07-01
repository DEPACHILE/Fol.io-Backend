package actors

import messages.MessagesActors
import MessagesActors._
import akka.actor._
import akka.event.Logging.Error
import messages.responses.error.{ErrorContent, ErrorResponse}
import messages.responses.ok.{OKResponse, OKContent}
import models.daos._
import play.api.libs.json.{Json, JsValue}
import akka.pattern.ask
import scala.concurrent.{Awaitable, Await, Future}
import scala.concurrent.duration._
import akka.util.Timeout
import scala.util.{Success,Failure}
import models.entities.{UserTest, Participation}

object ActorDBUsers{
  //def props = Props(classOf[ActorDBUsers], null, null)
  def props( userDAO: UserDAO, participationDAO: ParticipationDAO,eventDAO: EventDAO) =
    Props(classOf[ActorDBUsers], userDAO,participationDAO,eventDAO)
}

class ActorDBUsers( userDAO: UserDAO, participationDAO: ParticipationDAO, eventDAO: EventDAO) extends Actor{
  import context._
  import Actor._

  println(self.path)
  //Creacion de actores secundarios
  val loop={
    var n: Int=0
    for( n <- 1 until 5){
      println(n)
      actorOf(ActorNode.props(participationDAO,eventDAO))

    }
  }


  def getUsers(sender : ActorRef) = {
    println("rooms")
    userDAO.all.map { rooms =>
      println("rooms2")
      sender ! rooms.toString
    }
  }
  def getUser(sender : ActorRef,tuiId: String) = {
    println("rooms")
    userDAO.byTuiId(tuiId).map { rooms =>
      println("rooms2")
      if (rooms.isDefined)      sender ! Json.toJson(OKResponse(OKContent(Seq(rooms.get))))
      else sender ! Json.toJson(OKResponse(OKContent(Seq())))
    }
  }
  def userExists(v: VoteWithFolio) ={
    userDAO.byTuiId(v.tuiId).flatMap {
      case Some(va) => Future(Option(va))
      case _ =>  Future(None)
    }


  }

  // falta arreglar weas aca

  def vote(v: VoteWithFolio, sender: ActorRef, user: UserTest)={
    println("voting")
    participationDAO.byTuiId(v.tuiId).map {

    case Some(va) =>  {
      println("user voto")
      sender ! Json.toJson(ErrorResponse(ErrorContent("Persona ya voto")))
    }
    case _ => {
      val s = user.toString
      println(s"user no ha votado $s")
      val participation = Participation(0,user.name,user.lastName,user.rut,1,1,user.career,"a","a",user.tuiId,v.folio,1)
      participationDAO.insert(participation)
        .map{
        case _=>sender ! Json.toJson(OKResponse(OKContent(Seq(participation))))
      }
    }
  }
  }

  def voteUser(v: VoteWithFolio)={
    val userContainer = Await.result(userExists(v), 1000 millis)
    if(!userContainer.isEmpty){
      println("user Exists!")
      val newSender = sender()
      Await.result(vote(v,newSender,userContainer.get), 1000 millis)
    }
    else{
      sender ! ErrorResponse(ErrorContent("Usuario no existe"))
    }

  }

  def receive = {

    case v: VoteWithFolio => {
      println("voting")
      voteUser(v)

    }
    case v: VoteWithoutFolio => {
      println("inside")
      val newSender = sender()
      getUser(newSender,v.tuiId)

    }
    case _ => {
      println(self.path)
      println("jalp")

    }
  }

}