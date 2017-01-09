name := """play-2.5-with-docker"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, JavaAppPackaging, DockerPlugin, JavaAgent)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "io.kamon" %% "kamon-play-25" % "0.6.3",
  "io.kamon" %% "kamon-log-reporter" % "0.6.3",
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

dockerExposedPorts in Docker := Seq(9000, 9443)

javaAgents += "org.aspectj" % "aspectjweaver" % "1.8.10"

javaOptions in Universal += "-Dorg.aspectj.tracing.factory=default"