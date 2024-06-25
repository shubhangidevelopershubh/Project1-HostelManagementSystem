package com.hostel.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hostel.mgt.bean.BaseBean;
import com.hostel.mgt.bean.RoomBean;
import com.hostel.mgt.bean.UserBean;
import com.hostel.mgt.bean.WardenBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.model.HostelModel;
import com.hostel.mgt.model.RoomModel;
import com.hostel.mgt.model.UserModel;
import com.hostel.mgt.model.WardenModel;
import com.hostel.mgt.util.DataUtility;
import com.hostel.mgt.util.DataValidator;
import com.hostel.mgt.util.PropertyReader;
import com.hostel.mgt.util.ServletUtility;

/**
 * Servlet implementation class WardenCtl
 */
@ WebServlet(name="WardenCtl",urlPatterns={"/ctl/warden"})
public class WardenCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
private static Logger log = Logger.getLogger(RoomCtl.class);
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("WardenCtl preload method start");
		HostelModel model = new HostelModel();
		UserModel uModel=new UserModel();
		UserBean bean=new UserBean();
		bean.setRoleId(3L);
		try {
			List l = model.list();
			List l2 =uModel.search(bean);
			request.setAttribute("hostelList", l);
			request.setAttribute("userList", l2);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("WardenCtl preload method end");
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("WardenCtl Method validate Started");

		boolean pass = true;

		
	
		
		
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("hostelId"))) {
			request.setAttribute("hostelId",
					PropertyReader.getValue("error.require", "Hostel Name"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("userId"))) {
			request.setAttribute("userId",
					PropertyReader.getValue("error.require", "Warden Name"));
			pass = false;
		}



		log.debug("WardenCtl Method validate Ended");

		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("WardenCtl Method populatebean Started");

		WardenBean bean = new WardenBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setUserId(DataUtility.getLong(request.getParameter("userId")));

		bean.setHostelId(DataUtility.getLong(request.getParameter("hostelId")));

		populateDTO(bean, request);

		log.debug("WardenCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("WardenCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		WardenModel model = new WardenModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			WardenBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("WardenCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("WardenCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        
        WardenModel model = new WardenModel();
        
        long id = DataUtility.getLong(request.getParameter("id"));
        
        if (OP_SAVE.equalsIgnoreCase(op)) {
            WardenBean bean = (WardenBean) populateBean(request);
            
            try {
                if (id > 0) {
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                    long pk = model.add(bean);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }
              
               
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage(e.getMessage(), request);
            }
            ServletUtility.forward(getView(), request, response);
        } else if (OP_DELETE.equalsIgnoreCase(op)) {

        	WardenBean bean = (WardenBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HMSView.WARDEN_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HMSView.WARDEN_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HMSView.WARDEN_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("WardenCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.WARDEN_VIEW;
	}

}
