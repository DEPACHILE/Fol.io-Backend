
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/ScalaRamo/chat/conf/routes
// @DATE:Wed Jun 29 02:06:00 CLT 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseFolIOController FolIOController = new controllers.ReverseFolIOController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseFolIOController FolIOController = new controllers.javascript.ReverseFolIOController(RoutesPrefix.byNamePrefix());
  }

}
