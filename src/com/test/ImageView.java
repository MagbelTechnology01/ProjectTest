package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ImageView {

	public String fetchImage(String id) {
		String encodstring = null;
		try {
			//System.out.println("The file name is : " + barCode);
	        Properties prop = new Properties();
	        File file = new File("C:\\Property\\legendPlus.properties");
	        FileInputStream input = new FileInputStream(file);
	        prop.load(input);

	        String UPLOAD_FOLDER = prop.getProperty("imagesUrl");
	        
	       // System.out.println("The url is : " + UPLOAD_FOLDER);

	        String fullPath =  UPLOAD_FOLDER + id + ".jpg";
	        
	        System.out.println("Full Path is : " + fullPath);
	        
	        File f =  new File(fullPath);
	        
	        if(!f.exists()) {
	        	System.out.println("Image does not exist!!!"); 
	       
	        }else {
	        	encodstring = encodeFileToBase64Binary(f);
//	        	 System.out.println("The encoded image value is : " + encodstring);  
	            System.out.println("Successful!!!");  
	            
	          
	           
	        } 
	        
		}catch(Exception e) {
			e.getMessage();
		}
		return encodstring;
		
		
	}
	
	private static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        FileInputStream fileInputStreamReader = null;
        try {
             fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(org.apache.commons.codec.binary.Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}
