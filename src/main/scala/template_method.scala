package patterns

import scala.annotation.tailrec

object template_method {
  // OOP-style
  trait GameState { def winner(): Int }
  class Game(initialState: GameState,
             endOfGame: GameState => Boolean,
             makePlay: (GameState, Int) => GameState){
    def playGame(playersCount: Int): Int = {
      var state = initialState
      var i = 0
      while(!endOfGame(state)){
        state = makePlay(state, i)
        i = (i + 1) % playersCount
      }
      state.winner()
    }
  }

  // FP-style
  case class GameRules(endOfGame: GameState => Boolean,
                  makePlay: (GameState, Int) => GameState,
                  playersCount: Int)

  @tailrec
  final def playGame(gameRules: GameRules,
               state: GameState,
               currPlayer: Int): Int =
    if(gameRules.endOfGame(state)) state.winner()
    else playGame(gameRules,
      gameRules.makePlay(state, currPlayer),
      (currPlayer + 1) % gameRules.playersCount)
}
