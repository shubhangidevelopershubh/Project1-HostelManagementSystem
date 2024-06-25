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

import com.hostel.mgt.bean.ApplicationBean;
import com.hostel.mgt.bean.BaseBean;
import com.hostel.mgt.bean.UserBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.model.ApplicationModel;
import com.hostel.mgt.model.HostelModel;
import com.hostel.mgt.util.DataUtility;
import com.hostel.mgt.util.DataValidator;
import com.hostel.mgt.util.PropertyReader;
import com.hostel.mgt.util.ServletUtility;

/**
 * Servlet implementation class ApplicationCtl
 */
@ WebServlet(name="ApplicationCtl",urlPatterns={"/ctl/application"})
public class ApplicationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(ApplicationCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("ApplicationCtl preload method start");
		HostelModel model = new HostelModel();
		try {
			List l = model.list();
			request.setAttribute("hostelList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("ApplicationCtl preload method end");
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ApplicationCtl Method validate Started");

		boolean pass = true;

		
		 

		if (DataValidator.isNull(request.getParameter("qual"))) {
			request.setAttribute("qual",
					PropertyReader.getValue("error.require", " Qualification"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "address"));
			pass = false;
		}

		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("hostel"))) {
			request.setAttribute("hostel",
					PropertyReader.getValue("error.require", "Hostel Name"));
			pass = false;
		}



		log.debug("ApplicationCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("ApplicationCtl Method populatebean Started");

		ApplicationBean bean = new ApplicationBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setHostelId(DataUtility.getLong(request.getParameter("hostel")));

		bean.setQualification(DataUtility.getString(request.getParameter("qual")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);

		log.debug("ApplicationCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		ApplicationModel model = new ApplicationModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			ApplicationBean bean;
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
        log.debug("ApplicationCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ApplicationCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        ApplicationModel model = new ApplicationModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            ApplicationBean bean = (ApplicationBean) populateBean(request);
            HttpSession session=request.getSession();
            UserBean uBean=(UserBean)session.getAttribute("user");
            bean.setUserId(uBean.getId());
            bean.setName(uBean.getFirstName()+" "+uBean.getLastName());
            try {
                if (id > 0) {
                    /*model.update(bean);*/
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

        	ApplicationBean bean = (ApplicationBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HMSView.APPLICATION_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HMSView.APPLICATION_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HMSView.APPLICATION_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("ApplicationCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.APPLICATION_VIEW;
	}

}
