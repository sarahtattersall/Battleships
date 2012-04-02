//package Battleships
trait Status
case object Empty extends Status
case object Alive extends Status
case object Dead extends Status

class Cell (s : Status){
  var status = s
}

class Board (size: Int) {
  def boardSize = size
  var cells = Array.tabulate(size,size)((x,y) => new Cell(Empty))
  def update (x: Int, y: Int, s : Status) {
    cells(x)(y).status = s
  }

  def lookup (x: Int, y: Int) : Status = {
    cells(x)(y).status
  }
}