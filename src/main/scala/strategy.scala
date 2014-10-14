package patterns

object strategy {
  trait Point
  trait Block
  trait Context

  class Layout(layoutStrategy: (Point, Block) => Point)

  val globalContext: Context = ???

  // Place extra data using currying
  {
    val horizontal: Context => (Point, Block) => Point = ???
    val vertical: Context => (Point, Block) => Point = ???
    val layout = new Layout(horizontal(globalContext))
  }

  // Place extra data using closures:
  // use globalContext here
  {
    val horizontal: (Point, Block) => Point = ???
    val vertical: (Point, Block) => Point = ???
    val layout = new Layout(horizontal)
  }
}

