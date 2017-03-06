package org.indyscala
package sbt_plugin_tour

object ConsoleSpammer {
  val Spam = "Can you hear me now?"

  def main(args: Array[String]): Unit = {
    while (true) {
      println(Spam)
      Thread.sleep(2000)
    }
  }
}
