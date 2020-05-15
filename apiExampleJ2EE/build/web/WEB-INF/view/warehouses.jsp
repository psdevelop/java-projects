<%--
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html

 * author: tgiunipero
--%>


<%@page import="java.io.PrintWriter"%>
<%@page import="javax.json.JsonObject"%>
<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Czech. --%>
<c:set var='view' value='/warehouses' scope='session' />
<% 
    PrintWriter outp = response.getWriter();
    // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
    //out.print(jsonObject);
    //outp.print("{aa:\"eee\"}");
    //JsonObject obj = new JsonObject();
    //    obj.put("message", "hello from server");
    //outp.flush(); %>
{status:"ok",data:[<c:forEach var="warehouse" items="${warehouses}">{id:"${warehouse.id}",name:"${warehouse.name}",location:"${warehouse.location}",short_name:"${warehouse.shortName}",ims2_name:"${warehouse.ims2Name}",ims2_active:"${warehouse.ims2Active}"},</c:forEach>]}
