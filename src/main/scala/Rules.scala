trait Result
case object Hit extends Result
case object Miss extends Result

class Rules (size: Int) {
  val board = new Board(size)

  def guess (x: Int, y: Int) : Result = {
    board.lookup (x, y) match {
      case Alive => {
        board.update (x, y, Dead)
        Hit
      }
      case _ => Miss
    } 
  }
  // TODO:
  // Cant decide if should place all individual components or 
  // have placeShip (x: int, y: Int, size: Int, direction: Direction)
  def placeShip (x: Int, y: Int) : Boolean = {
    board.lookup (x, y) match {
      case Empty => {
        board.update (x, y, Alive)
        true
      }
      case _ => false 
    }
  }
}