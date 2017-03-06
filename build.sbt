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

// start plugin: sbt-dependency-graph
lazy val `dependency-graph` = (project in file("dependency-graph"))
  .settings(commonSettings)
  .settings(
    name := "dependency-graph",
    description := "Everything you wanted to know about your project's dependencies.",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % "2.6.0-M1"
    ),
    openDepGraphHtml := {
      val dir = (dependencyBrowseGraphTarget in Compile).value
      s"xdg-open file://$dir/graph.html" !
    }
  )

lazy val `dependency-graph-small` = (project in file("dependency-graph/small"))
  .settings(commonSettings)
  .settings(
    name := "dependency-graph-small",
    description := "Everything you wanted to know about your project's dependencies. Light",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.2.0"
    )
  )

// workaround for error: UnsupportedOperationException: The BROWSE action is not supported on the current platform!
lazy val openDepGraphHtml = taskKey[Unit]("Linux hack to open dependency graph HTML in browser.")

addCommandAlias("dependency-graph_01_dependencyGraph", "dependency-graph-small/dependencyGraph")
addCommandAlias("dependency-graph_02_dependencyTree", "dependency-graph/dependencyTree")
addCommandAlias("dependency-graph_03_dependencyList", "dependency-graph/dependencyList")
addCommandAlias("dependency-graph_04_dependencyStats", "dependency-graph/dependencyStats")
addCommandAlias("dependency-graph_05_dependencyLicenseInfo", "dependency-graph/dependencyLicenseInfo")
addCommandAlias("dependency-graph_06_dependencyBrowseGraph", "dependency-graph/dependencyBrowseGraph")
addCommandAlias("dependency-graph_06_dependencyBrowseGraph_open", "dependency-graph/openDepGraphHtml")
// end plugin: sbt-dependency-graph

// start plugin: sbt-coursier
lazy val coursier = (project in file("coursier"))
  .settings(commonSettings)
  .settings(
    name := "coursier",
    description := "Fetch your dependencies faster...and more.",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % "2.6.0-M1"
    )
  )

addCommandAlias("coursier_01_dependencyTree", "coursier/coursierDependencyTree")
// end plugin: sbt-coursier

// start plugin: sbt-updates
lazy val updates = (project in file("updates"))
  .settings(commonSettings)
  .settings(
    name := "updates",
    description := "How out-of-date are my dependencies?",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.0.1",
      "org.scalaz" %% "scalaz-core" % "7.2.7"
    )
  )

addCommandAlias("updates_01_dependencyUpdates", "updates/dependencyUpdates")
addCommandAlias("updates_02_dependencyUpdatesReport", "updates/dependencyUpdatesReport")
// end plugin: sbt-updates

// start plugin: sbt-site, sbt-ghpages
lazy val site = (project in file("site"))
  .enablePlugins(GhpagesPlugin, HugoPlugin)
  .settings(commonSettings)
  .settings(
    name := "site",
    description := "Assemble a snazzy website for your project.",
    baseURL in Hugo := new URI("http://indyscala.org/sbt-plugin-tour"),
    git.remoteRepo := "git@github.com:indyscala/sbt-plugin-tour.git",
    ghpagesBranch := "gh-pages", // default, but be explicit
    ghpagesNoJekyll := true      // we're using hugo
  )

addCommandAlias("site_01_makeSite", "site/makeSite")
addCommandAlias("site_02_previewSite", "site/previewSite")

addCommandAlias("ghpages_01_ghpagesPushSite", "site/ghpagesPushSite")
addCommandAlias("ghpages_02_pushSite", ";site/clean; site/makeSite ;site/ghpagesPushSite")
// end plugin: sbt-site, sbt-ghpages

// start plugin: sbt-scoverage
lazy val scoverage = (project in file("scoverage"))
  .settings(commonSettings)
  .settings(
    name := "scoverage",
    description := "How well is my code tested?"
  )

addCommandAlias("scoverage_01_measure", ";scoverage/clean ;coverage ;scoverage/test ;coverageOff")
addCommandAlias("scoverage_02_report", ";scoverage/coverageReport")
// end plugin: sbt-scoverage

// start plugin: sbt-revolver
lazy val revolver = (project in file("revolver"))
  .settings(commonSettings)
  .settings(
    name := "revolver",
    description := "Super-fast development turnaround for your Scala applications.",
    libraryDependencies ++= Seq(
      "com.jsuereth" %% "scala-arm" % "2.0"
    ),
    mainClass in reStart := Some("org.indyscala.sbt_plugin_tour.ConsoleSpammer")
  )

addCommandAlias("revolver_01_start", ";project revolver ;~reStart")
addCommandAlias("revolver_02_status", "reStatus")
addCommandAlias("revolver_03_stop", "reStop")
addCommandAlias("revolver_04_back_to_root", "project root")
// end plugin: sbt-revolver

// start plugin: sbt-mima-plugin
lazy val mima = (project in file("mima"))
  .settings(commonSettings)
  .settings(
    name := "mima",
    description := "Prevent unintended binary incompatibilities between releases.",
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.21"
    ),
    mimaPreviousArtifacts := Set("org.log4s" %% "log4s" % "1.3.4")
  )

addCommandAlias("mima_01_mimaReportBinaryIssues", "mima/mimaReportBinaryIssues")
// end plugin: sbt-mima-plugin

// start plugin: sbt-unidoc
lazy val unidoc = (project in file("unidoc"))
  .enablePlugins(ScalaUnidocPlugin)
  .settings(commonSettings)
  .settings(
    name := "unidoc",
    description := "Generate combined scaladoc and javadoc HTML."
  )

addCommandAlias("unidoc_01_unidoc", "unidoc/unidoc")
// end plugin: sbt-unidoc
