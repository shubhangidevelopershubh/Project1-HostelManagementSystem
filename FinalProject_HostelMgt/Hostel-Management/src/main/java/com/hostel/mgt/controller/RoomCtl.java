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
import com.hostel.mgt.bean.HostelBean;
import com.hostel.mgt.bean.RoomBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.model.HostelModel;
import com.hostel.mgt.model.RoleModel;
import com.hostel.mgt.model.RoomModel;
import com.hostel.mgt.util.DataUtility;
import com.hostel.mgt.util.DataValidator;
import com.hostel.mgt.util.PropertyReader;
import com.hostel.mgt.util.ServletUtility;

/**
 * Servlet implementation class RoomCtl
 */
@ WebServlet(name="RoomCtl",urlPatterns={"/ctl/room"})
public class RoomCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(RoomCtl.class);
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("RoomCtl preload method start");
		HostelModel model = new HostelModel();
		try {
			List l = model.list();
			request.setAttribute("hostelList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("RoomCtl preload method end");
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("RoomCtl Method validate Started");

		boolean pass = true;

		
		 

		if (DataValidator.isNull(request.getParameter("room"))) {
			request.setAttribute("room",
					PropertyReader.getValue("error.require", " RoomNo"));
			pass = false;
		}

		

		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		

		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("hostelId"))) {
			request.setAttribute("hostelId",
					PropertyReader.getValue("error.require", "Hostel Name"));
			pass = false;
		}



		log.debug("RoomCtl Method validate Ended");

		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("RoomCtl Method populatebean Started");

		RoomBean bean = new RoomBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoomNo(DataUtility.getString(request	.getParameter("room")));

		bean.setHostelId(DataUtility.getLong(request.getParameter("hostelId")));


		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.debug("RoomCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoomCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		RoomModel model = new RoomModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			RoomBean bean;
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
        log.debug("RoomCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoomCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        
        RoomModel model = new RoomModel();
        
        long id = DataUtility.getLong(request.getParameter("id"));
        
        if (OP_SAVE.equalsIgnoreCase(op)) {
            RoomBean bean = (RoomBean) populateBean(request);
            
            try {
                if (id > 0) {
                    model.update(bean);
                
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                    long pk = model.add(bean);
                   // bean.setId(pk);
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

        	RoomBean bean = (RoomBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HMSView.ROOM_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HMSView.ROOM_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HMSView.ROOM_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("RoomCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.ROOM_VIEW;
	}

}
