<%@page import="com.hostel.mgt.controller.FeeCtl"%>
<%@page import="com.hostel.mgt.bean.FeeBean"%>
<%@page import="com.hostel.mgt.util.ServletUtility"%>
<%@page import="com.hostel.mgt.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fee</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Fee</li>
	</ol>
	</nav>
	
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="<%=HMSView.FEE_CTL%>" method="post" >
							<br style="clear: both">
		
							<jsp:useBean id="bean" class="com.hostel.mgt.bean.FeeBean"
         					   scope="request"></jsp:useBean>
         			   <% Long aid=(Long)session.getAttribute("aId");%>
		
             			 <input type="hidden" name="aId" value="<%=aid%>">
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
							<h3 style="margin-bottom: 15px; text-align: left: ;">Fee</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="pay"
									placeholder="Pay Amount" value="<%=DataUtility.getStringData(bean.getPay())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("pay", request)%></font>
							</div>
							
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=FeeCtl.OP_SAVE %>">
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=FeeCtl.OP_RESET%>">
								
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 259px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>