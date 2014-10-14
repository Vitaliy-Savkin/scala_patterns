package patterns

object phantom_types {

  trait FlightStatus
  trait Flying extends FlightStatus
  trait Landed extends FlightStatus

  class Plane[Status <: FlightStatus]()

  def land(p: Plane[Flying]) = new Plane[Landed]()
  def takeOff(p: Plane[Landed]) = new Plane[Flying]()

  val plane = new Plane[Landed]()
  val flying = takeOff(plane) // ok
  val landed = land(flying) // ok
  //takeOff(flying) // error: type mismatch
  //land(landed) // error: type mismatch
}