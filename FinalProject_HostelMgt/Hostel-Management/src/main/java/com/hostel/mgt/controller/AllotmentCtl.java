package com.hostel.mgt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hostel.mgt.bean.AllotmentBean;
import com.hostel.mgt.bean.ApplicationBean;
import com.hostel.mgt.bean.BaseBean;
import com.hostel.mgt.bean.RoomBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.model.AllotmentModel;
import com.hostel.mgt.model.ApplicationModel;
import com.hostel.mgt.model.RoomModel;
import com.hostel.mgt.util.DataUtility;
import com.hostel.mgt.util.PropertyReader;
import com.hostel.mgt.util.ServletUtility;

/**
 * Servlet implementation class AllotmentCtl
 */
@ WebServlet(name="AllotmentCtl",urlPatterns={"/ctl/allotment"})
public class AllotmentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
    
	private static Logger log = Logger.getLogger(AllotmentCtl.class);
	
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("AllotmentCtl preload method start");
		RoomModel model = new RoomModel();
		ApplicationModel aModel=new ApplicationModel();
		ApplicationBean aBean=null;
		long userId=DataUtility.getLong(request.getParameter("uId"));
		System.out.println("application Id  "+userId);
		try {
			
			if(userId>0) {
				aBean=aModel.findByPK(userId);
			}
			RoomBean rBean=new RoomBean();
			
			rBean.setHostelId(aBean.getHostelId());
			
			List l = model.search(rBean);
			request.setAttribute("roomList", l);
			HttpSession session=request.getSession();
			session.setAttribute("allotmentId", userId);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("AllotmentCtl preload method end");
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("AllotmentCtl Method validate Started");

		boolean pass = true;

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("roomId"))) {
			request.setAttribute("roomId",
					PropertyReader.getValue("error.require", "Room No"));
			pass = false;
		}

		log.debug("AllotmentCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("AllotmentCtl Method populatebean Started");

		AllotmentBean bean = new AllotmentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRoomId(DataUtility.getLong(request.getParameter("roomId")));

		populateDTO(bean, request);

		log.debug("AllotmentCtl Method populatebean Ended");

		return bean;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AllotmentCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		AllotmentModel model = new AllotmentModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			AllotmentBean bean;
            try {
                bean = model.findByPk(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("AllotmentCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("AllotmentCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        
        AllotmentModel model = new AllotmentModel();
        
        long id = DataUtility.getLong(request.getParameter("id"));
        
        if (OP_SAVE.equalsIgnoreCase(op)) {
            AllotmentBean bean = (AllotmentBean) populateBean(request);
            
            HttpSession session=request.getSession();
            long aId=(long)session.getAttribute("allotmentId");
            ApplicationModel aModel=new ApplicationModel();
    		ApplicationBean aBean=null;
            
            try {
            	
            	if(aId>0) {
    				aBean=aModel.findByPK(aId);
    			}
            	
            	bean.setUserId(aBean.getUserId());
            	bean.setHostelId(aBean.getHostelId());
            	
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

        	AllotmentBean bean = (AllotmentBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HMSView.ALLOTMENT_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HMSView.ALLOTMENT_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HMSView.ALLOTMENT_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("AllotmentCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.ALLOTMENT_VIEW;
	}

}
