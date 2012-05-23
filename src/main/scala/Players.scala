// trait PlayerType
// case object Human extends PlayerType
// case object Computer extends PlayerType

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

  def setupShip(size: Int, coordSpace: CoordinateSpace, board: Board) : Ship

  def guess(board: Board): Coord
}

case class AILogic() extends ShipLogic{
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
    } else {
      placeShip (size, start, direction, coordSpace, board)
    }
  }

  // Checks if x is within the board boundary
  private def inBoundary(board: Board, x: Int, offset: Int): Boolean = {
    val y = x + offset
    (y >= 0 && y < board.size)
  }  
}


// Uses Strategy Pattern
case class Player(private val board: Board, private val shipLogic: ShipLogic) {
  private var ships = List[Ship]()
  // def playerType(): PlayerType
  // def setupShip(size: Int, coordSpace: CoordinateSpace) : Unit
    
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

  // Creates a ship of size size begining at start and going in direction
  // direction.
  // Returns the new list ship :: ships
  // protected def placeShip (size: Int, s: Coord, direction: Vec, coordSpace: CoordinateSpace): List[Ship] = {
  //   var start = s
  //   val ship = new Ship(size)
  //   for (i <- 0 to size - 1){
  //     board.updateShip(start, ship.getPiece(i))
  //     start = coordSpace.move(start, direction)
  //   }
  //   (ship :: ships)
  // }
  
}

// TODO: Implement strategy design pattern?
// Template Design Pattern
// case class HumanPlayer(board: Board, io: IO) extends Player(board, io) {
//   def playerType (): PlayerType = Human
  
//   def guess (): Coord = {
//     io.getCoord(0, board.size)
  // }
  
  // TODO: Ensure can't place ontop of another ship, for now assume the user
  // doesn't
  // Creates a new ship and places it on the playerBoard
//   def setupShip (size: Int, coordSpace: CoordinateSpace) {
//     val sizeIndex = size - 1
//     println ("Placing your ship of size " + size)
//     val start = io.getCoord(0, board.size)
//     val direction = io.getVector()
//     if (!inBoundary(board, start.x, direction.i*sizeIndex) 
//         || !inBoundary(board, start.y, direction.j*sizeIndex)){
//       println ("Placing of ship here goes out of bounds, try again")
//       setupShip (size, coordSpace)
//     } else {
//       ships = placeShip (size, start, direction, coordSpace)
//     }
//   }

//   // Checks if x is within the board boundary
//   private def inBoundary(board: Board, x: Int, offset: Int): Boolean = {
//     val y = x + offset
//     (y >= 0 && y < board.size)
//   }  
// }
// case class AIPlayer(board: Board, io: IO) extends Player(board, io) {
  // var lastPlaced = 0

  // def playerType (): PlayerType = Computer

  // def guess (): Coord = {
  //   new Coord (0,0)
  // }
  
    
  // // TODO: Come up with an algorithm to place ships
  // // Places a ship on the board
  // def setupShip (size: Int, coordSpace: CoordinateSpace) {
  //   val dir = new Vec(0,1)
  //   ships = placeShip (size, new Coord(lastPlaced, 0), dir, coordSpace)
  //   lastPlaced += 1
  // }
// }
