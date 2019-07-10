package kr.or.yi.gradle_mybatis_c3p0.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.or.yi.gradle_mybatis_c3p0.dao.TitleDao;
import kr.or.yi.gradle_mybatis_c3p0.dao.TitleDaoImpl;
import kr.or.yi.gradle_mybatis_c3p0.dto.Title;
import kr.or.yi.gradle_mybatis_c3p0.ui.content.PanelTitle;
import kr.or.yi.gradle_mybatis_c3p0.ui.list.TitleList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class TitleFrameUI extends JFrame implements ActionListener {
	private TitleDao dao;
	private JButton btnAdd;
	private PanelTitle pContent;
	private List<Title> titleList;
	private TitleList pList;
	private JButton btnCancel;
	private JPopupMenu popupMenu;
	private JMenuItem mntmDelete;
	private JMenuItem mntmUpdate;

	public TitleFrameUI() {
		dao = new TitleDaoImpl();
		titleList = dao.selectTitleByAll();
		initComponents();
	}

	private void initComponents() {
		setTitle("직책관리");
		setBounds(200, 100, 450, 450);
		JPanel pMain = new JPanel();
		getContentPane().add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new BorderLayout(0, 0));

		pContent = new PanelTitle();
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

		pList = new TitleList("직책 목록");

		reloadList();

		getContentPane().add(pList, BorderLayout.SOUTH);
		
		popupMenu = new JPopupMenu();
		addPopup(pList, popupMenu); 
		/* pList.add(popupMenu, BorderLayout.NORTH); */
		
		
		mntmUpdate = new JMenuItem("수정");
		mntmUpdate.addActionListener(this);
		popupMenu.add(mntmUpdate);
		
		mntmDelete = new JMenuItem("삭제");
		mntmDelete.addActionListener(this);
		popupMenu.add(mntmDelete);
		
		pList.setPopupMenu(popupMenu);//팝업메뉴담
		
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
			if(e.getActionCommand().equals("추가")) {
				actionPerformedBtnAdd(e);
			}
			if(e.getActionCommand().equals("수정")) {
				actionPerformedBtnUpdate(e);
			}
			
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "수정 버튼 클릭");
		Title updateTitle = pContent.getItem();
		int res = dao.updateTitle(updateTitle);
		refreshUI(updateTitle, res);
		btnAdd.setText("추가");
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title insertTitle = pContent.getItem();
		int res = dao.insertTitle(insertTitle);
		refreshUI(insertTitle, res);
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		clearContent();
	}
	
	private void reloadList() {
		titleList = dao.selectTitleByAll();
		pList.setItemList(titleList);
		pList.reloadData();
	}

	private void clearContent() {
		pContent.clearComponent(titleList.size() == 0 ? 1 : titleList.size() + 1);
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
	 

	protected void actionPerformedMntmUpdate(ActionEvent e) { //수정
		Title updateTitle = pList.getSelectedItem();
		pContent.setItem(updateTitle);
		btnAdd.setText("수정");
	}

	protected void actionPerformedMntmDelete(ActionEvent e) { //삭제
		Title delTitle = pList.getSelectedItem();
		int res = dao.deleteTitle(delTitle);
		refreshUI(delTitle, res);
	}

	private void refreshUI(Title item, int res) {
		String message = res == 1 ? "성공" : "실패";
		JOptionPane.showMessageDialog(null, item + message);
		reloadList();
		clearContent();
	}
}
