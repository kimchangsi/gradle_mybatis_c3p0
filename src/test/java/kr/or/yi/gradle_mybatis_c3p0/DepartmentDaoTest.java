package kr.or.yi.gradle_mybatis_c3p0;


import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.yi.gradle_mybatis_c3p0.dao.DepartmentDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest extends AbstractTest {
	private static DepartmentDaoImpl deptDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		deptDao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		deptDao = null;
	}
	
	@Test
	public void test02SelectDepartmentByAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Department> deptList = deptDao.selectDepartmentByAll();
		Assert.assertNotNull(deptList);
		for(Department d : deptList) {
			log.debug(String.format("%d -> %s , %d%n",d.getDeptCode(),d.getDeptName(),d.getDeptFloor()));
		}
	}
	
	@Test
	public void test01InsertDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department insertDept = new Department(6, "가가",1);
		int res = deptDao.insertDepartment(insertDept);
		log.debug("result = " + res);
		Assert.assertEquals(1, res); // res 가1이면  성공
	}
	
	@Test
	public void test03UpdateDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department updateTitle = new Department(6,"수정",1);
		int res = deptDao.updateDepartment(updateTitle);
		log.debug("result = " + res);
		Assert.assertEquals(1, res); // res 가1이면  성공
	}
	
	@Test
	public void test04DeleteDepartment() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department deleteTitle = new Department(6);
		int res = deptDao.deleteDepartment(deleteTitle);
		log.debug("result = " + res);
		Assert.assertEquals(1, res); // res 가1이면  성공
	}
	
	@Test
	public void test05SelectDepartmentByCode() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Department selectDept = new Department(1);
		Department searchDept = deptDao.selectDepartmentByCode(selectDept);
		log.debug("searchDept = " + searchDept);
		Assert.assertNotNull(searchDept); // res 가1이면  성공
	}
}
