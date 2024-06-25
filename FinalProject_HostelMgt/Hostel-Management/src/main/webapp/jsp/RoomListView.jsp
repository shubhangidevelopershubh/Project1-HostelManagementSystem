<%@page import="com.hostel.mgt.bean.AllotmentBean"%>
<%@page import="com.hostel.mgt.model.AllotmentModel"%>
<%@page import="com.hostel.mgt.bean.HostelBean"%>
<%@page import="com.hostel.mgt.model.HostelModel"%>
<%@page import="com.hostel.mgt.model.RoomModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.hostel.mgt.controller.RoomListCtl"%>
<%@page import="com.hostel.mgt.bean.RoomBean"%>
<%@page import="com.hostel.mgt.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Room List</title>
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
		
		<li class="breadcrumb-item active" aria-current="page">Room List</li>
	</ol>
	</nav>
<form action="<%=HMSView.ROOM_LIST_CTL%>" method="post">
		<div id="feedback">
			<div class="container">
				<div class="col-md-9">
					<div class="form-area">
							
							<h3 style="margin-bottom: 15px; text-align: left;">Room List</h3>
							<div class="form-row">
    							<div class="form-group col-lg-4">
								<input type="text" class="form-control"  name="room"
									placeholder="Room No." value="<%=ServletUtility.getParameter("room", request)%>" > 
							</div>
        					
							<div class="form-group col-lg-4">
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=RoomListCtl.OP_SEARCH%>">&nbsp;or&nbsp;
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=RoomListCtl.OP_RESET%>">
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
			<%if(userBean.getRoleId()==1){%>
				<th scope="col">Select</th>
				<%} %>
				<th scope="col">S.No</th>
				<th scope="col">Room No</th>
				<th scope="col">Hostel Name</th>
				<th scope="col">Description</th>
				<th scope="col">Status</th>
				<%if(userBean.getRoleId()==1){%>
				<th scope="col">Edit</th>
				<%} %>
			</tr>
		</thead>
		<tbody>
				<%
				
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					RoomBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<RoomBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
			<tr>
				<%if(userBean.getRoleId()==1){%>
				<td ><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"
						></td>
						<%} %>
				<td><%=index++%></td>
				<td><%=bean.getRoomNo()%></td>
				<% 
				HostelModel hModel=new HostelModel();
				 HostelBean hBean=hModel.findByPK(bean.getHostelId());
				%>
				<td><%=hBean.getName()%></td>
				<td><%=bean.getDescription()%></td>
				
				<%
					AllotmentModel aModel=new AllotmentModel();
					AllotmentBean aBean=new AllotmentBean();
					aBean.setRoomId(bean.getId());
					List li=aModel.search(aBean);
						
				%>
				<%if(li.size()==3){%>
				<td>No bed Available</td>
				<%}else{ 
					
					int no=3-li.size();
				%>
				<td><%=no%> bed Available</td>
				<%} %>
				<%if(userBean.getRoleId()==1){%>
				<td >
						<a class="btn btn-primary pull-right" href="room?id=<%=bean.getId()%>">Edit</a>
					</td>
					<%} %>
			</tr>
			
			<%} %>
		</tbody>
	</table>
	<hr>
	<table width="99%" style=" bottom: 45px">
				<tr>

					<td><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=RoomListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						<%if(userBean.getRoleId()==1){%>
					<td><input type="submit" name="operation"
						class="btn btn-primary pull-right"  value="<%=RoomListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation" class="btn btn-danger pull-right" 
						value="<%=RoomListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
						<%} %>
					<%
						RoomModel model = new RoomModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-primary pull-right" 
						value="<%=RoomListCtl.OP_NEXT%>"
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