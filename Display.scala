import swing._
import scala.collection.mutable._
import java.awt.Dimension
class Display (size: Int) extends MainFrame {
  def gridSize = size
  title = "Battleships"
  var dim = new Dimension (20, 20)
  val gridPanel = new GridPanel(size, size){
    contents.insertAll (0, Buffer.tabulate(gridSize * gridSize)(x => new Button(){
      preferredSize = dim
    }))
  }
  contents = gridPanel
}