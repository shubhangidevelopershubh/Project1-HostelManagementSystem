package com.hostel.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hostel.mgt.bean.BaseBean;
import com.hostel.mgt.bean.HostelBean;
import com.hostel.mgt.bean.UserBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.model.HostelModel;
import com.hostel.mgt.model.UserModel;
import com.hostel.mgt.util.DataUtility;
import com.hostel.mgt.util.DataValidator;
import com.hostel.mgt.util.PropertyReader;
import com.hostel.mgt.util.ServletUtility;

/**
 * Servlet implementation class HostelCtl
 */
@ WebServlet(name="HostelCtl",urlPatterns={"/ctl/hostel"})
public class HostelCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(HostelCtl.class);
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("HostelCtl Method validate Started");

		boolean pass = true;

		
		if (DataValidator.isNull(request.getParameter("contact"))) {
			request.setAttribute("contact", PropertyReader.getValue("error.require","Contact No"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(request.getParameter("contact"))){
			request.setAttribute("contact", PropertyReader.getValue("error.invalid","Contact No"));
			pass=false;
		} 

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.require", " Name"));
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
		

		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("type"))) {
			request.setAttribute("type",
					PropertyReader.getValue("error.require", "Type"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("fee"))) {
			request.setAttribute("fee",
					PropertyReader.getValue("error.require", "Fee"));
			pass = false;
		}



		log.debug("Hostel Method validate Ended");

		return pass;
	}
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("HotelCtl Method populatebean Started");

		HostelBean bean = new HostelBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request	.getParameter("name")));

		bean.setType(DataUtility.getString(request.getParameter("type")));

		bean.setContact(DataUtility.getString(request.getParameter("contact")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		bean.setFee(DataUtility.getString(request.getParameter("fee")));

		populateDTO(bean, request);

		log.debug("HostelCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("HostelCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		HostelModel model = new HostelModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			HostelBean bean;
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
        log.debug("HostelCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        HostelModel model = new HostelModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            HostelBean bean = (HostelBean) populateBean(request);
            
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

        	HostelBean bean = (HostelBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HMSView.HOSTEL_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HMSView.HOSTEL_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HMSView.HOSTEL_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("HostelCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.HOSTEL_VIEW;
	}

}
