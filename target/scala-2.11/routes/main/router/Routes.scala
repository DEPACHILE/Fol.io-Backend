
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/ScalaRamo/chat/conf/routes
// @DATE:Wed Jun 29 02:06:00 CLT 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:8
  FolIOController_0: controllers.FolIOController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:8
    FolIOController_0: controllers.FolIOController
  ) = this(errorHandler, FolIOController_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, FolIOController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """vote/""" + "$" + """eventId<[^/]+>/""" + "$" + """sender<[^/]+>/""" + "$" + """tuiId<[^/]+>/""" + "$" + """folio<[^/]+>""", """controllers.FolIOController.vote(tuiId:String, sender:Long, eventId:Long, folio:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addUser/""" + "$" + """tuiId<[^/]+>/""" + "$" + """name<[^/]+>/""" + "$" + """lastName<[^/]+>/""" + "$" + """rut<[^/]+>/""" + "$" + """career<[^/]+>""", """controllers.FolIOController.addUser(tuiId:String, name:String, lastName:String, rut:String, career:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:8
  private[this] lazy val controllers_FolIOController_vote0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vote/"), DynamicPart("eventId", """[^/]+""",true), StaticPart("/"), DynamicPart("sender", """[^/]+""",true), StaticPart("/"), DynamicPart("tuiId", """[^/]+""",true), StaticPart("/"), DynamicPart("folio", """[^/]+""",true)))
  )
  private[this] lazy val controllers_FolIOController_vote0_invoker = createInvoker(
    FolIOController_0.vote(fakeValue[String], fakeValue[Long], fakeValue[Long], fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FolIOController",
      "vote",
      Seq(classOf[String], classOf[Long], classOf[Long], classOf[Long]),
      "GET",
      """""",
      this.prefix + """vote/""" + "$" + """eventId<[^/]+>/""" + "$" + """sender<[^/]+>/""" + "$" + """tuiId<[^/]+>/""" + "$" + """folio<[^/]+>"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_FolIOController_addUser1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addUser/"), DynamicPart("tuiId", """[^/]+""",true), StaticPart("/"), DynamicPart("name", """[^/]+""",true), StaticPart("/"), DynamicPart("lastName", """[^/]+""",true), StaticPart("/"), DynamicPart("rut", """[^/]+""",true), StaticPart("/"), DynamicPart("career", """[^/]+""",true)))
  )
  private[this] lazy val controllers_FolIOController_addUser1_invoker = createInvoker(
    FolIOController_0.addUser(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FolIOController",
      "addUser",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      """""",
      this.prefix + """addUser/""" + "$" + """tuiId<[^/]+>/""" + "$" + """name<[^/]+>/""" + "$" + """lastName<[^/]+>/""" + "$" + """rut<[^/]+>/""" + "$" + """career<[^/]+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:8
    case controllers_FolIOController_vote0_route(params) =>
      call(params.fromPath[String]("tuiId", None), params.fromPath[Long]("sender", None), params.fromPath[Long]("eventId", None), params.fromPath[Long]("folio", None)) { (tuiId, sender, eventId, folio) =>
        controllers_FolIOController_vote0_invoker.call(FolIOController_0.vote(tuiId, sender, eventId, folio))
      }
  
    // @LINE:12
    case controllers_FolIOController_addUser1_route(params) =>
      call(params.fromPath[String]("tuiId", None), params.fromPath[String]("name", None), params.fromPath[String]("lastName", None), params.fromPath[String]("rut", None), params.fromPath[String]("career", None)) { (tuiId, name, lastName, rut, career) =>
        controllers_FolIOController_addUser1_invoker.call(FolIOController_0.addUser(tuiId, name, lastName, rut, career))
      }
  }
}
