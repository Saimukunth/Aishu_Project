package sparkdemo

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object PersonXML {

  def main(args: Array[String]): Unit = {

    import com.databricks.spark.xml._

    // Initiate the spark session

    val spark = SparkSession.
      builder().
      appName("Person XML").
      master("yarn").
      getOrCreate()

    // Reading the file

    val person_df = spark.
      read.
      format("xml").
      option("inferschema", true).
      option("rowTag", "person").
      xml(args(0))

    // To show a inner struct value

    person_df.select(col("addresses.address.street")).show()

    person_df.select(col("salary._VALUE")).show()

  }

}
