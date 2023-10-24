import scala.util.matching.Regex

object Main28 {

  def vowelsCount(): Unit = {
    print("Input text: ")
    var text: String = scala.io.StdIn.readLine()
    var regex: Regex = """[aeiouyAEIOUY]""".r
    var matches = regex.findAllMatchIn(text)
    println("Vowels count: " + matches.size)
  }

  def latinToNums(): Unit = {
    var text: String = "Hello to everybody"
    println("Source: " + text)
    var regex: Regex = """[a-zA-Z]""".r
    var newText: String =
      regex.replaceAllIn(text, { c => (c.toString + math.floor(math.random * 10).toInt).toString.substring(1) })
    println("Result: " + newText)
  }

  def getExtWords(): Unit = {
    var text: String = "When executing the exercise extract all extra words"
    println("Source: " + text)
    var regex: Regex = """\bext\w+\b""".r
    var matches = regex.findAllMatchIn(text)
    println("Results:")
    for (m <- matches) {
      println(m.group(0))
    }
  }

  def theToA(): Unit = {
    var text: String = "A big round ball fall to the ground"
    println("Source: " + text)
    var regex: Regex = """\bthe\b""".r
    var newText: String = regex.replaceAllIn(text, "a")
    println("Result: " + newText)
  }

  def reverseText(): Unit = {
    var text: String = "A big round ball fall to the ground"
    println("Source: " + text)
    var regex: Regex = """\b\w+\b""".r
    var words = regex.findAllMatchIn(text).map(_.group(0))

    var newText: String = ""

    for (word <- words) {
      newText = word + " " + newText
    }
    println("Result: " + newText)
  }

  def removeVowels(): Unit = {
    var text: String = "Hello to everybody"
    println("Source: " + text)
    var regex: Regex = """[aeiouyAEIOUY]""".r
    var newText = regex.replaceAllIn(text, "")
    println("Result: " + newText)
  }

  def doubleSymbol(): Unit = {
    var text: String = "Hello to everybody"
    println("Source: " + text)
    var regex: Regex = """\w""".r
    var newText: String = regex.replaceAllIn(text, { m => m.group(0) + m.group(0) })
    println("Result: " + newText)
  }

  def tripleLastLetter(): Unit = {
    var text: String = "The local landscape is so incredibly fascinating"
    println("Source: " + text)
    var regex: Regex = """\w\b""".r
    var newText: String = regex.replaceAllIn(text, { m => m.group(0) + m.group(0) + m.group(0) })
    println("Result: " + newText)
  }

  def removeAllY(): Unit = {
    var text: String = "Hello to everybody"
    println("Source: " + text)
    var regex: Regex = """y""".r
    var newText: String = regex.replaceAllIn(text, "")
    println("Result: " + newText)
  }

  def removeAllSpec(): Unit = {
    var text: String = "^&Th@(%#&i$$s)#^$([]) #f$^**ore}!#st@#(*%^) i#^s be@$*(^au*@#:]tif*@(&!u<(;::)>l"
    println("Source: " + text)
    var regex: Regex = """[^a-zA-Z0-9 ]""".r
    var newText: String = regex.replaceAllIn(text, "")
    println("Result: " + newText)
  }

  def heartness(): Unit = {
    var text: String = "Hello to everybody"
    println("Source: " + text)
    var regex: Regex = """\bto\b""".r
    var newText: String = regex.replaceAllIn(text, "with heartness to")
    println("Result: " + newText)
  }

  def main(args: Array[String]): Unit = {
    var flagloop = true
    var tasknum = 0

    while (flagloop) {
      var flag = true
      while (flag) {
        println("----------------------------")
        println("0 - Quit")
        println("1 - Letters to Numbers")
        println("2 - Find ext...")
        println("3 - The to A")
        println("4 - Reverse Text")
        println("5 - Remove Vowels")
        println("6 - Duplicate Symbols")
        println("7 - Remove Y")
        println("8 - With Heartness")
        println("9 - Count Vowels")
        println("10 - Triple Last Letters")
        println("11 - Remove Special Symbols")
        println("----------------------------")
        print("Enter the task number (0 - 11): ")
        var numberString = ""
        numberString = scala.io.StdIn.readLine()
        try {
          flag = false
          tasknum = numberString.toInt
        } catch {
          case ex: NumberFormatException => {
            println("Incorrect input");
            flag = true
          }
        }
        if (!flag) {
          if (tasknum < 0 || tasknum > 11) {
            println("Number should be from 0 to 11");
            flag = true
          }
        }
      }

      if (tasknum == 0) {
        flagloop = false
      }

      if (tasknum == 1) {
        latinToNums()
      }

      if (tasknum == 2) {
        getExtWords()
      }

      if (tasknum == 3) {
        theToA()
      }

      if (tasknum == 4) {
        reverseText()
      }

      if (tasknum == 5) {
        removeVowels()
      }

      if (tasknum == 6) {
        doubleSymbol()
      }

      if (tasknum == 7) {
        removeAllY()
      }

      if (tasknum == 8) {
        heartness()
      }

      if (tasknum == 9) {
        vowelsCount()
      }

      if (tasknum == 10) {
        tripleLastLetter()
      }

      if (tasknum == 11) {
        removeAllSpec()
      }

    }
  }
}
