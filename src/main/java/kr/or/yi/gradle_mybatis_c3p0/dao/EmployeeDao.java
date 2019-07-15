package kr.or.yi.gradle_mybatis_c3p0.dao;

import java.util.List;

import kr.or.yi.gradle_mybatis_c3p0.dto.Employee;
import kr.or.yi.gradle_mybatis_c3p0.dto.Title;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	int insertEmployee(Employee employee);
	Employee selectEmployeeByCode(Employee employee);
}