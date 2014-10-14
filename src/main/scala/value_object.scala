package patterns

object value_object {
  case class Point(x: Int, y: Int, z: Int)
  // looks fine
  val movePointZ = (dz: Int) => (p: Point) => p.copy(z = p.z + dz)

  trait Room
  case class Location(room: Room, p: Point)
  // there is some code smell
  val moveLocZ = (dz: Int) => (l: Location) =>
    l.copy(p = l.p.copy(z = l.p.z + dz))

  case class Object(l: Location, weight: Int)
  // awful
  val moveObjZ = (dz: Int) => (o: Object) =>
    o.copy(l = o.l.copy(p = o.l.p.copy(z = o.l.p.z + dz)))


  case class Lens[S, P](get: S => P, set: S => P => S){
    val modify: S => (P => P) => S =
      (s: S) => (f: P => P) => set(s)(f(get(s)))

    def andThen[T](next: Lens[P, T]): Lens[S, T] = {
      new Lens[S, T](s => next.get(this.get(s)),
                     s => v => this.modify(s)(next.set(_)(v)))
    }
  }

  val pointZ = new Lens[Point, Int](p => p.z, p => v => p.copy(z = v))

  val locPoint = new Lens[Location, Point](l => l.p, l => v => l.copy(p = v))

  val objLoc = new Lens[Object, Location](o => o.l, o => v => o.copy(l = v))

  val objZ:Lens[Object,Int]= objLoc andThen locPoint andThen pointZ

  val moveZ = (dz: Int) => (o: Object) => objZ.modify(o) { _ + dz }

}