package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import jdbc_study.dto.Employee;
import jdbc_study.jdbc.DBCon;
import jdbc_study.jdbc.JdbcUtil;

public class EmployeeDao {
	private static final EmployeeDao instance = new EmployeeDao();
	
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	
	private EmployeeDao() {}
	
	public static EmployeeDao getInstance(){
		return instance;
	}
	
	public List<Employee> selectEmployeeAll(){
		
		List<Employee> lists = new ArrayList<>();
		sql = "select empno,empname,title,manager,salary,dno from employee";
		con = DBCon.getInstance().getConn();
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				int empNo = rs.getInt("empno");
				String empName = rs.getString("empname");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
			
				lists.add(new Employee(empNo, empName, title, manager, salary, dno));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return lists;
		
	}
	
	public void insertEmployee(Employee emp){
		sql = "insert into employee values(?,?,?,?,?,?)";
		
		try {
			pstmt=DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getTitle());
			pstmt.setInt(4, emp.getManager());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDno());
			
		/*	
			int res = pstmt.executeUpdate();
			if(res<0){
				System.out.println("dd");
				return;
			}*/
			JOptionPane.showMessageDialog(null, emp+"추가하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void updateEmployee(Employee emp){
		sql="update employee set empname=?,title=?,manager=?,salary=?,dno=? where empno=?";
		try {
			pstmt=DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getTitle());
			pstmt.setInt(3, emp.getManager());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDno());
			pstmt.setInt(6, emp.getEmpNo());
			
			JOptionPane.showMessageDialog(null, emp+"갱신하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
		
		
	}
	
	public void deleteEmployee(Employee emp){
		sql="delete from employee where empno=?";
		try {
			pstmt=DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			JOptionPane.showMessageDialog(null, emp+"삭제하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Employee selectEmployeeNo(Employee emp){
		Employee employee=null;
		sql="select empno,empname,title,manager,salary,dno from employee where empno=?";
		
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpNo());
			rs = pstmt.executeQuery();
			if(rs.next()){
				int empNo = rs.getInt(1);
				String empName = rs.getString(2);
				String title = rs.getString(3);
				int manager = rs.getInt(4);
				int salary = rs.getInt(5);
				int dno = rs.getInt(6);
	
				employee = new Employee(empNo, empName, title, manager, salary, dno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}
}
