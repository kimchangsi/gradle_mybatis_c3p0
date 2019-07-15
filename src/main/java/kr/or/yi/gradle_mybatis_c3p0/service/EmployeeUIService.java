package kr.or.yi.gradle_mybatis_c3p0.service;

import java.util.List;

import kr.or.yi.gradle_mybatis_c3p0.dao.DepartmentDao;
import kr.or.yi.gradle_mybatis_c3p0.dao.DepartmentDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dao.EmployeeDao;
import kr.or.yi.gradle_mybatis_c3p0.dao.EmployeeDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dao.TitleDao;
import kr.or.yi.gradle_mybatis_c3p0.dao.TitleDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dto.Department;
import kr.or.yi.gradle_mybatis_c3p0.dto.Employee;
import kr.or.yi.gradle_mybatis_c3p0.dto.Title;

public class EmployeeUIService {
	private TitleDao titleDao;
	private DepartmentDao deptDao;
	private EmployeeDao empDao;

	public EmployeeUIService() {
		titleDao = new TitleDaoImpl();
		deptDao = new DepartmentDaoImpl();
		empDao = new EmployeeDaoImpl();
	}

	public List<Employee> selectEmpAll() {
		
		return empDao.selectEmployeeByAll();
	}

	public List<Department> selectDeptAll() {
		return deptDao.selectDepartmentByAll();
	}

	public List<Title> selectTitleAll() {
		return titleDao.selectTitleByAll();
	}

	public int updateEmployeeItem(Employee emp) {
		return empDao.updateEmployee(emp);
	}
	
	public int insertEmployeeItem(Employee emp) {
		return empDao.insertEmployee(emp);
	}
	
	public int deleteEmployeeItem(Employee emp) {
		return empDao.deleteEmployee(emp);
	}

}
