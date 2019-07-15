package kr.or.yi.gradle_mybatis_c3p0.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.yi.gradle_mybatis_c3p0.dto.Employee;
import kr.or.yi.gradle_mybatis_c3p0.jdbc.MybatisSqlSessionFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final String namespace = "kr.or.yi.gradle_mybatis_c3p0.dao.EmployeeDao"; //맵퍼의 namespace와 같아야함!!!!
	@Override
	public List<Employee> selectEmployeeByAll() {
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			return sqlSession.selectList(namespace+".selectEmployeeByAll");
		}
	}

}
