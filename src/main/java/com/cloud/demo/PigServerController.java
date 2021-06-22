package com.cloud.demo;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.pig.ExecType;
import org.apache.pig.PigException;
import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.PigContext;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.impl.logicalLayer.schema.Schema.FieldSchema;


public class PigServerController {
    PigServer pigServer;
    private PigContext ctx;
    Configuration  conf ;
    public PigServerController() throws PigException {
    	String HDFS_ROOT_URL="hdfs://localhost:9000";
   	 //Properties props = new Properties();
        //props.setProperty("fs.default.name", "hdfs://localhost:9000");
    	//ctx = new PigContext(ExecType.LOCAL, props);
        //ctx.connect();
        //ctx.getProperties().setProperty(PigConfiguration.PIG_BLACKLIST, "set");
        //PigServer pigServer = new PigServer(ctx);
    	//String HDFS_ROOT_URL="hdfs://localhost:9000";
    	 Properties props = new Properties();
         props.setProperty("fs.default.name", "hdfs://localhost:9000");
         props.setProperty("mapred.job.tracker", "localhost:54311");
         props.setProperty("output.compression.enabled", "true");
         props.setProperty("output.compression.codec", "org.apache.hadoop.io.compress.GzipCodec");
    	 //conf = new Configuration();
    	 //conf.set("fs.default.name", HDFS_ROOT_URL);
    	pigServer = new PigServer(ExecType.MAPREDUCE,props);
    	
    }
    public String exec(String str) throws IOException {
    	
        StringBuffer query = new StringBuffer();
        query.append("A = LOAD '"+str+"' AS fil;\n");
        
        
    	InputStream is = new ByteArrayInputStream(query.toString().getBytes());
        pigServer.registerScript(is);
        Iterator<Tuple> iterator = pigServer.openIterator("A");
        if(iterator.hasNext())
        	return iterator.next().toDelimitedString(null);
        else
        	return "";
        
    }
    
}
