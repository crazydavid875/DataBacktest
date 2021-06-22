package com.cloud.demo;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;

import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkController {
    private static String appName = "spark.sql.demo";
    private static String master = "local[*]";
    //"hdfs://localhost:9000/hadoop1/stock2330.csv"
    private static SparkSession spark;
    public SparkController() {
    	 SparkSession spark = SparkSession
                 .builder()
                 .appName(appName)
                 .master(master)
                 .getOrCreate();
    }
    public String run(String path){
       
                
        String str = "";
        //读取元数据文件
        //Dataset<Row> df = spark.read().csv("hdfs://localhost:9000/"+path);
        Dataset<Row> csv = spark.read().format("csv").option("header","true")
        .load("hdfs://140.124.184.198:9000/"+path);
        //生成rdd
        List<Row> line = csv.javaRDD().collect();
        for(Row val:line)
        {
            str+=val.get(0)+"\n";
        }
        //str = df.toString();
        
        return str;
    }
}
