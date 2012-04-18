trait Result
case object Hit extends Result
case object Miss extends Result

class Rules (size: Int) {
  val board = new Board(size)
  val coordSpace = new CoordinateSpace(size, size)
  val sizes = List(5, 4, 3, 3, 2)
  val fleet = Array.tabulate(sizes.size)(x => new Ship(sizes(x)))
  
  def guess (coord: Coord) : Result = {
    val cell = board.lookup (coord) 
    cell.status match {
      case Occupied => {
        cell.ship.map((s: ShipPiece) => s.status = Sunk)
        Hit
      }
      case _ => Miss
    } 
  }
  // TODO:
  // Cant decide if should place all individual components or 
  // have placeShip (x: int, y: Int, size: Int, direction: Direction)
  def placePiece (coord: Coord, vector: Vec, piece: ShipPiece) : Boolean = {
    board.lookup (coord).status match {
      case Empty => {
        board.updateStatus (coord, Occupied)
        board.updateShip (coord, piece)
         true
      }
      case _ => false 
    }
  }
  
  def getInput (msg : String, lowerBound: Int, upperBound: Int): Int = {
    println (msg)
    try { 
      val res = Console.readInt()    
      if (lowerBound <= res && upperBound >= res){
        res
      }
      else {
        getInput ("Not within bounds, try again", lowerBound, upperBound)
      }
    }
    catch {
      case e : java.lang.NumberFormatException => 
        getInput ("Bad Input, try again", lowerBound, upperBound)
    }

  }
  
  def setup () {
    println (board.toString())
    sizes.foreach(setupShip(_))
  }
  
  //TODO
  // Ensure x, y can't cause wrap around and ensure vec cant be 0, 0 
  def setupShip (size: Int) {
    val ship = new Ship(size)
    println ("Placing your ship of size " + size)
    val x = getInput ("Please enter x coord", 0, board.boardSize)
    val y = getInput ("Please enter y coord", 0, board.boardSize)
    val i = getInput ("Please enter x direction", -1, 1)
    val j = getInput ("Please enter y direction", -1, 1)
    var coord = new Coord(x, y)
    val vec = new Vec(i, j)
    for (i <- 0 to size - 1){
      board.updateShip(coord, ship.getPiece(i))
      coord = coordSpace.move(coord, vec)
    }
    println (board.toString())
  }
}
