import sbt._, Keys._
import xerial.sbt.Sonatype.autoImport.sonatypeProfileName

object CentralRequirementsPlugin extends AutoPlugin {
  // tells sbt to automatically enable this plugin where ever
  // the sbt-rig plugin is enabled (which should be all sub-modules)
  override def trigger = allRequirements

  override def requires = verizon.build.RigPlugin

  override lazy val projectSettings = Seq(
    // this tells sonatype what profile to use
    // (usually this is what you registered when you signed up
    // for maven central release via their OSS JIRA ticket process)
    sonatypeProfileName := "org.indyscala",
    // inform central who was explicitly involved in developing
    // this project. Note that this is *required* by central.
    developers += Developer("bfritz", "Brad Fritz", "", url("http://github.com/bfritz")),
    // where can users find information about this project?
    homepage := Some(url("https://github.com/indyscala/sbt-plugin-tour")),
    // show users where the source code is located
    scmInfo := Some(ScmInfo(url("https://github.com/indyscala/sbt-plugin-tour"),
                                "git@github.com:indyscala/sbt-plugin-tour.git"))
  )
}
