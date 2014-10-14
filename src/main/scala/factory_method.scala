package patterns

object factory_method {
  trait Room { def connect(other: Room): Unit }
  trait MagicRoom extends Room
  trait OrdinaryRoom extends Room
  trait Treasure

  class Maze(makeRoom: Treasure => Room){
    val room1 = makeRoom(randomTreasure())
    val room2 = makeRoom(randomTreasure())
    room1.connect(room2)
    val rooms = List(room1, room2)

    def randomTreasure(): Treasure = ???
  }

  trait Color
  object Color { val green = ??? }

  val ordinaryRoom: Color => Treasure => OrdinaryRoom = ???
  val magicRoom: Treasure => MagicRoom = ???

  val greenMaze = new Maze(ordinaryRoom(Color.green))
  val magicMaze = new Maze(magicRoom)
}
