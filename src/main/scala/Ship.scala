import scala.collection.mutable.HashMap
trait ShipStatus
case object Alive extends ShipStatus
case object Sunk extends ShipStatus

import scala.collection.mutable.LinkedList
trait componentStatus
case object Healthy extends componentStatus
case object Wounded extends componentStatus

case class Ship (var size: Int, var status: ShipStatus = Alive){
  //var coords = new HashMap[Coord, componentStatus] ()
  var ships = new LinkedList[ShipPiece]()
  
  def add (piece: ShipPiece) {
    //TODO Is this Scala-esque?
    ships = ships :+ piece
  }
  
  /*def add (coord: Coord, status: componentStatus = Healthy) : Boolean = {
    if (coords.size < size){
      coords += ((coord, status))
      return true
    }
    return false
  }
  
  def update (coord: Coord, status: componentStatus) : Boolean = {
    coords.get (coord) match {
      case Some(x) => {
        coords.update (coord, status)
        true
      }
      case _ => false
    }
  }*/
}

case class ShipPiece (ship: Ship, var status: ShipStatus = Alive){
  ship.add (this)
}