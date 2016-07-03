
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/Fol.io-Backend/conf/routes
// @DATE:Sun Jul 03 15:42:18 CLT 2016

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:8
package controllers {

  // @LINE:8
  class ReverseFolIOController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def addUser(tuiId:String, name:String, lastName:String, rut:String, career:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "addUser/" + implicitly[PathBindable[String]].unbind("tuiId", dynamicString(tuiId)) + "/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)) + "/" + implicitly[PathBindable[String]].unbind("lastName", dynamicString(lastName)) + "/" + implicitly[PathBindable[String]].unbind("rut", dynamicString(rut)) + "/" + implicitly[PathBindable[String]].unbind("career", dynamicString(career)))
    }
  
    // @LINE:17
    def deleteAll(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "deleteAll")
    }
  
    // @LINE:10
    def voteRut(rut:String, sender:Long, eventId:Long, folio:Long): Call = {
    
      (rut: @unchecked, sender: @unchecked, eventId: @unchecked, folio: @unchecked) match {
      
        // @LINE:10
        case (rut, sender, eventId, folio) if folio == -1 =>
          implicit val _rrc = new ReverseRouteContext(Map(("folio", -1)))
          Call("GET", _prefix + { _defaultPrefix } + "voteRut/" + implicitly[PathBindable[Long]].unbind("eventId", eventId) + "/" + implicitly[PathBindable[Long]].unbind("sender", sender) + "/" + implicitly[PathBindable[String]].unbind("rut", dynamicString(rut)) + "/")
      
        // @LINE:11
        case (rut, sender, eventId, folio)  =>
          import ReverseRouteContext.empty
          Call("GET", _prefix + { _defaultPrefix } + "voteRut/" + implicitly[PathBindable[Long]].unbind("eventId", eventId) + "/" + implicitly[PathBindable[Long]].unbind("sender", sender) + "/" + implicitly[PathBindable[String]].unbind("rut", dynamicString(rut)) + "/" + implicitly[PathBindable[Long]].unbind("folio", folio) + "/")
      
      }
    
    }
  
    // @LINE:8
    def voteTui(tuiId:String, sender:Long, eventId:Long, folio:Long): Call = {
    
      (tuiId: @unchecked, sender: @unchecked, eventId: @unchecked, folio: @unchecked) match {
      
        // @LINE:8
        case (tuiId, sender, eventId, folio)  =>
          import ReverseRouteContext.empty
          Call("GET", _prefix + { _defaultPrefix } + "voteTui/" + implicitly[PathBindable[Long]].unbind("eventId", eventId) + "/" + implicitly[PathBindable[Long]].unbind("sender", sender) + "/" + implicitly[PathBindable[String]].unbind("tuiId", dynamicString(tuiId)) + "/" + implicitly[PathBindable[Long]].unbind("folio", folio) + "/")
      
        // @LINE:9
        case (tuiId, sender, eventId, folio) if folio == -1 =>
          implicit val _rrc = new ReverseRouteContext(Map(("folio", -1)))
          Call("GET", _prefix + { _defaultPrefix } + "voteTui/" + implicitly[PathBindable[Long]].unbind("eventId", eventId) + "/" + implicitly[PathBindable[Long]].unbind("sender", sender) + "/" + implicitly[PathBindable[String]].unbind("tuiId", dynamicString(tuiId)) + "/")
      
      }
    
    }
  
  }


}
