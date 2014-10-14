package patterns

object abstract_factory {

  trait WindowFactory {
    type aWindow <: Window
    type aScrollbar <: Scrollbar

    def createWindow(s: aScrollbar): aWindow
    def createScrollbar(): aScrollbar

    abstract class Window(s: aScrollbar)
    abstract class Scrollbar
  }

  object VistaFactory extends WindowFactory{
    type aWindow = VistaWindow
    type aScrollbar = VistaScrollbar

    def createWindow(s: aScrollbar) = new VistaWindow(s)
    def createScrollbar() = new VistaScrollbar

    protected class VistaWindow(s:VistaScrollbar) extends Window(s)
    protected class VistaScrollbar extends Scrollbar
  }

  def get(os: String): WindowFactory =
    if(os == "vista") VistaFactory else ???

  val vista = get("vista")
  val window1: vista.Window = vista.createWindow(vista.createScrollbar())

  //val window2 = vista.createWindow(get("default").createScrollbar()) // type error
}
