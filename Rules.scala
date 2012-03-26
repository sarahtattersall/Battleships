package Battleships;
trait Result
case object Hit extends Result
case object Miss extends Result

class Rules {
  val board = new Board(20)

  def guess (x: Int, y: Int) : Result = {
    board.lookup (x, y) match {
      case Empty => Miss
      case Dead => Miss
      case Alive => {
        board.update (x, y, Dead)
        Hit
      }
    } 
  }
}