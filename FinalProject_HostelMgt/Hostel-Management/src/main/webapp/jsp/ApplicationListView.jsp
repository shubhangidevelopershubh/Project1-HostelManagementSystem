<%@page import="com.hostel.mgt.model.UserModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.hostel.mgt.bean.ApplicationBean"%>
<%@page import="com.hostel.mgt.model.ApplicationModel"%>
<%@page import="com.hostel.mgt.controller.ApplicationListCtl"%>
<%@page import="com.hostel.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application List</title>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
<%@ include file="Header.jsp"%>
<br>

 	<nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Application List</li>
	</ol>
	</nav>
<form action="<%=HMSView.APPLICATION_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							
							<h3 style="margin-bottom: 15px; text-align: left;">Application List</h3>
							<div class="form-row">
    							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=ServletUtility.getParameter("name", request)%>" > 
							</div>
        					
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ApplicationListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ApplicationListCtl.OP_RESET%>">
							</div>
							</div>
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
				<th scope="col">Select All</th>
				<th scope="col">S.No</th>
				<th scope="col">Profile</th>
				<th scope="col">Name</th>
				<th scope="col">Hostel Name</th>
				<th scope="col">Contact</th>
				<th scope="col">Address</th>
				<th scope="col">Description</th>
				<th scope="col">Allot</th>
			</tr>
		</thead>
		<tbody>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					ApplicationBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<ApplicationBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
			<tr>
				
				<td ><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"
						></td>
				<td><%=index++%></td>
				<%
					UserModel uModel=new UserModel();
					 UserBean uBean =uModel.findByPK(bean.getUserId());
				%>
				<td><img width="100px" height="100px" src="<%=HMSView.APP_CONTEXT%>/image/<%=uBean.getImage()%>"></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getHostelName()%></td>
				
				
				<td><%=uBean.getMobileNo()%></td>
				<td><%=bean.getAddress()%></td>
				<td><%=bean.getDescription()%></td>
				<td >
						<a class="btn btn-primary pull-right" href="allotment?uId=<%=bean.getId()%>">Allot</a>
					</td>
				
			</tr>
			
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=ApplicationListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					
					
					<%
						ApplicationModel model = new ApplicationModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=ApplicationListCtl.OP_NEXT%>"
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