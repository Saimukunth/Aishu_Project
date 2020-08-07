package sparkdemo

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object MinimumTemperature {

  def main(args: Array[String]): Unit = {

    // Initiate the spark session using spark 2.3.0

    val spark = SparkSession.
      builder().
      appName("Minimum Temperature").
      master("yarn").
      getOrCreate()

    // Reading the file from hdfs

    val temperatureRDD = spark.
      sparkContext.
      textFile(args(0))

    val tempMap: RDD[(String, String, Float)] = temperatureRDD.
      map(m => {
      val temp = m.split(",")
      (temp(0), temp(2), temp(3).toFloat)
    })

    // Filter only the min temperatures

    val min_records = tempMap.
      filter(f => (f._2 == "TMIN")).
      map(m => (m._1, m._3))

    // Find the min temperature

    val minTempsByStation = min_records.
      reduceByKey((x, y) => {
        if (x < y) x else y
      })

    // Collect, format, and print the results

    val results = minTempsByStation.
      collect()

    for (result <- results.sorted) {
      val station = result._1
      val temp = result._2
      val formattedTemp = f"$temp%.2f F"
      println(s"$station minimum temperature: $formattedTemp")
    }

  }

}
