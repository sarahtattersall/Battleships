import scala.collection.immutable.List
trait BoardStatus
case object Empty extends BoardStatus
case object Occupied extends BoardStatus

case class Cell (var status : BoardStatus = Empty, var ship: Option[ShipPiece] = None){
  override def toString = {
    "Hi"
  }
}

class Board (size: Int) {
  def boardSize = size
  var cells = Array.tabulate(size,size)((x,y) => new Cell())
  
  def updateShip (coord: Coord, piece: ShipPiece) {
    cells(coord.x)(coord.y).ship = Some(piece)
  }
  
  def updateStatus (coord: Coord, status: BoardStatus) {
    cells(coord.x)(coord.y).status = status
  }

  def lookup (coord: Coord) : Cell = {
    cells(coord.x)(coord.y)
  }
  
  override def toString = {
    
    "Hello"
  }
}