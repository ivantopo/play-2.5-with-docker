package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    val currentClassloader = Thread.currentThread().getContextClassLoader
    def getClassloaderChain(classLoader: ClassLoader, children: List[String]): List[String] = {
      if(classLoader == null) children else {
        getClassloaderChain(classLoader.getParent, classLoader.toString :: children)
      }
    }

    val classLoaders = getClassloaderChain(currentClassloader, Nil).map("===> " + _)

    Ok(views.html.index("Classloader chain: \n" + classLoaders.mkString("\n")))
  }

}
