package com.hostel.mgt.controller;

public interface HMSView {
	
	public String APP_CONTEXT = "/Hostel-Management";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	
	
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	
	
	public String HOSTEL_VIEW = PAGE_FOLDER + "/HostelView.jsp";
	public String HOSTEL_LIST_VIEW = PAGE_FOLDER + "/HostelListView.jsp";
	
	public String VISITOR_VIEW = PAGE_FOLDER + "/VisitorView.jsp";
	public String VISITOR_LIST_VIEW = PAGE_FOLDER + "/VisitorListView.jsp";
	
	public String ALLOTMENT_VIEW = PAGE_FOLDER + "/AllotmentView.jsp";
	public String ALLOTMENT_LIST_VIEW = PAGE_FOLDER + "/AllotmentListView.jsp";
	
	public String APPLICATION_VIEW = PAGE_FOLDER + "/ApplicationView.jsp";
	public String APPLICATION_LIST_VIEW = PAGE_FOLDER + "/ApplicationListView.jsp";
	
	public String ROOM_VIEW = PAGE_FOLDER + "/RoomView.jsp";
	public String ROOM_LIST_VIEW = PAGE_FOLDER + "/RoomListView.jsp";
	
	public String FEE_VIEW = PAGE_FOLDER + "/FeeView.jsp";
	public String FEE_LIST_VIEW = PAGE_FOLDER + "/FeeListView.jsp";
	
	public String WARDEN_VIEW = PAGE_FOLDER + "/WardenView.jsp";
	public String WARDEN_LIST_VIEW = PAGE_FOLDER + "/WardenListView.jsp";
	
	
		
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	
	public String USER_CTL = APP_CONTEXT + "/ctl/user";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/userList";
	
	public String APPLICATION_CTL = APP_CONTEXT + "/ctl/application";
	public String APPLICATION_LIST_CTL = APP_CONTEXT + "/ctl/applicationList";
	
	public String ALLOTMENT_CTL = APP_CONTEXT + "/ctl/allotment";
	public String ALLOTMENT_LIST_CTL = APP_CONTEXT + "/ctl/allotmentList";
	
	public String HOSTEL_CTL = APP_CONTEXT + "/ctl/hostel";
	public String HOSTEL_LIST_CTL = APP_CONTEXT + "/ctl/hostelList";
	
	public String ROOM_CTL = APP_CONTEXT + "/ctl/room";
	public String ROOM_LIST_CTL = APP_CONTEXT + "/ctl/roomList";
	
	public String FEE_CTL = APP_CONTEXT + "/ctl/fee";
	public String FEE_LIST_CTL = APP_CONTEXT + "/ctl/feeList";
	
	public String VISITOR_CTL = APP_CONTEXT + "/ctl/visitor";
	public String VISITOR_LIST_CTL = APP_CONTEXT + "/ctl/visitorList";
	
	public String WARDEN_CTL = APP_CONTEXT + "/ctl/warden";
	public String WARDEN_LIST_CTL = APP_CONTEXT + "/ctl/wardenList";
	
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	
	
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/register";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";



}
