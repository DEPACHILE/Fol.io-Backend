
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/ScalaRamo/chat/conf/routes
// @DATE:Wed Jun 29 02:06:00 CLT 2016

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

  
    // @LINE:12
    def addUser(tuiId:String, name:String, lastName:String, rut:String, career:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "addUser/" + implicitly[PathBindable[String]].unbind("tuiId", dynamicString(tuiId)) + "/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)) + "/" + implicitly[PathBindable[String]].unbind("lastName", dynamicString(lastName)) + "/" + implicitly[PathBindable[String]].unbind("rut", dynamicString(rut)) + "/" + implicitly[PathBindable[String]].unbind("career", dynamicString(career)))
    }
  
    // @LINE:8
    def vote(tuiId:String, sender:Long, eventId:Long, folio:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "vote/" + implicitly[PathBindable[Long]].unbind("eventId", eventId) + "/" + implicitly[PathBindable[Long]].unbind("sender", sender) + "/" + implicitly[PathBindable[String]].unbind("tuiId", dynamicString(tuiId)) + "/" + implicitly[PathBindable[Long]].unbind("folio", folio))
    }
  
  }


}
