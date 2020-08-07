name := "SparkMongoDemo"

version := "0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.3.2"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,

  "org.apache.spark" %% "spark-sql" % sparkVersion,

   "org.mongodb.spark" %% "mongo-spark-connector" % "2.3.2"
)