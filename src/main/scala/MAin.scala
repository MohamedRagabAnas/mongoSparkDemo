  import org.apache.avro.generic.GenericData.StringType
  import org.apache.spark.sql.types._
  import org.apache.spark.sql.{Row, SparkSession}

  object MAin {

    def main(args: Array[String]): Unit = {


      val  spark = SparkSession
        .builder
        .appName("mongoConnection")
        .master("local[*]")
        .getOrCreate()

      spark.sparkContext.setLogLevel("ERROR")



      import scala.io.Source
      val stream = getClass.getResourceAsStream("/u.user")
      val lines: Iterator[String] = scala.io.Source.fromInputStream( stream ).getLines

      // The schema is encoded in a string

      val schema  =new StructType()
        .add("user_id",DataTypes.IntegerType)
        .add("age",DataTypes.IntegerType)
        .add("gender",DataTypes.StringType)
        .add("occupation",DataTypes.StringType)
        .add("zip",DataTypes.StringType)



      //Read the Users File as a DataFrame in Spark
      val customers= spark.read
        .option("delimiter", "|")
        .schema(schema)
        .csv("C:\\Users\\Mohamed Ragab\\Desktop\\FunctionalProgramingTrainingScala\\Week2\\funsets\\SparkMongoDemo\\src\\main\\resources\\u.user")



      /*
      // Write the users DataFRame to MongoDB
      customers.write
        .format("com.mongodb.spark.sql.DefaultSource")
        .option("uri","mongodb://127.0.0.1/movielens.users")
        .save()
      */


      //Reading from MongoDB
      val movielensUsers= spark.read
        .format("com.mongodb.spark.sql.DefaultSource")
        .option("uri","mongodb://127.0.0.1/movielens.users")
        .load()

      movielensUsers.createOrReplaceTempView("users")

      spark.sql("SELECT * FROM users where age <20").show()


    }
}
