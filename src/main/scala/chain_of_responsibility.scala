package patterns

object chain_of_responsibility {
  def compress(s: String): String = ???
  def writeToDB(s: String): Unit = ???
  val logger = new { def error(s: String) = ??? }

  val storeEmpty: PartialFunction[String, Unit] = {
    case "" => logger.error("Empty message")
  }

  val storeShort: PartialFunction[String, Unit] = {
    case s if s.length < 256 => writeToDB(s)
  }

  val storeLong: PartialFunction[String, Unit] = {
    case s if s.length >= 256 => writeToDB(compress(s))
  }

  val storeMessage = storeEmpty orElse storeShort orElse storeLong
}
