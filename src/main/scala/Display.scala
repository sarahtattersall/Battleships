import swing._
import scala.collection.mutable.Buffer
import java.awt.Dimension
import java.awt.Color
import java.awt.event.MouseAdapter

case class GridCell (var status: BoardStatus = Empty) extends Button {
  preferredSize = new Dimension (20, 20)
  background = Color.RED
  text = "X"
  opaque = true
  listenTo (mouse.clicks)
  reactions += {
    case mouseClicked => {
      text = "S"
      println ("Clicked")
    }
  }
}

class Display (size: Int) extends MainFrame {
  def gridSize = size
  title = "Battleships"
  val gridPanel = new GridPanel(size, size){
    contents.insertAll (0, Buffer.tabulate(gridSize * gridSize)(x => new GridCell()))
  }
  contents = gridPanel
}