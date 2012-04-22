object BattleShips {
    var size = 10
    def main (args: Array[String]) {
        println("Welcome to battle ships!")
        val rules = new Rules(size)
        rules.setup()
        rules.play()
    }
}