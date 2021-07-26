package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * Servlet implementation class ImageUploadServlet
 */
@WebServlet("/ImageUploadServlet")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentId = request.getParameter("studentId");
		Part filePart = request.getPart("file");
 		 
 		Properties prop = new Properties();
        File file = new File("C:\\Property\\legendPlus.properties");
        FileInputStream input = new FileInputStream(file);
        prop.load(input);
   	
        String UPLOAD_FOLDER = prop.getProperty("imagesUrl");
 		 
       
 		 InputStream uploadedInputStream = null;
 		 
 		 
 		
 		try {
			//uploadedInputStream = new FileInputStream(inFile);
 			uploadedInputStream=filePart.getInputStream();
			  System.out.println("The uploaded input stream is : " + uploadedInputStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
 		 
 		
         if (uploadedInputStream == null) {
        System.out.println("Invalid form data...");
         }
         
         // create our destination folder, if it not exists
         try {
             createFolderIfNotExists(UPLOAD_FOLDER);
         } catch (SecurityException se) {
        	 System.out.println("Can not create destination folder on server...");
         }
        // String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();  
         String uploadedFileLocation = UPLOAD_FOLDER + studentId + ".jpg";
         System.out.println("The Uploaded Location is : " + uploadedFileLocation);
//         try {
        	 if(uploadedFileLocation.endsWith(".jpg")) {
        		 saveToFile(uploadedInputStream, uploadedFileLocation);
        		 System.out.println("File saved to " +uploadedFileLocation + " Successfully..");
        		 response.sendRedirect("FileUpload.jsp");
        	 }
//        	 else {
//        		 System.out.println("Can not save non jpg file...");
//	        	 data.put("status",503);
//		         data.put("message","Can not save non jpg file"); 
//        	 }
	}

	
	 private void saveToFile(InputStream inStream, String target)
	            throws IOException {
	        OutputStream out = null;
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        out = new FileOutputStream(new File(target));
	        while ((read = inStream.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    }
	    
	    
	    private void createFolderIfNotExists(String dirName)
	            throws SecurityException {
	        File theDir = new File(dirName);
	        if (!theDir.exists()) {
	            theDir.mkdir();
	        }
	    }
}
