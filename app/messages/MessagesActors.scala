package messages

/**
 * Created by Nicolas on 20-06-2016.
 */
object MessagesActors {
  trait Vote{
    def tuiId: String
    def senderId: String
    def eventId: Long
  }
  trait MessageOk

  case class VoteWithoutFolio(tuiId: String,senderId: String, eventId:Long ) extends Vote
  case class VoteWithFolio(tuiId: String,senderId: String, eventId:Long,folio: Long) extends Vote
  case class VoteRut(rut: String,senderId: String, eventId:Long,folio: Long)
  case class CanVote(tuiId: Long, name: String, lastName: String, career:String) extends MessageOk
  case class VoteReady() extends MessageOk
  trait vError{
    def error:String
  }
  case class VoteError(error:String) extends vError


}








