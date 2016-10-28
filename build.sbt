
name := "avro-generator"

version := "1.0"

scalaVersion := "2.11.8"


//Dependencies injection
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.0.0" % "provided"
libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.0.0" % "provided"
libraryDependencies += "org.apache.avro" % "avro" % "1.7.7" % "provided"

libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-8_2.11" % "2.0.0"

//Jar name
assemblyJarName in assembly := "avro-generator-1.0.jar"

//Merge policy for files with same name
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _       => MergeStrategy.first
}

//Generates Scala Case Classes compatible with Avro Specific API
sbtavrohugger.SbtAvrohugger.specificAvroSettings

(scalaSource in avroConfig) := new java.io.File("src/main/generated-classes")