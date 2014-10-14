package patterns

object circle_ellipse {
  // mutable
  {
    class Ellipse(xSize: Float, ySize: Float) {
      var x = xSize
      var y = ySize

      def stretchX(dx: Float) { x += dx }
      def stretchY(dy: Float) { y += dy }
    }

    class Circle(radius: Float) extends Ellipse(2 * radius, 2 * radius)
  }
  // extensible class hierarchy
  {
    class Ellipse(val x: Float, val y: Float) {
      def stretchX(dx: Float): Ellipse = new Ellipse(x + dx, y)

      def stretchY(dy: Float): Ellipse = new Ellipse(x, y + dy)
    }

    class Circle(val radius: Float) extends Ellipse(2 * radius, 2 * radius) {
      def stretch(d: Float): Circle = new Circle(radius + d / 2)
    }
  }
  // sealed class hierarchy
  {
    sealed class Ellipse(val x: Float, val y: Float)
    sealed class Circle(val radius: Float) extends Ellipse(2 * radius, 2 * radius)

    def stretchX(e: Ellipse, dx: Float): Ellipse =
      if (dx == 0) e
      else if (e.x + dx == e.y) new Circle(e.y / 2)
      else new Ellipse(e.x + dx, e.y)

    def stretchY(e: Ellipse, dy: Float): Ellipse =
      if (dy == 0) e
      else if (e.y + dy == e.x) new Circle(e.x / 2)
      else new Ellipse(e.x, e.y + dy)
  }

}
