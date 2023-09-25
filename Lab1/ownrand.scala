object MainOwn {
  def main(args: Array[String]): Unit = {
    print("Enter the string: ")
    var numberString = ""
    val numNum = 50
    numberString = scala.io.StdIn.readLine()
    if ((numberString.length() < 5)) {
      println("Input too small");
      sys.exit(0)
    }

    var p: Int = 0;
    var g: Int = 0;

    for (i <- 1 to 5) {
      if (i == 1) {
        p += numberString.charAt(0).toInt
      } else {
        p *= numberString.charAt(i - 1).toInt
      }
    }

    if (p < 0) p *= -1;

    var arr: Array[Int] = Array.fill(10)(0)

    for (i <- 1 to numNum) {
      var a = p.toString().substring(0, 5).toInt
      var b = p.toString().reverse.substring(0, 5).toInt
      var rezf = (a * b)
      if(rezf == 0) {
        rezf ={
          if(a == 0) b;
          else a
        }
      }
      var rez = rezf.toString().reverse.substring(0, 3).toFloat / 1000
      if (b == 0 || b == 1) b = a * 2;
      p *= b

      var flag = true

      while (flag) {
        g = (rez / 0.1f).toInt
        if (arr(g) >= numNum / 10) {
          rez = ((rez + 0.1f) * 1000).round.toFloat / 1000
          if (rez >= 1) rez -= 1
        } else {
          println("rez = " + rez)
          arr(g) += 1
          flag = false
        }
      }

      if (p < 0) {
        p = ("0" + p.toString().substring(2)).toInt
      }
      var t = p.toString()
      while ((t.length() < 6)) {
        t = "1" + t
      }
      p = t.toInt
    }

    println("---------------------")

    for (i <- 0 to 9) {
      println("arr(" + i + ") = " + arr(i))
    }

    println("---------------------")
  }
}
