trait BoardStatus
case object Empty extends BoardStatus
case object Occupied extends BoardStatus

// TODO:
// Investigate a better way than passing null
case class Cell (var status : BoardStatus = Empty, var ship: Ship = null)

class Board (size: Int) {
  def boardSize = size
  var cells = Array.tabulate(size,size)((x,y) => new Cell())
  
  def updateShip (coord: Coord, ship: Ship) {
    cells(coord.x)(coord.y).ship = ship
  }
  
  def updateStatus (coord: Coord, status: BoardStatus) {
    cells(coord.x)(coord.y).status = status
  }

  def lookup (coord: Coord) : Cell = {
    cells(coord.x)(coord.y)
  }
}