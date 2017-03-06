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
