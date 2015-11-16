import sbt._
import Keys._

object SparkKafkaWriter extends Build {

  lazy val dependencies = {
    val sparkVersion = "1.5.2"
    Seq(
      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-streaming" % sparkVersion,
      "org.apache.kafka" %% "kafka" % "0.8.2.2" exclude("com.sun.jmx", "jmxri") exclude("com.sun.jdmk", "jmxtools") exclude("javax.jms", "jms"),
      "junit" % "junit" % "4.11" % "test",
      "org.scalacheck" %% "scalacheck" % "1.11.3" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )
  }

  lazy val root = Project("spark-kafka-writer", file("."))
    .settings(
      organization := "org.cloudera.spark.streaming.kafka",
      version := "0.1.1-SNAPSHOT",
      description := "API to write from Spark and Spark Streaming to Kafka",
      scalaVersion := "2.11.7",
      libraryDependencies := dependencies,
      crossScalaVersions := Seq("2.10.6", "2.11.7"),
      publishMavenStyle := true,
      publishTo := {
        val url = "https://repository.cloudera.com/"
        if(isSnapshot.value) {
          Some("snapshots" at url + "cloudera/libs-release-local")
        } else {
          Some("releases" at url + "artifactory/libs-snapshot-local")
        }
      }
    )
}