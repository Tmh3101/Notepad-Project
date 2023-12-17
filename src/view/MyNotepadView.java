package src.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import src.controller.MyNotepadListener;
import src.model.MyNotepadModel;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButtonMenuItem;

public class MyNotepadView extends JFrame {

	private JPanel contentPane;
	private MyNotepadModel myNotepadModel = new MyNotepadModel();
	private JMenuBar mnBar;
	private JLabel lbl_fileName;
	private JTextArea contentArea;
	private JPanel footer;
	private JPanel pnl_btn;
	private JCheckBoxMenuItem chbx_darkMode;

	/**
	 * Create the frame.
	 */
	public MyNotepadView() {
		setIconImage(Toolkit.getDefaultToolkit().createImage(MyNotepadView.class.getResource("icon_notepad.png")));
		setTitle("My Notepad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 534);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));

		ActionListener action = new MyNotepadListener(this);
		
		mnBar = new JMenuBar();
		getContentPane().add(mnBar, BorderLayout.NORTH);
		
		JMenu mn_file = new JMenu("File");
		mn_file.setForeground(Color.ORANGE);
		JMenuItem mnItm_open = new JMenuItem("Open", KeyEvent.VK_O);
		mnItm_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mnItm_open.addActionListener(action);
		JMenuItem mnItm_save = new JMenuItem("Save", KeyEvent.VK_S);
		mnItm_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnItm_save.addActionListener(action);
		JMenuItem mnItm_exit = new JMenuItem("Exit");
		mnItm_exit.addActionListener(action);
		
		mn_file.add(mnItm_open);
		mn_file.add(mnItm_save);
		mn_file.addSeparator();
		mn_file.add(mnItm_exit);
		mnBar.add(mn_file);
		
		JMenu mn_edit = new JMenu("Edit");
		mn_edit.setForeground(Color.ORANGE);
		JRadioButtonMenuItem rdbtn_bold = new JRadioButtonMenuItem("Bold");
		rdbtn_bold.addActionListener(action);
		JRadioButtonMenuItem rdbtn_italic = new JRadioButtonMenuItem("Italic");
		rdbtn_italic.addActionListener(action);
		JRadioButtonMenuItem rdbtn_plain = new JRadioButtonMenuItem("Plain");
		rdbtn_plain.addActionListener(action);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtn_bold);
		bg.add(rdbtn_italic);
		bg.add(rdbtn_plain);
	
		mn_edit.add(rdbtn_bold);
		mn_edit.add(rdbtn_italic);
		mn_edit.add(rdbtn_plain);
		mnBar.add(mn_edit);
		
		
		JMenu mn_view = new JMenu("View");
		mn_view.setForeground(Color.ORANGE);
		chbx_darkMode = new JCheckBoxMenuItem("Dark Mode");
		chbx_darkMode.addActionListener(action);
		mn_view.add(chbx_darkMode);
		mnBar.add(mn_view);
		
		contentArea = new JTextArea();
		contentArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		JScrollPane scrlpn = new JScrollPane(contentArea);
		contentPane.add(scrlpn, BorderLayout.CENTER);

		footer = new JPanel(new BorderLayout());
		footer.setBackground(new Color(255, 255, 255));
		footer.setPreferredSize(new Dimension(10, 40));
		contentPane.add(footer, BorderLayout.SOUTH);

		lbl_fileName = new JLabel("File Name");
		lbl_fileName.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lbl_fileName.setBounds(24, 10, 409, 25);
		footer.add(lbl_fileName, BorderLayout.CENTER);

		pnl_btn = new JPanel(new GridLayout(1, 2, 5, 5));
		footer.add(pnl_btn, BorderLayout.EAST);

		JButton btn_open = new JButton("Open");
		btn_open.addActionListener(action);
		btn_open.addActionListener(action);
		btn_open.setIcon(new ImageIcon(MyNotepadView.class.getResource("icon_open.png")));
		btn_open.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_open.addActionListener(action);
		btn_open.setBounds(597, 6, 110, 30);
		pnl_btn.add(btn_open);

		JButton btn_save = new JButton("Save");
		btn_save.addActionListener(action);
		btn_save.setIcon(new ImageIcon(MyNotepadView.class.getResource("icon_save.png")));
		btn_save.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_save.addActionListener(action);
		btn_save.setBounds(717, 6, 110, 30);
		pnl_btn.add(btn_save);

		changeView();
		setVisible(true);
	}

	public int showDialog(JFileChooser fc, String kindOfShow) {
		if (kindOfShow.equals("Open"))
			return fc.showOpenDialog(this);
		else
			return fc.showSaveDialog(this);

	}

	public void setFileName(String fileName) {
		myNotepadModel.setFileName(fileName);
		lbl_fileName.setText(myNotepadModel.getFileName());
	}

	public void setTextArea(String text) {
		myNotepadModel.setContent(text);
		contentArea.setText(myNotepadModel.getContent());
	}

	public void setContent() {
		myNotepadModel.setContent(contentArea.getText());
	}

	public MyNotepadModel getMyNotepadModel() {
		return myNotepadModel;
	}
	
	public void changeView() {
		if(chbx_darkMode.isSelected()){

			mnBar.setBackground(new Color(55, 55, 55));
			contentPane.setBackground(new Color(55, 55, 55));
			contentArea.setBackground(new Color(55, 55, 55));
			contentArea.setForeground(Color.WHITE);
			pnl_btn.setBackground(new Color(55, 55, 55));
			footer.setBackground(new Color(55, 55, 55));
			lbl_fileName.setForeground(Color.WHITE);

		} else {
			mnBar.setBackground(Color.WHITE);
			contentPane.setBackground(Color.WHITE);
			contentArea.setBackground(Color.WHITE);
			contentArea.setForeground(Color.BLACK);
			pnl_btn.setBackground(Color.WHITE);
			footer.setBackground(Color.WHITE);
			lbl_fileName.setForeground(Color.BLACK);

		}
	}
	
	public void changeStyle(String style) {
		myNotepadModel.setStyle(style);
		if(myNotepadModel.getStyle().equals("Bold")) contentArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		else if(myNotepadModel.getStyle().equals("Italic")) contentArea.setFont(new Font("Monospaced", Font.ITALIC, 14));
		else contentArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	}

}
