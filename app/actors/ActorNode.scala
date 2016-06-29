
package actors

import messages.MessagesActors
import MessagesActors._
import akka.actor._
import akka.event.Logging.Error
import messages.responses.ok.{OKContent, OKResponse}
import models.daos.{EventDAO, ParticipationDAO}
import play.api.libs.json._
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout

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


  def checkEventAndSenderAndParticipation(tuiId: String, senderId: String, eventId: Long): Boolean= { true
  }
  def receive = {

    case v: VoteWithFolio => {
      if(checkEventAndSenderAndParticipation(v.tuiId,v.senderId,v.eventId)){
        val newSender = sender();
        (system.actorSelection("/user/actorUsers") ? v).mapTo[String].map { message =>
          println(message)
          print(newSender)
         newSender ! message
        }

      }else{
        sender ! "Event not available"
      }
    }
    case _ => {
      println(self.path)
      sender ! "Unexpected Error"

    }
  }

}



