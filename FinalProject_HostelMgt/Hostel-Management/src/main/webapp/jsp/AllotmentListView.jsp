<%@page import="com.hostel.mgt.bean.FeeBean"%>
<%@page import="com.hostel.mgt.model.FeeModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.hostel.mgt.bean.AllotmentBean"%>
<%@page import="com.hostel.mgt.model.AllotmentModel"%>
<%@page import="com.hostel.mgt.controller.AllotmentListCtl"%>
<%@page import="com.hostel.mgt.controller.ApplicationListCtl"%>
<%@page import="com.hostel.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Allotment List</title>
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
		
		<li class="breadcrumb-item active" aria-current="page">Allotment List</li>
	</ol>
	</nav>
<form action="<%=HMSView.ROOM_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							
							<h3 style="margin-bottom: 15px; text-align: left;">Allotment List</h3>
							<% if(userBean.getRoleId()!=2){ %>
							<div class="form-row">
    							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=ServletUtility.getParameter("Name", request)%>" > 
							</div>
        					
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=AllotmentListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=AllotmentListCtl.OP_RESET%>">
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
				<th scope="col">Fee Status</th>
				<%if(userBean.getRoleId()==3){%>
				
				<th scope="col">Pay Fee</th>
				<%} %>
			</tr>
		</thead>
		<tbody>
				<%
				
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					AllotmentBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<AllotmentBean> it = list.iterator();
					
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
				<td><%=bean.getRoomNo()%></td>
				
				<% FeeModel fModel=new FeeModel(); 
					FeeBean fBean=fModel.findByPkUserId(bean.getUserId());
				%>
				<% if(fBean!=null){ %>
				
				<%if(fBean.getTotalfee()==fBean.getPaidfee()){%>
				<td>No Remaining fee</td>
				<%}else{%>
				<td><%=fBean.getRemainingfee()%>&nbsp;Rs.&nbsp;Fee Remaining </td>
				<%} %>
				<%}else{ %>
				<td>Total Fee Remaining </td>
				<%} %>
				<%if(userBean.getRoleId()==3){%>
					
					<% if(fBean!=null){ %>
					<td >
						<a class="btn btn-primary pull-right" <%=(fBean.getTotalfee()==fBean.getPaidfee())?"disabled":""%> href="fee?aId=<%=bean.getId()%>">FeePay</a>
					</td>
					<%}else{ %>
					<td >
						<a class="btn btn-primary pull-right"  href="fee?aId=<%=bean.getId()%>">FeePay</a>
					</td>
					<%} %>
					<%} %>
			</tr>
			
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=AllotmentListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						<%if(userBean.getRoleId()==3){%>
					<td><input type="submit" name="operation"
						class="btn btn-primary pull-right"  value="<%=AllotmentListCtl.OP_NEW%>"></td>
					
						<%} %>
					<%
						AllotmentModel model = new AllotmentModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=AllotmentListCtl.OP_NEXT%>"
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