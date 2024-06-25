
<%@page import="com.hostel.mgt.controller.LoginCtl"%>
<%@page import="com.hostel.mgt.bean.UserBean"%>
<%@page import="com.hostel.mgt.controller.HMSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=HMSView.APP_CONTEXT%>/css/bootstrap.min.css" >
<script src="<%=HMSView.APP_CONTEXT%>/js/jquery-3.3.1.slim.min.js" ></script>
<script src="<%=HMSView.APP_CONTEXT%>/js/popper.min.js" ></script>
<script src="<%=HMSView.APP_CONTEXT%>/js/bootstrap.min.js" ></script>

</head>
<body>

 <%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="#">Hostel Management</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    
   		<li class="nav-item active">
        <a class="nav-link" href="<%=HMSView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
      	</li>
      	<%if(userLoggedIn){%>
      	<%if(userBean.getRoleId()==1){ %>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          User
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.USER_CTL%>">Add User</a>
          <a class="dropdown-item" href="<%=HMSView.USER_LIST_CTL%>">User List</a>
          
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Hostel
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.HOSTEL_CTL%>">Add Hostel</a>
          <a class="dropdown-item" href="<%=HMSView.HOSTEL_LIST_CTL%>">Hostel List</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Room
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.ROOM_CTL%>">Add Room</a>
          <a class="dropdown-item" href="<%=HMSView.ROOM_LIST_CTL%>">Room List</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Warden
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.WARDEN_CTL%>">Add Warden</a>
          <a class="dropdown-item" href="<%=HMSView.WARDEN_LIST_CTL%>">Warden List</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.ALLOTMENT_LIST_CTL%>">Alloted Room</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.FEE_LIST_CTL%>">Payment List</a>
      </li>
      
      <%}else if(userBean.getRoleId()==2){%>
       <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.APPLICATION_CTL%>">Application</a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.HOSTEL_LIST_CTL%>">Hostel List</a>
      </li>
      
     <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.ROOM_LIST_CTL%>">Room List</a>
      </li>
      
       <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.ALLOTMENT_LIST_CTL%>">Alloted Room</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.FEE_LIST_CTL%>">Payment List</a>
      </li>
       
      <%}else if(userBean.getRoleId()==3){ %>
     
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.APPLICATION_LIST_CTL%>">Application List</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.ROOM_LIST_CTL%>">Room List</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.ALLOTMENT_LIST_CTL%>">Alloted Room</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%=HMSView.FEE_LIST_CTL%>">Payment List</a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Visitor
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.VISITOR_CTL%>">Add Visitor</a>
          <a class="dropdown-item" href="<%=HMSView.VISITOR_LIST_CTL%>">Visitor List</a>
        </div>
      </li>
     
      <%} %>
      <%} %>
    </ul>
    <form class="form-inline my-2 my-lg-0">
     <ul class="navbar-nav mr-auto">
     <%if (userLoggedIn){%>
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.MY_PROFILE_CTL%>">My Profile</a>
          <a class="dropdown-item" href="<%=HMSView.CHANGE_PASSWORD_CTL%>">Change Password</a>
          <a class="dropdown-item" href="<%=HMSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
        </div>
      </li>
      <%}else { %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=welcomeMsg%>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=HMSView.LOGIN_CTL%>">SignIn</a>
          <a class="dropdown-item" href="<%=HMSView.USER_REGISTRATION_CTL%>">SignUp</a>
          
        </div>
      </li>
      <%} %>
     </ul>
      
    </form>
  </div>
</nav>
</body>
</html>