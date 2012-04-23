abstract class Player(private val board: Board, private val io: IO) {
  protected var ships = List[Ship]()
  
  def guess(): Coord
    
  def getBoard (): Board = {
    board
  }
  
  def alive (): Boolean = {
    // TODO: WHy does _.sank() not work here?
    ships.exists((x) => !x.sank())
  }
  
  // Creates a ship of size size begining at start and going in direction
  // direction.
  // Returns the new list ship :: ships
  protected def placeShip (size: Int, s: Coord, direction: Vec, coordSpace: CoordinateSpace): List[Ship] = {
    var start = s
    val ship = new Ship(size)
    for (i <- 0 to size - 1){
      board.updateShip(start, ship.getPiece(i))
      start = coordSpace.move(start, direction)
    }
    (ship :: ships)
  }
  
}
case class HumanPlayer(board: Board, io: IO) extends Player(board, io) {
  
  def guess (): Coord = {
    io.getCoord(0, board.size)
  }
  
  // TODO: Ensure can't place ontop of another ship, for now assume the user
  // doesn't
  // Creates a new ship and places it on the playerBoard
  def setupShip (size: Int, coordSpace: CoordinateSpace) {
    val sizeIndex = size - 1
    println ("Placing your ship of size " + size)
    val start = io.getCoord(0, board.size)
    val direction = io.getVector()
    if (!inBoundary(board, start.x, direction.i*sizeIndex) 
        || !inBoundary(board, start.y, direction.j*sizeIndex)){
      println ("Placing of ship here goes out of bounds, try again")
      setupShip (size, coordSpace)
    } else {
      ships = placeShip (size, start, direction, coordSpace)
    }
  }

  // Checks if x is within the board boundary
  private def inBoundary(board: Board, x: Int, offset: Int): Boolean = {
    val y = x + offset
    (y >= 0 && y < board.size)
  }  
}
case class AIPlayer(board: Board, io: IO) extends Player(board, io) {

  def guess (): Coord = {
    new Coord (0,0)
  }
  
    
  // TODO: Come up with an algorithm to place ships
  // Creates a basic AIBoard with ships placed.
  def setupBoard (sizes: List[Int], coordSpace: CoordinateSpace) {
    val dir = new Vec(0,1)
    sizes.foldLeft(0)((acc, size) => 
      {
        ships = placeShip (size, new Coord(acc, 0), dir, coordSpace)
        acc + 1
       })
  }
}
