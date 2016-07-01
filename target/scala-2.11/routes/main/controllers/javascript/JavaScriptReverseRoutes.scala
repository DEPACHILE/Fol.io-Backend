
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/Fol.io-Backend/conf/routes
// @DATE:Wed Jun 29 16:46:46 CLT 2016

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:8
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:8
  class ReverseFolIOController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def addUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FolIOController.addUser",
      """
        function(tuiId0,name1,lastName2,rut3,career4) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "addUser/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tuiId", encodeURIComponent(tuiId0)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name1)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("lastName", encodeURIComponent(lastName2)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("rut", encodeURIComponent(rut3)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("career", encodeURIComponent(career4))})
        }
      """
    )
  
    // @LINE:8
    def vote: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FolIOController.vote",
      """
        function(tuiId0,sender1,eventId2,folio3) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "vote/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("eventId", eventId2) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("sender", sender1) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tuiId", encodeURIComponent(tuiId0)) + "/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("folio", folio3)})
        }
      """
    )
  
  }


}
