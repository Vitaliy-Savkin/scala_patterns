package patterns

object decorator {
  import java.io._
  import java.util.zip.GZIPInputStream

  val fileInputStream:     String      => FileInputStream     = ???
  val bufferedInputStream: InputStream => BufferedInputStream = ???
  val gzipInputStream:     InputStream => GZIPInputStream     = ???
  val objectInputStream:   InputStream => ObjectInputStream   = ???

  val getStream = fileInputStream andThen bufferedInputStream andThen gzipInputStream andThen objectInputStream

  val deserializationStream = getStream("objects.gz")

  val obj = deserializationStream.readObject()

  deserializationStream.close()
}
