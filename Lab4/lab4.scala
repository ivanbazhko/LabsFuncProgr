val lines = spark.read.textFile("mytext.txt")
val words = lines.flatMap(line => line.split("\\W+"))

val stopWords = List("the", "and", "or", "is", "for", "a", "of")
val words1 = words.filter(word => !stopWords.contains(word))
words1.collect().foreach(println)

val regex2 = ".*t.*"
val words2 = words.filter(word => word.matches(regex2))
words2.collect().foreach(println)

val regex3 = ".*ing$"
val words3 = words.filter(word => word.matches(regex3))
words3.collect().foreach(println)

val regex4 = "^.a.*$"
val words4 = words.filter(word => word.matches(regex4))
words4.collect().foreach(println)

val regex5 = ".*s$"
val words5 = words.filter(word => word.matches(regex5))
words5.collect().foreach(println)

val words6 = lines.flatMap(line => line.split("\\W+").zipWithIndex.filter(_._2 % 2 == 1).map(_._1))
words6.collect().foreach(println)

val doplines = spark.read.textFile("twosentences.txt")

val pattern1 = """[aeiouyAEIOUY]"""
val filtered1 = doplines.map((word: String) => word.replaceAll(pattern1, ""))
filtered1.collect().foreach(print)

val pattern2 = """(\w{3}\b|\b\w{2}\b|\b\w\b)"""
val filtered2 = doplines.map((word: String) => word.replaceAll(pattern2, "ing"))
filtered2.collect().foreach(print)

val pattern3 = """[ ]"""
val filtered3 = doplines.map((word: String) => word.replaceAll(pattern3, ""))
filtered3.collect().foreach(print)

val pattern4 = """\s"""
val filtered4 = doplines.map((word: String) => word.replaceAll(pattern4, ""))
filtered4.collect().foreach(print)
