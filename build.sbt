import sbtassembly.MergeStrategy
name := "Aishu_Project"

version := "0.1"

scalaVersion := "2.11.12"

assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "hadoop", "yarn", "factories", "package-info.class")         => MergeStrategy.discard
  case PathList("org", "apache", "hadoop", "yarn", "provider", "package-info.class")         => MergeStrategy.discard
  case PathList("org", "apache", "hadoop", "util", "provider", "package-info.class")         => MergeStrategy.discard
  case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class")         => MergeStrategy.first
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"
resolvers += "Cascading repo" at "http://conjars.org/repo"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.3.0"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.3.0"
libraryDependencies += "com.databricks" % "spark-xml" % "0.5.0"

// Hive UDF Function

// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.0"
// https://mvnrepository.com/artifact/org.apache.hive/hive-exec
libraryDependencies += "org.apache.hive" % "hive-exec" % "1.2.1"
libraryDependencies += "org.pentaho" % "pentaho-aggdesigner-algorithm" % "5.1.5-jhyde" % Test
// https://mvnrepository.com/artifact/commons-io/commons-io
libraryDependencies += "commons-io" % "commons-io" % "2.7"
// https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient
libraryDependencies += "commons-httpclient" % "commons-httpclient" % "3.1"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-test
libraryDependencies += "org.apache.hadoop" % "hadoop-test" % "1.2.1" % Test
// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.8.2" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
