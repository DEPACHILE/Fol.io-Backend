
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/Fol.io-Backend/conf/routes
// @DATE:Wed Jun 29 16:46:46 CLT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
