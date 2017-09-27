package jdbc_study;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

public class TestMain {

	public static void main(String[] args) throws SQLException {

		//testDepartmentDao();
	
		EmployeeDao eao = EmployeeDao.getInstance();
		showEmployeeList(eao);
		
		Employee emp = new Employee(2017,"배수지","이사",4377,4500000,2);
		eao.insertEmployee(emp);
		showEmployeeList(eao);
		
		emp.setSalary(4000000);
		eao.updateEmployee(emp);
		showEmployeeList(eao);
		
		eao.deleteEmployee(emp);
		showEmployeeList(eao);
		
		Employee searchemp=eao.selectEmployeeNo(new Employee(1003));
		System.out.println("찾은 결과" + searchemp);
	}

	private static void showEmployeeList(EmployeeDao eao) {
		System.out.println("=========================================================");
		List<Employee> lists = eao.selectEmployeeAll();
		for(Employee emp :lists){
			System.out.println(emp);
		}
	}

	private static void testDepartmentDao() throws SQLException {
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
