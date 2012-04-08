trait Result
case object Hit extends Result
case object Miss extends Result

class Rules (size: Int) {
  def board = new Board(size)
  var sizes = Array(5, 4, 3, 3, 2)
  def fleet = Array.tabulate(sizes.size)(x => new Ship(sizes(x)))
  
  def guess (coord: Coord) : Result = {
    val cell = board.lookup (coord) 
    cell.status match {
      case Occupied => {
        cell.ship.map((s: Ship) => s.status = Alive)
        Hit
      }
      case _ => Miss
    } 
  }
  // TODO:
  // Cant decide if should place all individual components or 
  // have placeShip (x: int, y: Int, size: Int, direction: Direction)
  def placeShip (coord: Coord, vector: Vec, ship: Ship) : Boolean = {
    board.lookup (coord).status match {
      case Empty => {
        board.updateStatus (coord, Occupied)
        board.updateShip (coord, ship)
        return ship.add (coord)
      }
      case _ => false 
    }
  }
}
