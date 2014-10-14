package patterns

object singleton {
  trait Locale {
    def getMessage(key: String): String
  }

  object MessageBox{
    def show(message: String): String = ???
    def show(messageKey: String, locale: Locale): String =
      show(locale.getMessage(messageKey))
  }

  class ConfigBasedLocale(configFile: String) extends Locale{
    def getMessage(key: String): String = ??? // get messages from config
  }

  MessageBox.show(
    "FileNotFoundError",
    new ConfigBasedLocale("user_defined.conf"))

  object English extends ConfigBasedLocale("english.conf")
  object French extends ConfigBasedLocale("french.conf")

  MessageBox.show("FileNotFoundError", English)
  MessageBox.show("FileNotFoundError", French)

}
