trait Result
case object Hit extends Result
case object Miss extends Result

class Rules (size: Int) {
  private val io = new IO()
  private val playerBoard = new Board(size)
  private val AIBoard = new Board(size)
  private val coordSpace = new CoordinateSpace(size, size)
  private val sizes = List(5, 4, 3, 3, 2)
  private val fleet = Array.tabulate(sizes.size)(x => new Ship(sizes(x)))
  private var playerShips = List[Ship]()
  private var AIShips = List[Ship]()
  createAIBoard()
  
  // Displays the players board and then calls setupShip for each
  // of the available ships with sizes dictated in sizes
  def setup () {
    println (playerBoard.toString())
    sizes.foreach(setupShip(_))
  }
  
  
  def play(){
    // TODO: WHy does _.sank() not work here?
    while (playerShips.exists((x) => !x.sank()) && AIShips.exists((x) => !x.sank())){
      
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
  
  // Checks if x is within the board boundary
  private def inBoundary(board: Board, x: Int, offset: Int): Boolean = {
    val y = x + offset
    (y >= 0 && y < board.size)
  }
  
  // TODO: Ensure can't place ontop of another ship, for now assume the user
  // doesn't
  // Creates a new ship and places it on the playerBoard
  private def setupShip (size: Int) {
    val sizeIndex = size - 1
    println ("Placing your ship of size " + size)
    val start = io.getCoord(0, playerBoard.size)
    val direction = io.getVector()
    if (!inBoundary(playerBoard, start.x, direction.i*sizeIndex) 
        || !inBoundary(playerBoard, start.y, direction.j*sizeIndex)){
      println ("Placing of ship here goes out of bounds, try again")
      setupShip (size)
    } else {
      playerShips = placeShip(playerBoard, playerShips, size, start, direction)
      println(playerBoard.toString())
    }
  }
  
  // Creates a ship of size size begining at start and going in direction
  // direction.
  // Returns the new list ship :: ships
  private def placeShip (board: Board, ships: List[Ship], size: Int, s: Coord, direction: Vec): List[Ship] = {
    var start = s
    val ship = new Ship(size)
    for (i <- 0 to size - 1){
      board.updateShip(start, ship.getPiece(i))
      start = coordSpace.move(start, direction)
    }
    (ship :: ships)
  }
  
  // TODO: Come up with an algorithm to place ships
  // Creates a basic AIBoard with ships placed.
  private def createAIBoard () {
    val dir = new Vec(0,1)
    sizes.foldLeft(0)((acc, size) => 
      {
        AIShips = placeShip(AIBoard, AIShips, size, new Coord(acc, 0), dir)
        acc + 1
       })
    println("AI BOARD")
    println(AIBoard.toString())
  }
}
