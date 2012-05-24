abstract class ShipLogic() {
  // Creates a ship of size size begining at start and going in direction
  // direction.
  // Returns the newly created ship
  protected def placeShip (size: Int, s: Coord, direction: Vec, coordSpace: CoordinateSpace, board: Board): Ship = {
    var start = s
    val ship = new Ship(size)
    for (i <- 0 to size - 1){
      board.updateShip(start, ship.getPiece(i))
      start = coordSpace.move(start, direction)
    }
    ship
  }

  // Setup ship on given board
  def setupShip(size: Int, coordSpace: CoordinateSpace, board: Board) : Ship

  // Take a guess on the provided board
  def guess(board: Board): Coord
}

case class AILogic() extends ShipLogic {
  var lastPlaced = 0

  def guess (board: Board): Coord = {
    new Coord (0,0)
  }

  // TODO: Come up with an algorithm to place ships
  // Places a ship on the board
  def setupShip (size: Int, coordSpace: CoordinateSpace, board: Board): Ship = {
    val dir = new Vec(0,1)
    val ship = placeShip (size, new Coord(lastPlaced, 0), dir, coordSpace, board)
    lastPlaced += 1
    ship
  }

}

case class HumanLogic(private val io: IO) extends ShipLogic {
  def guess (board: Board): Coord = {
    io.getCoord(0, board.size)
  }

  // TODO: Ensure can't place ontop of another ship, for now assume the user
  // doesn't
  // Creates a new ship and places it on the playerBoard
  def setupShip (size: Int, coordSpace: CoordinateSpace, board: Board): Ship = {
    val sizeIndex = size - 1
    println ("Placing your ship of size " + size)
    val start = io.getCoord(0, board.size)
    val direction = io.getVector()
    if (!inBoundary(board, start.x, direction.i*sizeIndex) 
        || !inBoundary(board, start.y, direction.j*sizeIndex)){
      println ("Placing of ship here goes out of bounds, try again")
      setupShip (size, coordSpace, board)
    } else if (alreadyPlaced(size, start, direction, coordSpace, board)) {
      println ("Placing ship ontop of an already placed ship, try again")
      setupShip (size, coordSpace, board)
    } else {
      placeShip (size, start, direction, coordSpace, board)
    }
  }

  protected def alreadyPlaced (size: Int, start: Coord, direction: Vec, coordSpace: CoordinateSpace, board: Board): Boolean = {
    var coord = start
    for (i <- 0 to size - 1){
      // TODO: How to avoid warning just have if
      board.lookup(start).ship match {
        case Some (piece) => return false
      }
    }
    false
  }

  // Checks if x is within the board boundary
  private def inBoundary(board: Board, x: Int, offset: Int): Boolean = {
    val y = x + offset
    (y >= 0 && y < board.size)
  }  
}


// Uses Strategy Pattern for ship placing and guessing algorithm.
case class Player(private val board: Board, private val shipLogic: ShipLogic) {
  private var ships = List[Ship]()
    
  def getBoard (): Board = {
    board
  }
  
  def alive (): Boolean = {
    // TODO: WHy does _.sank() not work here?
    ships.exists((x) => !x.sank())
  }
  

  def guess(): Coord = {
    shipLogic.guess(board)
  }

  def setupShip(size: Int, coordSpace: CoordinateSpace) = {
    ships = shipLogic.setupShip(size, coordSpace, board) :: ships
  }
}
