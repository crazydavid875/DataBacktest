package com.cloud.demo;

import org.apache.spark.api.java.JavaRDD;

import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkController {
    private static String appName = "spark.sql.demo";
    private static String master = "local[*]";
    //"hdfs://localhost:9000/hadoop1/stock2330.csv"
    public String run(String path){
        SparkSession spark = SparkSession
                .builder()
                .appName(appName)
                .master(master)
                .getOrCreate();
                
        String str = "";
        //读取元数据文件
        Dataset<Row> df = spark.read().csv("hdfs://localhost:9000/hadoop1/"+path);
        //生成rdd
        JavaRDD<Row> rdd = df.toJavaRDD();
        //遍历
        rdd.foreach(new VoidFunction<Row>() {
            public void call(Row row) throws Exception {
                System.out.println(row.toString());
            }
        });
        str = df.toString();
        spark.stop();
        return str;
    }
}
