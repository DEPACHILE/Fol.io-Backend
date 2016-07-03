
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Nicolas/Documents/Fol.io-Backend/conf/routes
// @DATE:Sun Jul 03 15:42:18 CLT 2016

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseFolIOController FolIOController = new controllers.ReverseFolIOController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseFolIOController FolIOController = new controllers.javascript.ReverseFolIOController(RoutesPrefix.byNamePrefix());
  }

}
