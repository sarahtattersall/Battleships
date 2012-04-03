trait Result
case object Hit extends Result
case object Miss extends Result

class Rules (size: Int) {
  val board = new Board(size)

  def guess (coord: Coord) : Result = {
    val cell = board.lookup (coord) 
    cell.status match {
      case Occupied => {
        cell.ship.status = Alive
        Hit
      }
      case _ => Miss
    } 
  }
  // TODO:
  // Cant decide if should place all individual components or 
  // have placeShip (x: int, y: Int, size: Int, direction: Direction)
  def placeShip (coord: Coord, ship: Ship) : Boolean = {
    board.lookup (coord).status match {
      case Empty => {
        board.updateStatus (coord, Occupied)
        board.updateShip (coord, ship)
        true
      }
      case _ => false 
    }
  }
}