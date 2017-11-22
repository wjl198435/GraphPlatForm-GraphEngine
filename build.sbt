name := "sparkMiniSample"
organization := "myOrg"

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation",
  "-Xfuture",
  "-Xlint:missing-interpolator",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-dead-code",
  "-Ywarn-unused"
)

//The default SBT testing java options are too small to support running many of the tests
// due to the need to launch Spark in local mode.
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
parallelExecution in Test := false

lazy val spark = "2.1.0"

resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"

resolvers += "Hbase Packages Repo" at "https://mvnrepository.com/artifact/org.apache.hbase/hbase-shaded-client"



libraryDependencies ++= Seq(
//  "org.apache.spark" %% "spark-core" % spark % "provided",
//  "org.apache.spark" %% "spark-sql" % spark % "provided",
//  "org.apache.spark" %% "spark-hive" % spark % "provided",
//  "graphframes" % "graphframes" % "0.3.0-spark2.0-s_2.11",
//  "org.apache.spark" %% "spark-graphx" % spark % "provided"

  "org.apache.spark" %% "spark-core" % spark,
  "org.apache.spark" %% "spark-sql" % spark,
  "org.apache.spark" %% "spark-hive" % spark,
  "graphframes" % "graphframes" % "0.3.0-spark2.0-s_2.11",
  "org.janusgraph" % "janusgraph-hbase" % "0.2.0" ,
  "org.apache.hbase" % "hbase-shaded-client" % "1.2.6",
  "org.janusgraph" % "janusgraph-core" % "0.2.0",
  "org.apache.spark" %% "spark-graphx" % spark
)

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))

assemblyMergeStrategy in assembly := {
  case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.last
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

test in assembly := {}

initialCommands in console :=
  """
    |import java.io.File
    |
    |import org.apache.spark.SparkConf
    |import org.apache.spark.sql.{DataFrame, SparkSession}
    |
    |val conf: SparkConf = new SparkConf()
    |    .setAppName("exampleSQL")
    |    .setMaster("local[*]")
    |    .set("spark.executor.extraJavaOptions", "-XX:+UseG1GC")
    |    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    |    .set("spark.kryoserializer.buffer.max", "1G")
    |    .set("spark.kryoserializer.buffer", "100m")
    |
    |  val spark: SparkSession = SparkSession
    |    .builder()
    |    .config(conf)
    |    .enableHiveSupport()
    |    .getOrCreate()
    |
    |  import spark.implicits._
  """.stripMargin

mainClass := Some("myOrg.ExampleSQL")
