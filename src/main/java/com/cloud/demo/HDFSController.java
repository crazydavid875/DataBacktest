package com.cloud.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
 
// Sample Java program to read files from hadoop hdfs filesystem
public class HDFSController {
 
  // This is copied from the entry in core-site.xml for the property fs.defaultFS. 
  // Replace with your Hadoop deployment details.
  
  private Configuration conf;
 
  
    //HDFSDemo demo = new HDFSDemo();
     
    // Reads a file from the user's home directory.
    // Replace jj with the name of your folder
    // Assumes that input.txt is already in HDFS folder
   // String uri = HDFS_ROOT_URL+"/user/jj/input.txt";
    //demo.printHDFSFileContents(uri);
  
   
    public HDFSController() {
    	String HDFS_ROOT_URL="hdfs://localhost:9000";
    	conf = new Configuration();
        conf.set("fs.default.name", HDFS_ROOT_URL);
    
    }
   
  // Example - Print hdfs file contents to console using Java
  public String readHDFSFileContents(String uri) throws Exception {
    
    try {
      FileSystem fs = FileSystem.get(conf);
      // Hadoop DFS Path - Input file
      Path inFile = new Path(uri);
        
      // Check if input is valid
      if (!fs.exists(inFile)) {
        System.out.println("Input file not found");
        throw new IOException("Input file not found");
      }
			
      // open and read from file
      FSDataInputStream in = fs.open(inFile);
      // system.out as output stream to display 
      //file content on terminal 
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line = "";
      StringBuffer stringBuffer = new StringBuffer();
      try {
        while ((line = br.readLine()) != null) {
            stringBuffer.append(line);
        }
        
      } catch (IOException e) {
        System.out.println("Error while copying file");
      } finally {
         // Closing streams
        in.close();
        br.close();
        return stringBuffer.toString();
      }      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "";
  }
  
}


