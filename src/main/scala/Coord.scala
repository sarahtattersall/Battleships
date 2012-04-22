case class Coord (x: Int, y: Int)
case class Vec (i: Int, j: Int)
case class CoordinateSpace (private val width: Int, private val height: Int){
  // Returns a new coord moved in direction vector
  def move (coord: Coord, vector: Vec) : Coord = {
    val x = coord.x + vector.i
    if (x < 0 || x >= width)
      throw new OutOfBoundsException ()
    val y = coord.y + vector.j
    if (y < 0 || y >= height)
      throw new OutOfBoundsException ()
    new Coord (x, y)
  }
  
  def move (vector: Vec, coord: Coord) : Coord = {
    move (coord, vector)
  }
}

case class OutOfBoundsException () extends Exception