import swing._
import scala.collection.mutable.Buffer
//import scala.collection.immutable.Vector
import scala.collection.immutable.List
import java.awt.{Dimension, Color}
import java.awt.event.MouseAdapter
// import java.awt.Color
// import java.awt.event.MouseAdapter
import javax.swing.table.DefaultTableModel
import javax.swing.JTable

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
  val table = new Table(size,size){
    preferredSize = new Dimension (200, 200)
    showGrid = true
  }
  contents = new ScrollPane (table){
    import java.util.Vector
    //peer.setRowHeaderView (new Table (Array.tabulate(gridSize,1)((x,y) => x), Seq(1)))
    //val headers = new Vector[Int]()
    //for (i <- 0 to gridSize){
    //  headers.add (i)
    //}
    //val v: java.util.Vector[Int] = (Vector.tabulate(gridSize)((x) => x))
    val model = new DefaultTableModel (0, 1)
    for (i <- 0 to gridSize){
      model.addRow (new Vector[String] (){
        add ("1")
      })
    }
    peer.setRowHeaderView (new JTable (new DefaultTableModel (gridSize, 1)))
  }
}