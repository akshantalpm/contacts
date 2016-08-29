package contacts.io

import scala.util.Try

class InputReader {

  def readInt(prompt: String): Int = {
    println(prompt)

    Try(scala.io.StdIn.readInt()).getOrElse({
      println("Not a number. Please Enter a valid number")
      readInt(prompt)
    })
  }

  def readLine(prompt: String): String = {
    scala.io.StdIn.readLine(prompt)
  }
}
