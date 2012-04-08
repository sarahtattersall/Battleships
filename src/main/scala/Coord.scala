case class Coord (x: Int, y: Int)
case class Vec (i: Int, j: Int)
case class CoordinateSpace (width: Int, height: Int){
  // TODO: Investigate private methods, and a better way to do this
  def mod (number: Int, modValue: Int) : Int = {
    var x = (number % modValue) 
    if (0 > x){
      x += modValue
    }
    x
  }
  def move (coord: Coord, vector: Vec) : Coord = {
    val x = mod(coord.x + vector.i, width);
    val y = mod(coord.y + vector.j, height);
    new Coord (x, y)
  }
  def move (vector: Vec, coord: Coord) : Coord = {
    move (coord, vector)
  }
}