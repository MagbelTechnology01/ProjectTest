<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body>

<form action="UploadServlet" method="Post" enctype="multipart/form-data">
<label for="studentId">Student Id</label>
  <input type="text" name="studentId" value="${id}">
  <br/>
  <label for="file">Image</label>
  <input type="file" id="file" name="file">
  <br/>
   <input type="submit" />
</form>
</body>
</html>