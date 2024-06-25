package com.hostel.mgt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hostel.mgt.bean.AllotmentBean;
import com.hostel.mgt.bean.BaseBean;
import com.hostel.mgt.bean.FeeBean;
import com.hostel.mgt.bean.HostelBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.model.AllotmentModel;
import com.hostel.mgt.model.FeeModel;
import com.hostel.mgt.model.HostelModel;
import com.hostel.mgt.util.DataUtility;
import com.hostel.mgt.util.DataValidator;
import com.hostel.mgt.util.PropertyReader;
import com.hostel.mgt.util.ServletUtility;

/**
 * Servlet implementation class FeeCtl
 */
@ WebServlet(name="FeeCtl",urlPatterns={"/ctl/fee"})
public class FeeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
private static Logger log = Logger.getLogger(FeeCtl.class);
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FeeCtl Method validate Started");

		boolean pass = true;



		if (DataValidator.isNull(request.getParameter("pay"))) {
			request.setAttribute("pay",
					PropertyReader.getValue("error.require", " Pay Amount"));
			pass = false;
		}


		log.debug("FeeCtl Method validate Ended");

		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FeeCtl Method populatebean Started");

		FeeBean bean = new FeeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setAllotmentId(DataUtility.getLong(request.getParameter("aId")));
		
		System.out.println("Application Id in Poulate Bean"+bean.getAllotmentId());

		HttpSession session=request.getSession();
		session.setAttribute("aId", bean.getAllotmentId());
		
		
		bean.setPay(DataUtility.getString(request.getParameter("pay")));

		populateDTO(bean, request);

		log.debug("FeeCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeeCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
		
		Long aId=DataUtility.getLong(request.getParameter("aId"));
		
		HttpSession session=request.getSession();
		session.setAttribute("aId", aId);
        
		FeeModel model = new FeeModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			FeeBean bean;
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
        log.debug("FeeCtl Method doGet Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeeCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        FeeModel model = new FeeModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            FeeBean bean = (FeeBean) populateBean(request);
            
            
            
            
            
            try {
            	
            	System.out.println("Allotead id in  fee Pstt"+bean.getAllotmentId());
            	AllotmentModel aModel=new AllotmentModel();
                AllotmentBean aBean=aModel.findByPk(bean.getAllotmentId());
                
                System.out.println("Hostel Id in fee post"+aBean.getHostelId());
                
                HostelModel hModel=new HostelModel();
                HostelBean hBean=hModel.findByPK(aBean.getHostelId());
                
                FeeBean fBean= model.findByUserIdAndHostelIdAndRoomId(aBean.getUserId(), aBean.getHostelId(), aBean.getRoomId());
                if(fBean!=null) {
                	
                	fBean.setPay(bean.getPay());
                	
                	long totalfee=DataUtility.getLong(fBean.getTotalfee());
                	long pay=DataUtility.getLong(fBean.getPay());
                	long paid=DataUtility.getLong(fBean.getPaidfee());
                	long rem=totalfee-(paid+pay);
                	
                	paid=paid+pay; 
                	fBean.setPaidfee(String.valueOf(paid));
                	
                	fBean.setRemainingfee(String.valueOf(rem));
                	model.update(fBean);
                }else {
                
                	bean.setUserId(aBean.getUserId());
                	bean.setHostelId(aBean.getHostelId());
                	bean.setRoomId(aBean.getRoomId());
                	
                	long totalfee=DataUtility.getLong(hBean.getFee());
                	long pay=DataUtility.getLong(bean.getPay());
                	long rem=totalfee-pay;
                	
                	bean.setTotalfee(hBean.getFee());
                	bean.setPay(bean.getPay());
                	bean.setPaidfee(bean.getPay());
                	bean.setRemainingfee(String.valueOf(rem));
                	
                	long pk = model.add(bean);
                }
                ServletUtility.setSuccessMessage("Data is successfully saved",request);
              
              
               
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

        	FeeBean bean = (FeeBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(HMSView.FEE_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(HMSView.FEE_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(HMSView.ALLOTMENT_LIST_CTL, request, response);
    		return;
    }
    					
        ServletUtility.forward(getView(), request, response);
        

        log.debug("FeeCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return HMSView.FEE_VIEW;
	}

}
