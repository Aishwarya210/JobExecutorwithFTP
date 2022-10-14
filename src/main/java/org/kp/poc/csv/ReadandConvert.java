package org.kp.poc.csv;

import java.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;
import org.apache.commons.net.ftp.FTPClient;

@Component
@RequestMapping("/jobexecutor")
public class ReadandConvert {
	
	
		
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
		 	

		/**
	     * @param host     remote host
	     * @param port     remote port
	     * @param username remote username
	     */
			public ChannelSftp setupJsch() throws JSchException {
				
			    String SFTPUSER = "";
			    String SFTPPASS = "";
			    String SFTPHOST = "";
			    int SFTPPORT = 0 ;
			    Session jschSession = null;
			   
			
			    
				try {
					
					JSch jsch = new JSch();
					jschSession = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
					
					java.util.Properties config = new java.util.Properties();
				    config.put("StrictHostKeyChecking", "no");
				    jschSession.setConfig(config);
				    
				    
				    jschSession.setPassword(SFTPPASS);
				    jschSession.connect();
				    
					
					} catch (JSchException e) {
					e.printStackTrace();
				}
				
				return (ChannelSftp) jschSession.openChannel("sftp");
			    
			  }
		

			public boolean downloadSftp( String localFilePath, String remoteFile) {
				
			    ChannelSftp channelSftp = null;
			    String SFTPWORKINGDIR = "/FGV"; //file directory path
				
			   
			    try {
			    	
				      channelSftp = setupJsch();
				      channelSftp.connect();
				      channelSftp.cd(SFTPWORKINGDIR);
				      
				      ArrayList<String> fileList = new ArrayList<String>();
				      Vector<ChannelSftp.LsEntry> lsfiles = channelSftp.ls("*.cvs");//getting all the CSV files from dir
				      
				      if (lsfiles != null && !lsfiles.isEmpty()){
				      
				    	  for(ChannelSftp.LsEntry file : lsfiles) {
				    		  if(file != null){
				    			  
				    			  fileList.add(file.getFilename());//getting the list of all files
				    			  System.out.println(fileList);
				    			  
				    			  String fileNameToget = file.getFilename();
				    			  String filePath = SFTPWORKINGDIR + "/" + file.getFilename ();
				    			  
				    			  channelSftp.get("/" + filePath, "/src/main/resources/" + fileNameToget );
				    		  }
				    		    
				    		}
				      
				      }
				      
				      System.out.println("File downloaded successfully ");
				      
				      
			    	}
			    
			    catch (JSchException e) {
			    	e.printStackTrace();
			    }
			    
			    catch (SftpException e) {
			      e.printStackTrace();
			    }
			   
			    channelSftp.exit();
			    return true;
			}

			
		public static void getFileFromFtp() {	
			 // Create an instance of FTPClient
	        FTPClient ftp = new FTPClient();
	        try {
	            // Establish a connection with the FTP URL
	            ftp.connect("ftp.test.com");
	            // Enter user details : user name and password
	            boolean isSuccess = ftp.login("user", "password");
	  
	            if (isSuccess) {
	                // Fetch the list of names of the files. In case of no files an
	                // empty array is returned
	                String[] filesFTP = ftp.listNames();
	                int count = 1;
	                // Iterate on the returned list to obtain name of each file
	                for (String file : filesFTP) {
	                    System.out.println("File " + count + " :" + file);
	                    count++;
	                }
	            }
	  
	            ftp.logout();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                ftp.disconnect();
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
			
			
}
		 


	
