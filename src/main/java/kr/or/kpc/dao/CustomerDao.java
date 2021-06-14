package kr.or.kpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.kpc.dto.CustomerDto;
import kr.or.kpc.util.ConnLocator;

public class CustomerDao {
	private static CustomerDao single;

	private CustomerDao() {
	}

	public static CustomerDao getInstance() {
		if (single == null) {
			single = new CustomerDao();
		}
		return single;
	}
	public boolean insert(CustomerDto dto) {
		boolean success = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO customer(c_num, c_email,c_pwd,c_name, ");
			sql.append("c_status, c_regdate) VALUES(?,?, ");
			sql.append("PASSWORD(?),?,?,NOW()) ");

			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setInt(index++, dto.getNum());
			pstmt.setString(index++, dto.getEmail());
			pstmt.setString(index++, dto.getPwd());
			pstmt.setString(index++, dto.getName());
			pstmt.setString(index++, dto.getStatus());

			pstmt.executeUpdate();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return success;
	}
	public boolean update(CustomerDto dto) {
		boolean success = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnLocator.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE customer ");
			sql.append("SET c_pwd = PASSWORD(?), c_name =?, ");
			sql.append("c_status=? ");
			sql.append("WHERE c_num = ? ");

			pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, dto.getPwd());
			pstmt.setString(index++, dto.getName());
			pstmt.setString(index++, dto.getStatus());
			pstmt.setInt(index++, dto.getNum());

			pstmt.executeUpdate();
			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return success;
	}
}





