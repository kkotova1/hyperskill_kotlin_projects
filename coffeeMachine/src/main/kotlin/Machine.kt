class Machine {
    private var water: Int = 400
    private var milk: Int = 540
    private var coffeeBeans: Int = 120
    private var disposableCups: Int = 9
    private var money: Int = 550

    fun menu() {
        do {
            println("Write action (buy, fill, take, remaining, exit):")
            when (readln()) {
                "buy" -> buy()
                "fill" -> fill()
                "take" -> takeMoney()
                "remaining" -> remaining()
                "exit" -> break
            }
        } while (true)
    }

    private fun isEnough(waterRequired: Int = 0, milkRequired: Int = 0, coffeeBeansRequired: Int = 0): String {
        return when {
            waterRequired > water -> "Sorry, not enough water!"
            milkRequired > milk -> "Sorry, not enough milk!"
            coffeeBeansRequired > coffeeBeans -> "Sorry, not enough coffee beans!"
            else -> "I have enough resources, making you a coffee!"
        }
    }

    private fun buy() {
        val message: String

        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        when (readln()) {
            "1" -> {
                message = isEnough(waterRequired = 250, coffeeBeansRequired = 16)
                println(message)
                when {
                    message != "I have enough resources, making you a coffee!" -> return
                    else -> {
                        money += 4
                        disposableCups -= 1
                        water -= 250
                        coffeeBeans -= 16
                    }
                }
            }
            "2" -> {
                message = isEnough(waterRequired = 350, milkRequired = 75, coffeeBeansRequired = 20)
                println(message)
                when {
                    message != "I have enough resources, making you a coffee!" -> return
                    else -> {
                        money += 7
                        disposableCups -= 1
                        water -= 350
                        milk -= 75
                        coffeeBeans -= 20
                    }
                }
            }
            "3" -> {
                message = isEnough(waterRequired = 200, milkRequired = 100, coffeeBeansRequired = 12)
                println(message)
                when {
                    message != "I have enough resources, making you a coffee!" -> return
                    else -> {
                        money += 6
                        disposableCups -= 1
                        water -= 200
                        milk -= 100
                        coffeeBeans -= 12
                    }
                }
            }
            "back" -> return
        }
    }

    private fun fill() {
        println("Write how many ml of water do you want to add:")
        water += readln().toInt()
        println("Write how many ml of milk do you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans do you want to add:")
        coffeeBeans += readln().toInt()
        println("Write how many disposable cups of coffee do you want to add:")
        disposableCups += readln().toInt()
    }

    private fun takeMoney() {
        println("I gave you $$money")
        money = 0
    }

    private fun remaining() {
        println("""
            The coffee machine has:
            $water ml of water
            $milk ml of milk
            $coffeeBeans g of coffee beans
            $disposableCups disposable cups
            $$money of money
        """.trimIndent())
    }

}
