package patterns

object dependency_injection {

  // the first piece of cake
  trait NameProviderComponent {
    val nameProvider: NameProvider
    trait NameProvider { def getName: String }
  }

  // the second one
  trait SayHelloComponent {
    val sayHelloService: SayHelloService
    trait SayHelloService { def sayHello: Unit }
  }

  trait Components extends NameProviderComponent with SayHelloComponent


  trait NameProviderComponentImpl extends NameProviderComponent {
    val nameProvider: NameProvider = new NameProviderImpl
    private class NameProviderImpl extends NameProvider {
      def getName: String = "World"
    }}

  trait SayHelloComponentImpl extends SayHelloComponent {
    self: NameProviderComponent =>
    val sayHelloService: SayHelloService = new SayHelloServiceImpl
    private class SayHelloServiceImpl extends SayHelloService {
      def sayHello: Unit =
        println("Hello, " + self.nameProvider.getName)
    }}

  object MyApplication1 {
    object Components extends Components
                         with SayHelloComponentImpl
                         with NameProviderComponentImpl

    class Client(c: Components) {
      def run() = c.sayHelloService.sayHello
    }

    def main(args: Array[String]) = new Client(Components).run()
  }

  object MyApplication2 {

    class Client { c: Components => def run() = c.sayHelloService.sayHello }
    def main(args: Array[String]) =
      (new Client with Components with SayHelloComponentImpl with NameProviderComponentImpl).run()
  }
}
