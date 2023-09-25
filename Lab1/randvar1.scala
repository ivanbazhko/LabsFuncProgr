object Main25 {
  def main(args: Array[String]): Unit = {
    print("Enter the number: ")

    var numberString = ""
    var a = 0
    var b = 0
    var c = 0
    var rez: Float = 0.0
    var z = 0
    numberString = scala.io.StdIn.readLine()
    if ((numberString.length() < 10)) {
      println("Input too small");
      sys.exit(0)
    }

    for (i <- 1 to 5) {
      if (i == 1) {
        try {
          a = numberString.substring(0, 5).toInt
          b = numberString.substring(5, 10).toInt
        } catch {
          case ex: NumberFormatException => {
            println("Incorrect input");
            sys.exit(0)
          }
        }
      } else {
        a = numberString.substring(0, 5).toInt
        b = numberString.substring(5, 10).toInt
      }
      
      c = a * b
      rez = c.toString().substring(0, 3).toFloat / 1000
      z += c
      println("result=" + rez);
      println("z=" + z)
      numberString = z.toString()
      if ((numberString.charAt(0) == '-')) {
        numberString = "0" + numberString.substring(2);
      }
      while ((numberString.length() < 10)) {
        numberString = "1" + numberString
      }
    }
  }
}
