
package actors

import akka.actor.Status.Success
import messages.MessagesActors
import MessagesActors._
import akka.actor._
import akka.event.Logging.Error
import messages.responses.error.{ErrorContent, ErrorResponse}
import messages.responses.ok.{OKContent, OKResponse}
import models.daos.{EventDAO, ParticipationDAO}
import org.omg.CosNaming.NamingContextPackage.NotFound
import play.api.libs.json._
import akka.pattern.ask
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import akka.util.Timeout

import scala.util.Failure

object ActorNode{
  def props = Props[ActorNode]
  def props(participationDAO: ParticipationDAO,eventDAO: EventDAO) =
    Props(classOf[ActorNode],participationDAO,eventDAO)
}

class ActorNode(participationDAO: ParticipationDAO, eventDAO : EventDAO) extends Actor {

  import context._
  import Actor._


  implicit val timeout: Timeout = 20.seconds

  println(self.path)




  def checkParticipation(tuiId: String)={ participationDAO.byTuiId(tuiId).flatMap{
    case Some(room) => Future(false)

    case _ => Future(true)
  }
  }
  def receive = {

    case v: VoteWithFolio => {
      if(Await.result(checkParticipation(v.tuiId), 1000 millis)){
        val newSender = sender();
        (system.actorSelection("/user/actorUsers") ? v).mapTo[JsValue].map { message =>
          println(message)
          print(newSender)
         newSender ! message
        }

      }else{
        sender ! Json.toJson(ErrorResponse(ErrorContent("Persona ya voto")))
      }
    }
    case v: VoteWithoutFolio => {
      if(Await.result(checkParticipation(v.tuiId), 1000 millis)){
        val newSender = sender();
        (system.actorSelection("/user/actorUsers") ? v).mapTo[JsValue].map { message =>
          println(message)
          print(newSender)
          newSender ! message
        }

      }else{
        sender ! Json.toJson(ErrorResponse(ErrorContent("Persona ya voto")))
      }

    }
    case _ => {
      println(self.path)
      sender ! Json.toJson(ErrorResponse(ErrorContent("Unexpected error")))

    }
  }

}



