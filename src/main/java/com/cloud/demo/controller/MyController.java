package com.cloud.demo.controller;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cloud.demo.HDFSController;
import com.cloud.demo.PigServerController;
import com.cloud.demo.SparkController;

import org.apache.hadoop.conf.Configuration;
import org.apache.pig.PigException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(value = "http://204adc7ceaaa.ngrok.io")
@Controller
public class MyController {
    
    @PostMapping("/rule")
    public String hello(@RequestParam Map<String,String> allRequestParams, Model model) {
        
        List<String> rules = new LinkedList<>(); 
        allRequestParams.forEach((k,v)->{
            if(k.matches("rule(.*)")){
                rules.add(v);
            }
        });
        model.addAttribute("a",rules.get(0).toString());
        
        return "pagelook" ;
    }
    @GetMapping("/readHDFStest/{fname}")
    public String getResult(@PathVariable(required = true) String fname,Model model){
    	
    	HDFSController hdfsController = new HDFSController();
        
        String uri = "/hadoop1/"+fname;
        try{
            model.addAttribute("a",hdfsController.readHDFSFileContents(uri));
        }
        catch(Exception e){
        }
        
        
        return "pagelook" ;
    }
    /*
    @GetMapping("/runPigetest/{fname}")
    public String getrunpig(@PathVariable(required = true) String fname,Model model){
    	
        PigServerController pig;
		try {
			pig = new PigServerController();
			model.addAttribute("a", pig.exec("/hadoop1/"+fname));	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "pagelook" ;
    }
    */
    @GetMapping("/runSparktest/{fname}")
    public String getrunSpark(@PathVariable(required = true) String fname,Model model){
    	SparkController sparkController = new SparkController();
        model.addAttribute("a", sparkController.run("/hadoop1/"+fname));	
                
        return "pagelook" ;
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
