<%--
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html

 * author: tgiunipero
--%>


<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Czech. --%>
<c:set var="view" value="/pramounts" scope="session"/>
{[
    <c:forEach var="pramount" items="${pramounts}">
        {id:'${pramount.id}',quantity:${pramount.quantity},prod_id:'${pramount.productId}',whs_id:'${pramount.warehouseId}',},
    </c:forEach>
    ]}