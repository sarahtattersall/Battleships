import scala.collection.immutable.List
import scala.collection.mutable.StringBuilder
trait BoardStatus
case object Empty extends BoardStatus 
case object Occupied extends BoardStatus

case class Cell (var status : BoardStatus = Empty, var ship: Option[ShipPiece] = None){
  override def toString = {
    //ship.map((s :ShipPiece) ->
    ship match {
      case Some (piece) => piece.status.toString()
      case None => "_"
    }
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
    var result = new StringBuilder(Range(0, boardSize).foldLeft("")((a,b) => a + b.toString() + ", "))
    
    result.append("\n")
    for (i <- 0 to boardSize - 1){
      for (j <- 0 to boardSize - 1){
        result.append(cells(i)(j).toString() + ", ")
      }
      result.append("\n")
    }
    result.toString()
  }
}