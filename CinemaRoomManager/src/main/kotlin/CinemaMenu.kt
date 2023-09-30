import java.lang.IndexOutOfBoundsException

class CinemaMenu {
    //initializing all needed variables
    private var rows = 0
    private var rowSeats = 0
    private var chosenRow = 0
    private var chosenSeat = 0
    private var cinemaSize = 0
    private var freeSeat = 'S'
    private var bookedSeat = 'B'
    private var cinemaHall = MutableList(0) { MutableList(0) { freeSeat } }
    private var purchasedTickets = 0
    private var percentage = "0.00"
    private var currentIncome = 0
    private var totalIncome = 0

    // getting number of rows and sets from input and initializing current cinema hall
    // which is represented as mutable list
    private fun welcome() {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        rowSeats = readln().toInt()
        cinemaHall = MutableList(rows) { MutableList(rowSeats) { freeSeat } }
    }

    //interface for working with current cinema hall
    fun menu() {
        welcome()
        do {
            println("1. Show the seats")
            println("2. Buy a ticket")
            println("3. Statistics")
            println("0. Exit")
            when (readln()) {
                "1" -> cinemaState()
                "2" -> buy()
                "3" -> statistics()
                "0" -> return
            }
        } while (true)
    }

    //calculating total income of cinema hall
    //conditions: if current hall has 60 <= seats in total, each ticket is 10$
    //if total number of seats > 60, seats from row 1 to middle are 10$ each, the rest - 8$
    //if number of rows is uneven, seats from 1 row to (rows  - (rows % 2)) / 2 are 10$, and the rest are 8$
    private fun calculateIncome(rows: Int, seats: Int) {
        val hallSize = rows * seats
        if (hallSize <= 60) {
            totalIncome = hallSize * 10
            println("Total income: $$totalIncome")
        } else {
            val half1 = ((rows - rows % 2) / 2) * seats * 10
            val half2 = (rows - ((rows - rows % 2) / 2)) * seats * 8
            totalIncome = half1 + half2
            println("Total income: $$totalIncome")
        }

    }

    //tracking of purchased tickets
    private fun buy() {
        //getting coordinates of required seat
        println("Enter a row number:")
        chosenRow = readln().toInt()
        println("Enter a seat number in that row:")
        chosenSeat = readln().toInt()

       try {
           //checking if required seat is not free,
           // if user's input doesn't correspond to possible cinemaHall coordinates, exception is thrown
           // if the seat is not free program asks for input again
           if (cinemaHall[chosenRow - 1][chosenSeat - 1] == bookedSeat){
               println("That ticket has already been purchased!")
               buy()
           } else {
               // if previous condition is false program changes the value of the element according to chosen coordinates
               cinemaHall[chosenRow - 1][chosenSeat - 1] = bookedSeat
               //then program checks if seat is 10$ or 8$ and increments the income for statistics
               currentIncome += if (cinemaSize < 60 || chosenRow in 1..(rows - rows % 2) / 2) {
                   println("Ticket price: $10")
                   10
               } else {
                   println("Ticket price: $8")
                   8
               }
               //the number of purchased tickets is being incremented for statistics
               purchasedTickets += 1
           }
       } catch (e: IndexOutOfBoundsException){
                   println("Wrong input!")
       }
           //program calculates the percentage of sold seats out of total number of seats for statistics
            val percentPerTicket = (100 / cinemaSize.toDouble()) * purchasedTickets
            val formatPercentage = "%.2f".format(percentPerTicket)
            percentage = formatPercentage
    }


    //cinema scheme
    private fun cinemaState() {
        //calculating size of current cinema hall
        cinemaSize = rows * rowSeats

        println("Cinema:")

        //making a string to display each seat's number
        var firstRow = " "
        for (j in 1..rowSeats) {
            firstRow += " $j"
        }
        println(firstRow)

        //printing cinema hall scheme using an index
        for (i in 0..<cinemaHall.size) {
            println("${i + 1} ${cinemaHall[i].joinToString().replace(",", "")}")

        }
    }

    // displaying statistics
    private fun statistics(){
        println("Number of purchased tickets: $purchasedTickets")
        println("Percentage: $percentage%")
        println("Current income: \$$currentIncome")
        calculateIncome(rows, rowSeats)


    }
}