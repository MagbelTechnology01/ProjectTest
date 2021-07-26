package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		try {
			//System.out.println("The file name is : " + barCode);
	        Properties prop = new Properties();
	        File file = new File("C:\\Property\\legendPlus.properties");
	        FileInputStream input = new FileInputStream(file);
	        prop.load(input);

	        String UPLOAD_FOLDER = prop.getProperty("imagesUrl");
	        
	       // System.out.println("The url is : " + UPLOAD_FOLDER);

	        String fullPath =  UPLOAD_FOLDER + id + ".jpg";
	        
	        File f =  new File(fullPath);
	        
	        if(!f.exists()) {
	        	System.out.println("Image does not exist!!!"); 
	       
	        }else {
	        	String encodstring = encodeFileToBase64Binary(f);
	        	
	        	 request.setAttribute("image", encodstring);
	             
	             String page = "/ViewImage.jsp";
	             RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	             requestDispatcher.forward(request, response); 
	            
	           
	        } 
	        
		}catch(Exception e) {
			e.getMessage();
		}
			   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
