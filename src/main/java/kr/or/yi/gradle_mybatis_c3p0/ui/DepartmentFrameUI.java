package kr.or.yi.gradle_mybatis_c3p0.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.or.yi.gradle_mybatis_c3p0.dao.DepartmentDao;
import kr.or.yi.gradle_mybatis_c3p0.dao.DepartmentDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dao.TitleDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dto.Department;
import kr.or.yi.gradle_mybatis_c3p0.dto.Title;
import kr.or.yi.gradle_mybatis_c3p0.ui.content.PanelDepartment;
import kr.or.yi.gradle_mybatis_c3p0.ui.list.DepartmentList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class DepartmentFrameUI extends JFrame implements ActionListener {
	private JButton btnAdd;
	private PanelDepartment pContent;
	private List<Department> deptList;
	private DepartmentList pList;
	private JButton btnCancel;
	private DepartmentDao dao;
	private JPopupMenu popupMenu;
	private JMenuItem mntmUpdate;
	private JMenuItem mntmDelete;

	public DepartmentFrameUI() {
		dao = new DepartmentDaoImpl();
		deptList=dao.selectDepartmentByAll();
		initComponents();
	}

	private void initComponents() {
		setTitle("직책관리");
		setBounds(200, 100, 450, 450);
		JPanel pMain = new JPanel();
		getContentPane().add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new BorderLayout(0, 0));

		pContent = new PanelDepartment("부서");
		
		clearContent();
		pMain.add(pContent, BorderLayout.CENTER);

		JPanel pBtns = new JPanel();
		pMain.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);

		pList = new DepartmentList("부서");
		
		reloadList();
		
		getContentPane().add(pList, BorderLayout.SOUTH);
		
		popupMenu = new JPopupMenu();
		addPopup(pList, popupMenu);
		
		mntmUpdate = new JMenuItem("수정");
		mntmUpdate.addActionListener(this);
		popupMenu.add(mntmUpdate);
		
		mntmDelete = new JMenuItem("삭제");
		mntmDelete.addActionListener(this);
		popupMenu.add(mntmDelete);
		
		
		pList.setPopupMenu(popupMenu);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmDelete) {
			actionPerformedMntmDelete(e);
		}
		if (e.getSource() == mntmUpdate) {
			actionPerformedMntmUpdate(e);
		}

		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals("수정")) {
				actionPerformedBtnUpdate(e);
			}
			if(e.getActionCommand().equals("추가")) {
				actionPerformedBtnAdd(e);
			}
			
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "수정 버튼 클릭");
		Department updateDept = pContent.getItem();
		int res = dao.updateDepartment(updateDept);
		refreshUI(updateDept, res);
		btnAdd.setText("추가");
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department insertDept= pContent.getItem();
		int res = dao.insertDepartment(insertDept);
		refreshUI(insertDept, res);
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		clearContent();
		btnAdd.setText("추가");
	}
	
	
	private void reloadList() { 
		deptList = dao.selectDepartmentByAll();
		pList.setItemList(deptList);
		pList.reloadData();
	}
	
	private void clearContent() {
		
		pContent.clearComponent(deptList.size() == 0 ? 1 : deptList.size() + 1);
	}
	
	private void refreshUI(Department item, int res) {
		String message = res == 1 ? "성공" : "실패";
		JOptionPane.showMessageDialog(null, item + message);
		reloadList();
		clearContent();
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});    
	}
	protected void actionPerformedMntmUpdate(ActionEvent e) { //수정팝업 클릭
		Department updateDept = pList.getSelectedItem();
		pContent.setItem(updateDept);
		btnAdd.setText("수정");
	}
	protected void actionPerformedMntmDelete(ActionEvent e) { //삭제팝업 클릭
		Department delDept = pList.getSelectedItem();
		int res = dao.deleteDepartment(delDept);
		refreshUI(delDept, res);
	}
}
