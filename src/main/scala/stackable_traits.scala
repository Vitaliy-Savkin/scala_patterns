package patterns

object stackable_traits {
  trait IntQueue {
    def get(): Int
    def put(x: Int)
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new scala.collection.mutable.ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) { buf += x }
  }
  
  trait Doubling extends IntQueue {
    abstract override def put(x: Int) { super.put(2 * x) }
  }

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x + 1) }
  }

  val queue1 = new BasicIntQueue with Doubling with Incrementing
  queue1.put(10)
  queue1.get() //22

  val queue2 = new BasicIntQueue with Incrementing with Doubling
  queue2.put(10)
  queue2.get() //21
}
