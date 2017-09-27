package jdbc_study;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;

public class TestMain {

	public static void main(String[] args) throws SQLException {
	/*	DBCon dbCon = DBCon.getInstance(); //프로젝트가 끝날때까지 하나의 객체만 생성한다. 
		System.out.println(dbCon);
		System.out.println(dbCon.getConn());
		//dbCon.connClose();
		
		DBCon dbCon1 = DBCon.getInstance(); //동일한 주소를 갖는다. 하나의 객체만 생성되므로
		System.out.println(dbCon1);
		System.out.println(dbCon1.getConn()); //close를 해서 null값이 나오는것임
		
		dbCon.connClose();*/
		
		//select
		DepartmentDao dao = DepartmentDao.getInstance();
		showDepartmentList(dao);
		
		
		//insert
		Department dept = new Department(6, "마케팅", 10);
		dao.insertDepartment(dept);
		showDepartmentList(dao);
		
		
		//update
		dept.setDeptName("빅데이터 마케팅");
		dao.updateDepartment(dept);
		showDepartmentList(dao);
		
		//delete
		dao.deleteDepartment(dept);
		showDepartmentList(dao);
		
		//select where
		Department searchDept=dao.selectDepartmentByNo(new Department(1));
		System.out.println("찾은 결과" + searchDept);
	
	}

	private static void showDepartmentList(DepartmentDao dao) throws SQLException{
		//DepartmentDao dao = DepartmentDao.getInstance();
		System.out.println("=============================================");
		List<Department> lists =dao.selectDepartmentByAll();
		for(Department dept : lists){
			System.out.println(dept);
		}
	}

}
