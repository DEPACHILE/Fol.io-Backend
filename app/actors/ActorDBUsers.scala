package actors

import messages.MessagesActors
import MessagesActors._
import akka.actor._
import akka.event.Logging.Error
import messages.responses.ok.{OKResponse, OKContent}
import models.daos._
import play.api.libs.json.{Json, JsValue}
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout
import scala.util.{Success,Failure}

object ActorDBUsers{
  //def props = Props(classOf[ActorDBUsers], null, null)
  def props( userDAO: UserDAO, participationDAO: ParticipationDAO,eventDAO: EventDAO) =
    Props(classOf[ActorDBUsers], userDAO,participationDAO,eventDAO)
}

class ActorDBUsers( userDAO: UserDAO, participationDAO: ParticipationDAO, eventDAO: EventDAO) extends Actor{
  import context._
  import Actor._

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
      if (rooms.isDefined)      sender ! Json.toJson(OKResponse(OKContent(Seq(rooms.get)))).toString()
      else sender ! Json.toJson(OKResponse(OKContent(Seq()))).toString()
    }
  }
  println(self.path)

  /*def voteInDB(tuiId: Long) ={
    println("hehe")

  }*/

  def receive = {

    case v: Vote => {
      /*if(voteInDB(v.tuiId)){
          println("jalpbd")
          sender ! "sex"
      }else{
        println("jalpbd2")
        sender ! "Error in votacion"
      }*/
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