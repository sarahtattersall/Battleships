object BattleShips {
    var size = 20
    val display = new Display(size);
    def main (args: Array[String]){
        println("Welcome to battle ships!")
        display.open()
    }
}