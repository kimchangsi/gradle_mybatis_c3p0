package kr.or.yi.gradle_mybatis_c3p0.ui;

import java.util.List;

import javax.swing.JFrame;

import kr.or.yi.gradle_mybatis_c3p0.dto.Employee;
import kr.or.yi.gradle_mybatis_c3p0.service.EmployeeUIService;
import kr.or.yi.gradle_mybatis_c3p0.ui.content.AbstractPanel;
import kr.or.yi.gradle_mybatis_c3p0.ui.content.PanelEmployee;
import kr.or.yi.gradle_mybatis_c3p0.ui.list.AbstractList;
import kr.or.yi.gradle_mybatis_c3p0.ui.list.EmployeeList;

@SuppressWarnings("serial")
public class EmployeeFrameUI extends AbstractFrameUI<Employee> {
	private EmployeeUIService service;

	public EmployeeFrameUI(String title) {
		super(title);
		setBounds(100, 100, 500, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	protected void initDao() {
		service = new EmployeeUIService();
	}

	@Override
	protected AbstractList<Employee> createListPanel() {
		/*
		 * empList.setItemList(service.selectEmpAll()); empList.reloadData();
		 */
		return new EmployeeList();
	}

	@Override
	protected AbstractPanel<Employee> createContentPanel() {
		PanelEmployee empPanel = new PanelEmployee();
		empPanel.setDeptList(service.selectDeptAll());
		empPanel.setTitleList(service.selectTitleAll());
		return empPanel;
	}

	@Override
	protected void clearContent() {
		pContent.clearComponent(itemList.size() == 0 ? 1 : itemList.size() + 1);
	}

	@Override
	protected List<Employee> getListAll() {
		
		return service.selectEmpAll();
	}

	@Override
	protected int updateItem(Employee item) {
		// TODO Auto-generated method stub
		return service.updateEmployeeItem(item);
	}

	@Override
	protected int insertItem(Employee item) {
		// TODO Auto-generated method stub
		return service.insertEmployeeItem(item);
	}

	@Override
	protected int deleteItem(Employee item) {
		// TODO Auto-generated method stub
		return service.deleteEmployeeItem(item);
	}

}
