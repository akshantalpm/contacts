name := "contacts"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies += "org.specs2" %% "specs2-core" % "2.4.17" % "test"
libraryDependencies += "org.specs2" %% "specs2" % "2.3.12" % "test"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-encoding", "UTF-8"       // yes, this is 2 args
)
