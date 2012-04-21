import scala.collection.mutable.HashMap
import scala.collection.mutable.LinkedList
trait ShipStatus
case object Alive extends ShipStatus {
  override def toString = "A"
}
case object Sunk extends ShipStatus {
  override def toString = "S"
}

case class Ship (var size: Int, var status: ShipStatus = Alive){
  private var ships = List[ShipPiece]()
  for (i <- 0  to size){
    ships = new ShipPiece(this) :: ships
  }
  
  //TODO: Bounds checking
  def getPiece (index: Int): ShipPiece = {
    ships(index)
  }
}

case class ShipPiece (private val ship: Ship, private var status: ShipStatus = Alive) {
  def sink() {
    status = Sunk
  }
  
  override def toString(): String = {
    status.toString()
  }
}