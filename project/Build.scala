import sbt._
import Keys._

object DevNulltterBuild extends Build {
   lazy val root = Project(id = "devnulltter",
                           base = file(".")).aggregate(common, daemon, authorizer)

   lazy val common = Project(id = "common",
                             base = file("common"))

   lazy val daemon = Project(id = "daemon",
                             base = file("daemon")).dependsOn(common)

   lazy val authorizer = Project(id = "authorizer",
                                 base = file("authorizer")).dependsOn(common)

}
