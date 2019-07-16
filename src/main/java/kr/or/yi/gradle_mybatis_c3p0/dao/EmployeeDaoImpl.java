package kr.or.yi.gradle_mybatis_c3p0.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import kr.or.yi.gradle_mybatis_c3p0.dto.Employee;
import kr.or.yi.gradle_mybatis_c3p0.dto.State;
import kr.or.yi.gradle_mybatis_c3p0.jdbc.MybatisSqlSessionFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final String namespace = "kr.or.yi.gradle_mybatis_c3p0.dao.EmployeeDao"; //맵퍼의 namespace와 같아야함!!!!
	@Override
	public List<Employee> selectEmployeeByAll() { //select는 comit()안해줘도됨
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			return sqlSession.selectList(namespace+".selectEmployeeByAll");
		}
	}
	@Override
	public int updateEmployee(Employee employee) {
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			int res =  sqlSession.update(namespace+".updateEmployee", employee);
			sqlSession.commit();
			return res;
		}	
	}
	@Override
	public int deleteEmployee(Employee employee) {
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			int res =  sqlSession.delete(namespace+".deleteEmployee", employee);
			sqlSession.commit();
			return res;
		}	
	}
	@Override
	public int insertEmployee(Employee employee) {
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			int res =  sqlSession.insert(namespace+".insertEmployee", employee);
			sqlSession.commit();
			return res;
		}	
	}
	@Override
	public Employee selectEmployeeByCode(Employee employee) { //방법1
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			return sqlSession.selectOne(namespace+".selectEmployeeByCode", employee);
		}
	}
	@Override
	public Map<String, Object> getSalaryByDepartment(Map<String, Object> param) {
		Map<String,Object> map = new HashMap<String, Object>();
		ResultHandler<State> resultHandler = new ResultHandler<State>() {

			@Override
			public void handleResult(ResultContext<? extends State> resultContext) {
				State state = resultContext.getResultObject();
				map.put(state.getDeptName(), state.getTotal());
			}
			
		};
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			sqlSession.select(namespace+".getSalaryByDepartment", param, resultHandler);
		}
		return map;
	}
	@Override
	public State getStateSalaryByDepartment(Map<String, Object> param) { //방법2
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			return sqlSession.selectOne(namespace+".getStateSalaryByDepartment", param);
		}
	}

}
