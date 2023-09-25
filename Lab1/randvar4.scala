object Main26 {
  def main(args: Array[String]): Unit = {
    print("Enter the number: ")
    var a = 0
    var b = 0
    var numberString = ""
    var rez: Float = 0.0
    var z: Long = 0
    numberString = scala.io.StdIn.readLine()
    if ((numberString.length() < 16)) {
      println("Input too small");
      sys.exit(0)
    }

    for (i <- 1 to 5) {
      if (i == 1) {
        try {
          a = numberString.substring(0, 8).toInt
          b = numberString.substring(8, 16).toInt
          z = numberString.substring(0, 16).toLong
        } catch {
          case ex: NumberFormatException => {
            println("Incorrect input");
            sys.exit(0)
          }
        }
      } else {
        a = numberString.substring(0, 8).toInt
        b = numberString.substring(8, 16).toInt
      }

      var c = a * b
      if(c < 0) c *= -1
      rez = c.toString().substring(0, 3).toFloat / 1000
      println("result=" + rez);
      println("z=" + z)
      z *= c
      numberString = z.toString().substring(0, 16)
      if ((numberString.charAt(0) == '-')) {
        numberString = "0" + numberString.substring(2);
      }
      while ((numberString.length() < 16)) {
        numberString = "1" + numberString
      }
      z = numberString.toLong
    }
  }
}
