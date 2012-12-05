name := "/dev/nulltter"

version := "0.1"

scalaVersion := "2.9.2"

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
	"org.twitter4j" % "twitter4j-core" % "3.0.1",
    "com.twitter" % "util-eval" % "5.3.0" withSources()
)

resolvers ++= Seq(
	"snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
	"releases"  at "http://oss.sonatype.org/content/repositories/releases",
    "T repo"    at "http://maven.twttr.com/"
)
