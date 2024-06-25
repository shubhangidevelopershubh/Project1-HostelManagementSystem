package com.hostel.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hostel.mgt.bean.FeeBean;
import com.hostel.mgt.bean.HostelBean;
import com.hostel.mgt.bean.RoomBean;
import com.hostel.mgt.bean.UserBean;
import com.hostel.mgt.exception.ApplicationException;
import com.hostel.mgt.exception.DatabaseException;
import com.hostel.mgt.exception.DuplicateRecordException;
import com.hostel.mgt.util.JDBCDataSource;

public class FeeModel {
	
	
	private static Logger log = Logger.getLogger(FeeModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_fee");
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
	
	
	public FeeBean findByPk(long id) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Fee  where Id=? ");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setHostelId(rs.getLong(4));
				bean.setHostelName(rs.getString(5));
				bean.setRoomId(rs.getLong(6));
				bean.setRoomName(rs.getString(7));
				bean.setTotalfee(rs.getString(8));
				bean.setPay(rs.getString(9));
				bean.setPaidfee(rs.getString(10));
				bean.setRemainingfee(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Room No");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}
	
	
	public FeeBean findByPkUserId(long id) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Fee  where UserId=? ");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setHostelId(rs.getLong(4));
				bean.setHostelName(rs.getString(5));
				bean.setRoomId(rs.getLong(6));
				bean.setRoomName(rs.getString(7));
				bean.setTotalfee(rs.getString(8));
				bean.setPay(rs.getString(9));
				bean.setPaidfee(rs.getString(10));
				bean.setRemainingfee(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Room No");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}
	
	public FeeBean findByUserIdAndHostelIdAndRoomId(long userId,long hostelId,long roomId) throws ApplicationException {
		log.debug("Model findBy EmailId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Fee  where userId=? and hostelId=? and roomId=?");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			pstmt.setLong(2, hostelId);
			pstmt.setLong(3, roomId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setHostelId(rs.getLong(4));
				bean.setHostelName(rs.getString(5));
				bean.setRoomId(rs.getLong(6));
				bean.setRoomName(rs.getString(7));
				bean.setTotalfee(rs.getString(8));
				bean.setPay(rs.getString(9));
				bean.setPaidfee(rs.getString(10));
				bean.setRemainingfee(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Room No");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}
	
	
	public long add(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		
		HostelModel hModel=new HostelModel();
		HostelBean hBean=hModel.findByPK(bean.getHostelId());
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getUserId());
		
		RoomModel rModel=new RoomModel();
		RoomBean rBean=rModel.findByPK(bean.getRoomId());
		
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO H_fee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getUserId());
			pstmt.setString(3, uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(4, bean.getHostelId());
			pstmt.setString(5, hBean.getName());
			pstmt.setLong(6, bean.getRoomId());
			pstmt.setString(7,rBean.getRoomNo());
			pstmt.setString(8,bean.getTotalfee());
			pstmt.setString(9,bean.getPay());
			pstmt.setString(10,bean.getPaidfee());
			pstmt.setString(11,bean.getRemainingfee());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Room");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	public void delete(FeeBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_fee WHERE ID=?");
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
	
	
	public void update(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		
		
		HostelModel hModel=new HostelModel();
		HostelBean hBean=hModel.findByPK(bean.getHostelId());
		
		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getUserId());
		
		RoomModel rModel=new RoomModel();
		RoomBean rBean=rModel.findByPK(bean.getRoomId());
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE H_fee SET UserId=?,name=?,hostelId=?,hostelName=?,roomId=?,roomName=?,totalfee=?,pay=?,paidfee=?,Remainingfee=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getUserId());
			pstmt.setString(2, uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(3, bean.getHostelId());
			pstmt.setString(4, hBean.getName());
			pstmt.setLong(5, bean.getRoomId());
			pstmt.setString(6,rBean.getRoomNo());
			pstmt.setString(7,bean.getTotalfee());
			pstmt.setString(8,bean.getPay());
			pstmt.setString(9,bean.getPaidfee());
			pstmt.setString(10,bean.getRemainingfee());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setLong(15, bean.getId());
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
			throw new ApplicationException("Exception in updating Room ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(FeeBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

   
    public List search(FeeBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM H_fee WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getHostelId() > 0) {
                sql.append(" AND HostelId = " + bean.getHostelId());
            }
            if (bean.getUserId() > 0) {
                sql.append(" AND UserId = " + bean.getUserId());
            }
            if (bean.getRoomId() > 0) {
                sql.append(" AND RoomId = " + bean.getRoomId());
            }
            if (bean.getRoomName() != null && bean.getRoomName().length() > 0) {
				sql.append(" AND RoomName LIKE '" + bean.getName() + "%'");
            }
            if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name LIKE '" + bean.getName() + "%'");
            }
            if (bean.getHostelName() != null && bean.getHostelName().length() > 0) {
				sql.append(" AND RoomNo LIKE '" + bean.getHostelName() + "%'");
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
                bean = new FeeBean();
                bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setHostelId(rs.getLong(4));
				bean.setHostelName(rs.getString(5));
				bean.setRoomId(rs.getLong(6));
				bean.setRoomName(rs.getString(7));
				bean.setTotalfee(rs.getString(8));
				bean.setPay(rs.getString(9));
				bean.setPaidfee(rs.getString(10));
				bean.setRemainingfee(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Role");
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
        StringBuffer sql = new StringBuffer("select * from H_Fee");
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
                FeeBean bean = new FeeBean();
                bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setHostelId(rs.getLong(4));
				bean.setHostelName(rs.getString(5));
				bean.setRoomId(rs.getLong(6));
				bean.setRoomName(rs.getString(7));
				bean.setTotalfee(rs.getString(8));
				bean.setPay(rs.getString(9));
				bean.setPaidfee(rs.getString(10));
				bean.setRemainingfee(rs.getString(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }
	

}
