object BattleShips {
    var size = 10
    val display = new Display(size);
    def main (args: Array[String]) {
        println("Welcome to battle ships!")
        display.open()
    }
}