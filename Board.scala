object Status extends Enumeration {
  type Status = Value
  val EMPTY, ALIVE, DEAD = Value
}
import Status._
class Cell (s : Status){
  def status = s
}
class Board (size: Int) {
  def boardSize = size
  var cells = Array.tabulate(size,size)((x,y) => new Cell(EMPTY))
  
}