package kr.or.yi.gradle_mybatis_c3p0;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.yi.gradle_mybatis_c3p0.dao.EmployeeDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dto.Department;
import kr.or.yi.gradle_mybatis_c3p0.dto.Employee;
import kr.or.yi.gradle_mybatis_c3p0.dto.State;
import kr.or.yi.gradle_mybatis_c3p0.dto.Title;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest extends AbstractTest {
	private static EmployeeDaoImpl empDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empDao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void test02SelectEmployeeByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Employee> empList = empDao.selectEmployeeByAll();
		Assert.assertNotNull(empList);
		for(Employee e : empList) {
			log.debug(e.toString());
		}
	}
	
	@Test
	public void test01InsertEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		java.util.Date joindate = new java.util.Date();
		Employee insertEmployee = new Employee(5000,"케이시",2000000,new Department(1),true,joindate,new Title(3));
		int res = empDao.insertEmployee(insertEmployee);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test04DeleteEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Employee deleteEmp = new Employee(5000, "케이시");
		int res = empDao.deleteEmployee(deleteEmp);
		log.debug("result = " + res);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void test05SelectEmployeeByCode() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Employee searchEmp = new Employee(1003,"조민희");
		
		Employee res = empDao.selectEmployeeByCode(searchEmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void test03UpdateEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Calendar join = Calendar.getInstance();
		join.clear();
		join.set(Calendar.YEAR, 2016);
		join.set(Calendar.MONTH,7);
		join.set(Calendar.DAY_OF_MONTH,01);
		
		Employee upemp = new Employee(5000, "케이시", 2000000, new Department(3),true, join.getTime(), new Title(2));
		
		int res = empDao.updateEmployee(upemp);
		Assert.assertEquals(1, res);
		//원상 복원
		
	}
	
	@Test
	public void test06getSalaryByDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Map<String,Object> param = new HashMap<>();
		param.put("deptno", 1);
		
		Map<String,Object> result = empDao.getSalaryByDepartment(param);
		for(Entry<String,Object> e : result.entrySet()) {
			log.debug(String.format("%s -> %d", e.getKey(),e.getValue()));
		}
		
		Assert.assertNotEquals(0, result.size());
	}
	
	@Test
	public void test07getStateSalaryByDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Map<String,Object> param = new HashMap<>();
		param.put("deptno", 2);
		
		State state = empDao.getStateSalaryByDepartment(param);
		log.debug(state.toString());
		
		Assert.assertNotEquals(null, state);
	}

}
