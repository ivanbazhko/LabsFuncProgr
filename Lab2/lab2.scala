object Main27 {

  def sumList(lst: List[Int]): Int = {
    if (lst.isEmpty) 0
    else if (lst.head > 10) sumList(lst.tail)
    else lst.head + sumList(lst.tail)
  }

  def sumListLim(lst: List[Int], number: Int): Int = {
    if (lst.isEmpty) 0
    else if (number == 0) 0
    else lst.head + sumListLim(lst.tail, number - 1)
  }

  def checkSort(lst: List[Int], number: Int): Boolean = {
    if (lst.isEmpty) true
    else if (number > lst.head) false
    else checkSort(lst.tail, lst.head)
  }

  def maxList(lst: List[Int], maxval: Int): Int = {
    if (lst.isEmpty) maxval
    else if (lst.head > maxval) maxList(lst.tail, lst.head)
    else maxList(lst.tail, maxval)
  }

  def findIndex(lst: List[Int], elem: Int, index: Int): Int = {
    if (lst.isEmpty) -1
    else if (lst.head == elem) index
    else findIndex(lst.tail, elem, index + 1)
  }

  def checkEquals(lst: List[Int], number: Int): Boolean = {
    if (lst.isEmpty) false
    else if (lst.head == number) true
    else checkEquals(lst.tail, number)
  }

  def checkSame(lst: List[Int]): Boolean = {
    if (lst.isEmpty) false
    else if (checkEquals(lst.tail, lst.head)) true
    else checkSame(lst.tail)
  }

  def countUnevenPos(lst: List[Int], count: Int = 1): Int = {
    if (lst.isEmpty) 0
    else if (count == 1) lst.head + countUnevenPos(lst.tail, 0)
    else countUnevenPos(lst.tail, 1)
  }

  def rewrList(lst1: List[Int], lst2: List[Int]): List[Int] = {
    if (lst1.isEmpty) lst2
    else {
      rewrList(lst1.tail, lst2 ++ List(lst1.head))
    }
  }

  def mergeLists(lst1: List[Int], lst2: List[Int]): List[Int] = {
    if (lst1.isEmpty) lst2
    else if (lst2.isEmpty) lst1
    else {
      var list3: List[Int] = List.fill(0)(0)
      var res_p = rewrList(lst1, list3)
      var res_f = rewrList(lst2, res_p)
      res_f
    }
  }

  def entList(): List[Int] = {
    var fl1 = true
    var nlst: Int = 0
    while (fl1) {
      fl1 = false
      println("Enter the number: ")
      try {
        nlst = scala.io.StdIn.readLine().toInt
      } catch {
        case ex: NumberFormatException => {
          println("Incorrect input");
          fl1 = true
        }
      }
    }
    println("Enter the list: ")
    var list: List[Int] = List.fill(nlst)(0)
    for (i <- 0 to nlst - 1) {
      var fl2 = true
      while (fl2) {
        print(s"${i + 1}: ")
        var str = ""
        var num = 0
        str = scala.io.StdIn.readLine()
        try {
          fl2 = false
          num = str.toInt
        } catch {
          case ex: NumberFormatException => {
            println("Incorrect input");
            fl2 = true
          }
        }
        list = list.updated(i, num)
      }
    }
    list
  }

  def main(args: Array[String]): Unit = {

    var list = entList()

    var flagloop = true
    var tasknum = 0

    while (flagloop) {
      var flag = true
      while (flag) {
        print("Enter the task number (0 - 6): ")
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
          if (tasknum < 0 || tasknum > 6) {
            println("Number should be from 0 to 6");
            flag = true
          }
        }
      }

      if (tasknum == 0) {
        flagloop = false
      }

      if (!list.isEmpty) {

        if (tasknum == 1) {
          // var result1 = sumList(list)
          var result1 = list.filter(_ <= 10).foldLeft(0)(_ + _)
          println("Sum = " + result1)
        }

        if (tasknum == 2) {
          println("Sum of 3 = " + sumListLim(list, 3))
        }

        if (tasknum == 3) {
          println(
            "Max element at index: " + findIndex(
              list,
              maxList(list.tail, list.head),
              1
            )
          )
        }

        if (tasknum == 4) {
          if (checkSort(list.tail, list.head)) println("List is sorted!")
          else println("List is not sorted!")
        }

        if (tasknum == 5) {
          if (checkSame(list)) println("List contains equal elements!")
          else println("List does not contain equal elements!")
        }

      } else if (tasknum != 0 && tasknum != 6) println("List is empty!")

      if (tasknum == 6) {
        var l1 = entList()
        var l2 = entList()
        var l3 = mergeLists(l1, l2)
        if (l3.isEmpty) println("List is empty!")
        else println("res = " + l3)
        println("resUnevenPos = " + countUnevenPos(l3))
      }

    }
  }
}
