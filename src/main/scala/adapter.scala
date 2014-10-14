package patterns

object adapter {
  trait StringProvider { def getStringData: String }
  val show: StringProvider => Unit = ???

  trait Message {// how to show it?
  def user: String
    def data: String
  }
  val formatMessage: Message => StringProvider = m =>
    new StringProvider {
      def getStringData: String = m.user + " said " + m.data
    }

  val showMessage: Message => Unit = formatMessage andThen show
}
