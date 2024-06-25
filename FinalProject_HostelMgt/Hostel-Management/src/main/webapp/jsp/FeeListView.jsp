<%@page import="com.hostel.mgt.model.FeeModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.hostel.mgt.bean.FeeBean"%>
<%@page import="com.hostel.mgt.controller.FeeListCtl"%>
<%@page import="com.hostel.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fee List View</title>
</head>
<body>
<%@ include file="Header.jsp"%>
<br>

 	<nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Fee List</li>
	</ol>
	</nav>
<form action="<%=HMSView.FEE_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							
							<h3 style="margin-bottom: 15px; text-align: left;">Fee List</h3>
							<% if(userBean.getRoleId()!=2){ %>
							<div class="form-row">
    							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=ServletUtility.getParameter("Name", request)%>" > 
							</div>
        					
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=FeeListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=FeeListCtl.OP_RESET%>">
							</div>
							</div>
							<%} %>
					</div>
				</div>
			</div>
		</div>
	<center>
		<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
	</center>
		
	<table class="table">
		<thead class="thead-dark">
			<tr>
			<%if(userBean.getRoleId()==3){%>
				<th scope="col">Select</th>
				<%} %>
				<th scope="col">S.No</th>
				<th scope="col">Name</th>
				<th scope="col">Hostel Name</th>
				<th scope="col">Room No</th>
				<th scope="col">Total Fee</th>
				<th scope="col">Paid Fee</th>
				<th scope="col">Last Pay</th>
				<th scope="col">Remaining Fee</th>
				
			</tr>
		</thead>
		<tbody>
				<%
				
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					FeeBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<FeeBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
			<tr>
				<%if(userBean.getRoleId()==3){%>
				<td ><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"
						></td>
						<%} %>
				<td><%=index++%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getHostelName()%></td>
				<td><%=bean.getRoomName()%></td>
				<td><%=bean.getTotalfee()%></td>
				<td><%=bean.getPaidfee()%></td>
				<td><%=bean.getPay()%></td>
				<td><%=bean.getRemainingfee()%></td>
					
					
			</tr>
			
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=FeeListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						<%if(userBean.getRoleId()==3){%>
					<td><input type="submit" name="operation"
						class="btn btn-primary pull-right"  value="<%=FeeListCtl.OP_NEW%>"></td>
					
						<%} %>
					<%
						FeeModel model = new FeeModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=FeeListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<br>
	<%@ include file="Footer.jsp"%>
</body>
</html>