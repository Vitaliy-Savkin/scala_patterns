// run as: scala -cp scala_patterns.jar startup.scala true

import patterns.dependency_injection._
import MyApplication2.Client

trait TestNameProviderComponentImpl extends NameProviderComponent {
  val nameProvider: NameProvider = new NameProviderImpl
  private class NameProviderImpl extends NameProvider {
    def getName: String = "Test"
  }
}

val test = args(0).toBoolean

val client = if(test) new Client with Components with SayHelloComponentImpl with TestNameProviderComponentImpl
             else new Client with Components with SayHelloComponentImpl with NameProviderComponentImpl
client.run()
