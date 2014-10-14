package patterns

object syntax {
  // lambda
  val inc1 = (x: Int) => x + 1
  val inc2 = (_: Int) + 1
  val inc3: Int => Int = _ + 1
  // closures
  val const = 10
  val addConst = (x: Int) => x + const

  // method
  class Foo { def inc(i: Int): Int = i + 1 }
  // methods as functions
  val foo = new Foo
  val f: Int => Int = foo.inc _

  // partially defined function
  val fac: PartialFunction[Int, Int] = {
    case 0 | 1 => 1
    case n if n > 1 => n * fac(n - 1)
  }

  // curried function
  val add = (x: Int) => (y: Int) => x + y
  val inc = add(1)

  // high-order function
  val applyToDoubled = (f: Int => Int) => (x: Int) => f(2 * x)
  val incDoubled: Int => Int = applyToDoubled(inc)
  incDoubled(10) // 21
}
