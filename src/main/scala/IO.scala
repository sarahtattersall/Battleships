case class IO(){
  // Displays msg before getting integer input from the user. Tests to see if
  // lowerBound <= input <= upperBound, if not tries again
  private def getBoundedIntegerInput (msg : String, lowerBound: Int, upperBound: Int): Int = {
    println (msg)
    try { 
      val res = Console.readInt()    
      if (lowerBound <= res && upperBound >= res){
        res
      }
      else {
        getBoundedIntegerInput ("Not within bounds, try again", lowerBound, upperBound)
      }
    }
    catch {
      case e : java.lang.NumberFormatException => 
        getBoundedIntegerInput ("Bad Input, try again", lowerBound, upperBound)
    }
  }
  
  // Gets user input to create a Coord with the min and max bounds
  def getCoord(min: Int, max: Int): Coord = {
    val x = getBoundedIntegerInput ("Please enter x coord", min, max)
    val y = getBoundedIntegerInput ("Please enter y coord", min, max)
    new Coord (x,y)
  }
  
  // Gets user input to create a Vector
  def getVector(): Vec = {
    val i = getBoundedIntegerInput ("Please enter x direction", -1, 1)
    val j = getBoundedIntegerInput ("Please enter y direction", -1, 1)
    if (i == 0 && j == 0) {
      println ("Error that isn't a proper direction, try again")
      return getVector()
    }
    new Vec (i,j)
  }
}