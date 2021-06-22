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
    	spark = SparkSession
                 .builder()
                 .appName(appName)
                 .master(master)
                 .getOrCreate();
    }
    public String run(String path){
       
                
        String str = "";
        String loc = "hdfs://localhost:9001/"+path;
        //读取元数据文件
        //Dataset<Row> df = spark.read().csv("hdfs://localhost:9000/"+path);
        Dataset<Row> csv = spark.read().format("csv").option("header","true")
        .load(loc);
        //生成rdd
        List<Row> line = csv.javaRDD().collect();
        for(Row val:line)
        {
            for(int i=0;i<val.size();i++){
                str+=val.get(i)+",";
            }
            str+="\n";
        }
        //str = df.toString();
        
        return str;
    }
}
