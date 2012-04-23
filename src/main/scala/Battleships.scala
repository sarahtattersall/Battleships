object BattleShips {
    var size = 10
    def main (args: Array[String]) {
        println("Welcome to battle ships!")
        val io = new IO()
        val rules = new Rules(size, io)
        rules.setup()
        rules.play()
    }
}