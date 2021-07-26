<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript"
    src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<title>Login</title>
</head>
<body>
 <div style="text-align: center">
        <h1>Login</h1>
<form  action="LoginServlet" method="Get">
<label for="username">Username</label>
  <input type="text" id="username" name="username">
  <br/>
  <label for="password">Password</label>
  <input type="text" id="password" name="password">
  <br>${message}
  <br/>
  <input type="submit" value="Submit">
  </form>
  </div>
  
 
</body>
<script type="text/javascript">
 
    $(document).ready(function() {
        $("#loginForm").validate({
            rules: {
                username: {
                    required: true,
                    username: true
                },
         
                password: "required",
            },
             
            messages: {
                username: {
                    required: "Please enter username",
                    username: "Please enter a valid username"
                },
                 
                password: "Please enter password"
            }
        });
 
    });
</script>
</html>