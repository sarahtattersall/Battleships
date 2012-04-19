import scala.collection.immutable.List
import scala.collection.mutable.StringBuilder

case class Cell (var ship: Option[ShipPiece] = None){
  override def toString = {
    ship match {
      case Some (piece) => piece.status.toString()
      case None => "_"
    }
  }
}

trait Board {
  val cells = Array.tabulate(size,size)((x,y) => new Cell())
  val size : Int
  
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
  
  def lookup (coord: Coord) : Cell = {
    cells(coord.x)(coord.y)
  }
}

case class PlayerBoard (size: Int) extends Board {
  def updateShip (coord: Coord, piece: ShipPiece) {
    cells(coord.x)(coord.y).ship = Some(piece)
  }
}

case class AIBoard (size: Int) extends Board {

}