import scala.collection.immutable.List
import scala.collection.mutable.StringBuilder
trait BoardStatus

case class Cell (var ship: Option[ShipPiece] = None){
  override def toString = {
    ship match {
      case Some (piece) => piece.status.toString()
      case None => "_"
    }
  }
}

case class Board (size: Int) {
  var cells = Array.tabulate(size,size)((x,y) => new Cell())
  
  def updateShip (coord: Coord, piece: ShipPiece) {
    cells(coord.x)(coord.y).ship = Some(piece)
  }
  
  def lookup (coord: Coord) : Cell = {
    cells(coord.x)(coord.y)
  }
  
  override def toString = {
    var result = new StringBuilder("   " + Range(0, size).foldLeft("")((a,b) => a + b.toString() + ", "))
    
    result.append("\n")
    for (i <- 0 to size - 1){
      result.append(i)
      for (j <- 0 to size - 1){
        result.append(", " + cells(i)(j).toString())
      }
      result.append("\n")
    }
    result.toString()
  }
}