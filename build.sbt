sbtPlugin := true

name := "sbt-findbugs"

organization := "uk.co.josephearl"

version := "2.4.4-SNAPSHOT"

libraryDependencies ++= Seq(
  "net.sf.saxon" % "Saxon-HE" % "9.4",
  "org.scalatest" % "scalatest_2.10" % "2.2.1" % "test",
  "junit" % "junit" % "4.11" % "test"
)

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-language:_")

licenses += ("EPL-1.0", url("https://www.eclipse.org/legal/epl-v10.html"))

publishMavenStyle := false

publishArtifact in Test := false
lazy val root = (project in file(".")).enablePlugins(BuildInfoPlugin).settings(

  name := "jacoco4sbt",
  organization := "de.johoop",
  version := "2.4.0-SNAPSHOT",
  scalaVersion := "2.10.6",

  sbtPlugin := true,

  libraryDependencies ++= Seq(
    "org.jacoco"  %  "org.jacoco.core"      % jacocoVersion artifacts jacocoCore,
    "org.jacoco"  %  "org.jacoco.report"    % jacocoVersion artifacts jacocoReport,
    "org.specs2"  %% "specs2-core"          % "3.8.4" % Test,
    "org.specs2"  %% "specs2-matcher-extra" % "3.8.4" % Test,

    "org.mockito" %  "mockito-all"          % "1.10.19"  % Test,
    "org.pegdown" %  "pegdown"              % "1.6.0"  % Test
  ),

  scalacOptions ++= Seq("-unchecked", "-deprecation", "-language:_"),

  buildInfoKeys := Seq[BuildInfoKey](resourceDirectory in Test, version, "jacocoVersion" -> jacocoVersion),
  buildInfoPackage := "de.johoop.jacoco4sbt.build",

  test in Test <<= test in Test dependsOn publishLocal,
  parallelExecution in Test := false,
  scalacOptions in Test ++= Seq("-Yrangepos")
)

lazy val jacocoCore    = Artifact("org.jacoco.core", "jar", "jar")
lazy val jacocoReport  = Artifact("org.jacoco.report", "jar", "jar")
lazy val jacocoVersion = "0.7.9"
