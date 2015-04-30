<%@ page import="java.io.*,java.util.*" %>
<html>
<head>
<title>Page Redirection</title>
</head>
<body>
<%
   // New location to be redirected
   String site = new String("search.do");
   response.setStatus(response.SC_MOVED_PERMANENTLY);
   response.setHeader("Location", site); 
%>
</body>
</html>