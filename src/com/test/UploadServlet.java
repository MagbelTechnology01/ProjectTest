package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studentId = (String) session.getAttribute("id");
		
		//String studentId = request.getParameter("studentId");
		String image = request.getParameter("file");
//		Update update = new Update();
//		String username = update.newId();
//		System.out.println("The username id is : " + username);
		//System.out.println("The student username is : " + username);
		Properties prop = new Properties();
		 File file = new File("C:\\Property\\legendPlus.properties");
        FileInputStream input = new FileInputStream(file);
        prop.load(input);
   	
        String UPLOAD_FOLDER = prop.getProperty("imagesUrl"); 
		
		if(ServletFileUpload.isMultipartContent(request)){
	            try {
	                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	                for(FileItem item : multiparts){
	                    if(!item.isFormField()){
	                        String name = new File(item.getName()).getName();
	                        String uploadedFileLocation = UPLOAD_FOLDER + studentId + ".jpg";
	                        if(uploadedFileLocation.endsWith(".jpg")) {
	                        	item.write( new File(uploadedFileLocation));
	                   		 System.out.println("File saved to " +uploadedFileLocation + " Successfully..");
	                   		 response.sendRedirect("FileUpload.jsp");
	                   	 }
	                        
	                    }
	                }
	               //File uploaded successfully
	               //request.setAttribute("gurumessage", "File Uploaded Successfully");
	            } catch (Exception ex) {
	            	 System.out.println("File Upload Failed due to " + ex);
	               //request.setAttribute("gurumessage", "File Upload Failed due to " + ex);
	            }         		
	        }else{
	        	System.out.println("No File found");
}
		 //response.sendRedirect("FileUpload.jsp");
	}

}
