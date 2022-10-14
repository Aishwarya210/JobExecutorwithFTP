package org.kp.salesconnect.biz.metadataloader.scheduler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author A670691
 *
 */
@Component
@EnableScheduling
public class MetadataLoaderScheduler {
	
	private static Logger log = LoggerFactory.getLogger(MetadataLoaderScheduler.class);
	
	
	/**
	 * method execute after every configured fixedDelay/fixedrate
	 * to run the scheduler. 
	 */
	
	@Scheduled(fixedRate = 10000)
	public void test() {
		
	
		System.out.println("Testing Executor");
		
	}

	@Scheduled(cron="0 40 03 * * *")
	//@Scheduled(cron="${app.salesconnect.oncall.scheduler.cron}")
	 public static void csvTojsonConverter() {
		 
		 
		 CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper csvMapper = new CsvMapper();
	       
	      
	        try {
	            List<Map<?, ?>> list;
	            File input = new ClassPathResource("input.csv").getFile();
	            
	            try (MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader()
	                    .forType(Map.class)
	                    .with(csvSchema)
	                    .readValues(input)) {
	                list = mappingIterator.readAll();
	            }
		 
	        System.out.println("List Is::");
	        System.out.println(list);
	        
	        ObjectMapper objectMapper = new ObjectMapper();
            String jsonPretty = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(list);
            
            System.out.println("Json Object Is::");
            System.out.println(jsonPretty);
	        
	        
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		 
		 
}
	 	
public static void readFile() {
		
		String SFTPHOST = "server-name";
	    int SFTPPORT = 22;
	    String SFTPUSER = "username";
	    String SFTPPASS = "password";
	    String SFTPWORKINGDIR = "/FGV";
	
	    Session session = null;
	    Channel channel = null;
	    ChannelSftp channelSftp = null;
	
	    try {
	        JSch jsch = new JSch();
	        session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
	        
	       Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	        
	        session.setPassword(SFTPPASS);
	        session.connect();
	
	        channel = session.openChannel("sftp");
	        channel.connect();
	
	        channelSftp = (ChannelSftp) channel;
	        channelSftp.cd(SFTPWORKINGDIR);
	        
	        byte[] buffer = new byte[1024];
	        BufferedInputStream bis = new BufferedInputStream(
	            channelSftp.get("/FGV/US/BS/input.csv"));
	        
	        File newFile = new File(
	            "C:\\workspace\\src\\CSV_FROM_SERVER.csv");
	        OutputStream os = new FileOutputStream(newFile);
	        
	        BufferedOutputStream bos = new BufferedOutputStream(os);
	        int readCount = 0;
	        
	        //System.out.println("Getting: " + theLine);while ((readCount = bis.read(buffer)) > 0) {
	            //System.out.println("Writing: ");
	            bos.write(buffer, 0, readCount);
	        
	        
	        while(session != null){
	            System.out.println("Killing the session");
	            session.disconnect();
	            bis.close();
	            bos.close();
	            System.exit(0);
	        }
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    
	
}
	
}