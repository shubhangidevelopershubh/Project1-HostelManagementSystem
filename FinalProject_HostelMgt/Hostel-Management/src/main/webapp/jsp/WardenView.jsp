<%@page import="com.hostel.mgt.controller.WardenCtl"%>
<%@page import="com.hostel.mgt.bean.WardenBean"%>
<%@page import="com.hostel.mgt.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="com.hostel.mgt.util.ServletUtility"%>
<%@page import="com.hostel.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Warden</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Add Warden</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=HMSView.WARDEN_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="com.hostel.mgt.bean.WardenBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Add Room</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                			<%
						
							List list=(List)request.getAttribute("hostelList");
                			List list1=(List)request.getAttribute("userList");
							%>
							
							<div class="form-group">
								<%=HTMLUtility.getList("userId",String.valueOf(bean.getUserId()), list1)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("userId", request)%></font>
							</div>
							
							<div class="form-group">
								<%=HTMLUtility.getList("hostelId",String.valueOf(bean.getHostelId()), list)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("hostelId", request)%></font>
							</div>

							
        					<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=WardenCtl.OP_SAVE%>">&nbsp;or&nbsp;
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=WardenCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>
		<div style="margin-top: 205px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>