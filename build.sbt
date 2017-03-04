lazy val commonSettings = Seq(
  organization := "org.indyscala.sbt_plugin_tour",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.1",

   homepage := Some(url("https://github.com/indyscala/sbt-plugin-tour")),
   licenses := Seq(
     "MIT" -> url("http://opensource.org/licenses/MIT")
   )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "tour",
    description := "A tour of useful sbt plugins."
  )
