package com.hostel.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hostel.mgt.bean.HostelBean;
import com.hostel.mgt.bean.VisitorBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DatabaseException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.util.JDBCDataSource;

public class VisitorModel {

	private static Logger log = Logger.getLogger(VisitorModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_Visitor");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	public VisitorBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Visitor WHERE NAME=?");
		VisitorBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new VisitorBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setContactNo(rs.getString(3));
				bean.setStudentName(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setRelation(rs.getString(6));
				bean.setPurpose(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Hostel in Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}
	
	
	public VisitorBean findByPK(long Id) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Visitor WHERE Id=?");
		VisitorBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new VisitorBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setContactNo(rs.getString(3));
				bean.setStudentName(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setRelation(rs.getString(6));
				bean.setPurpose(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Hostel in Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}
	
	public long add(VisitorBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_visitor VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getContactNo());
			pstmt.setString(4, bean.getStudentName());
			pstmt.setString(5, bean.getAddress());
			pstmt.setString(6, bean.getRelation());
			pstmt.setString(7, bean.getPurpose());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Hostel");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	public void delete(VisitorBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Visitor WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	public void update(VisitorBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE H_Visitor SET NAME=?,Contact=?,StudentName=?,Address=?,relation=?,purpose=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,fee=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getContactNo());
			pstmt.setString(3, bean.getStudentName());
			pstmt.setString(4, bean.getAddress());
			pstmt.setString(5, bean.getRelation());
			pstmt.setString(6, bean.getPurpose());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Hostel ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	 public List search(VisitorBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

	   
	    public List search(VisitorBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM H_visitor WHERE 1=1");
	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getName() != null && bean.getName().length() > 0) {
					sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
	            }
	            if (bean.getStudentName() != null
	                    && bean.getStudentName().length() > 0) {
					sql.append(" AND DESCRIPTION LIKE '" + bean.getStudentName()
	                        + "%'");
	            }
	        }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" Limit " + pageNo + ", " + pageSize);
	            // sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                bean = new VisitorBean();
	                bean.setId(rs.getLong(1));
					bean.setName(rs.getString(2));
					bean.setContactNo(rs.getString(3));
					bean.setStudentName(rs.getString(4));
					bean.setAddress(rs.getString(5));
					bean.setRelation(rs.getString(6));
					bean.setPurpose(rs.getString(7));
					bean.setCreatedBy(rs.getString(8));
					bean.setModifiedBy(rs.getString(9));
					bean.setCreatedDatetime(rs.getTimestamp(10));
					bean.setModifiedDatetime(rs.getTimestamp(11));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Hostel");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model search End");
	        return list;
	    }
	    
	    public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	   
	    public List list(int pageNo, int pageSize) throws ApplicationException {
	        log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from H_VISITOR");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	            	VisitorBean bean = new VisitorBean();
	            	 bean.setId(rs.getLong(1));
						bean.setName(rs.getString(2));
						bean.setContactNo(rs.getString(3));
						bean.setStudentName(rs.getString(4));
						bean.setAddress(rs.getString(5));
						bean.setRelation(rs.getString(6));
						bean.setPurpose(rs.getString(7));
						bean.setCreatedBy(rs.getString(8));
						bean.setModifiedBy(rs.getString(9));
						bean.setCreatedDatetime(rs.getTimestamp(10));
						bean.setModifiedDatetime(rs.getTimestamp(11));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Hostel");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model list End");
	        return list;

	    }
}
