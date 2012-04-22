case class IO(){
  // Displays msg before getting integer input from the user. Tests to see if
  // lowerBound <= input <= upperBound, if not tries again
  def getBoundedIntegerInput (msg : String, lowerBound: Int, upperBound: Int): Int = {
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
}