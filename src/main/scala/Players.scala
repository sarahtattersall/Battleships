abstract class Player(private val boardSize: Int) {
  def guess(): Coord
}
case class HumanPlayer(boardSize: Int) extends Player(boardSize) {
  private val io = new IO()
  def guess (): Coord = {
    io.getCoord(0, boardSize)
  }
}
case class AIPlayer(boardSize: Int) extends Player(boardSize) {
  def guess (): Coord = {
    new Coord (0,0)
  }
}
