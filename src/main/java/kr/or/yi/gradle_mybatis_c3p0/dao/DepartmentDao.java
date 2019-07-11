package kr.or.yi.gradle_mybatis_c3p0.dao;

import java.util.List;

import kr.or.yi.gradle_mybatis_c3p0.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	int insertDepartment(Department dept);
	int deleteDepartment(Department dept);
	int updateDepartment(Department dept);
	Department selectDepartmentByCode(Department dept);
}
