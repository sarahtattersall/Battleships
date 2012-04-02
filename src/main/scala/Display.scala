//package Battleships
import swing._
import scala.collection.mutable._
import java.awt.Dimension
import java.awt.Color
class GridCell (s: Status) extends Button {
  preferredSize = new Dimension (50, 50)
  background = Color.RED
  text = "X"
  opaque = true
  def status = s
}

class Display (size: Int) extends MainFrame {
  def gridSize = size
  title = "Battleships"
  val gridPanel = new GridPanel(size, size){
    contents.insertAll (0, Buffer.tabulate(gridSize * gridSize)(x => new GridCell(Empty)))
  }
  contents = gridPanel
}