trait Result
case object Hit extends Result
case object Miss extends Result

class Rules (size: Int) {
  private val io = new IO()
  private val coordSpace = new CoordinateSpace(size, size)
  private val AIPlayer = new AIPlayer(new Board(size))
  private val humanPlayer = new HumanPlayer(new Board(size))
  private val sizes = List(5, 4, 3, 3, 2)
  private val fleet = Array.tabulate(sizes.size)(x => new Ship(sizes(x)))
  
  // Displays the players board and then calls setupShip for each
  // of the available ships with sizes dictated in sizes
  def setup () {
    AIPlayer.setupBoard(sizes, coordSpace)
    println (humanPlayer.getBoard().toString())
    sizes.foreach((x) => {
                    humanPlayer.setupShip(x, coordSpace)
                    println(humanPlayer.getBoard().toString())
                    })
  }
  
  
  def play(){
    while (humanPlayer.alive() && AIPlayer.alive()){
      
    }
  }
  
  // Makes a guess at the given coord in the given board, returns
  // the result
  private def guess (board: Board, coord: Coord) : Result = {
    val cell = board.lookup (coord) 
    cell.ship match {
      case Some (piece) => {
        piece.sink()
        Hit
      }
      case _ => Miss
    }
  }
}
