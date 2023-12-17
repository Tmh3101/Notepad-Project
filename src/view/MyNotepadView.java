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
	private JLabel label_fileName;
	private JTextArea contentArea;
	private JPanel footer;

	/**
	 * Create the frame.
	 */
	public MyNotepadView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Code\\Java\\JavaEclipse\\MyNotepadProject\\src\\view\\icon_notepad.png"));
		setTitle("My Notepad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		ActionListener action = new MyNotepadListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menu_file = new JMenu("File");
		JMenuItem menuItem_open = new JMenuItem("Open", KeyEvent.VK_O);
		menuItem_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		menuItem_open.addActionListener(action);
		JMenuItem menuItem_save = new JMenuItem("Save", KeyEvent.VK_S);
		menuItem_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		menuItem_save.addActionListener(action);
		JMenuItem menuItem_exit = new JMenuItem("Exit");
		menuItem_exit.addActionListener(action);
		
		menu_file.add(menuItem_open);
		menu_file.add(menuItem_save);
		menu_file.addSeparator();
		menu_file.add(menuItem_exit);
		menuBar.add(menu_file);
		
		JMenu menu_edit = new JMenu("Edit");
		JRadioButtonMenuItem radioButton_bold = new JRadioButtonMenuItem("Bold");
		radioButton_bold.addActionListener(action);
		JRadioButtonMenuItem radioButton_italic = new JRadioButtonMenuItem("Italic");
		radioButton_italic.addActionListener(action);
		JRadioButtonMenuItem radioButton_plain = new JRadioButtonMenuItem("Plain");
		radioButton_plain.addActionListener(action);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton_bold);
		bg.add(radioButton_italic);
		bg.add(radioButton_plain);
	
		menu_edit.add(radioButton_bold);
		menu_edit.add(radioButton_italic);
		menu_edit.add(radioButton_plain);
		menuBar.add(menu_edit);
		
		
		JMenu menu_view = new JMenu("View");
		JCheckBoxMenuItem checkBox_darkMode = new JCheckBoxMenuItem("Dark Mode");
		checkBox_darkMode.addActionListener(action);
		menu_view.add(checkBox_darkMode);
		menuBar.add(menu_view);
		
		contentArea = new JTextArea();
		contentArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(contentArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		footer = new JPanel();
		footer.setBackground(new Color(255, 255, 255));
		footer.setPreferredSize(new Dimension(10, 40));
		contentPane.add(footer, BorderLayout.SOUTH);
		footer.setLayout(null);

		label_fileName = new JLabel("File Name");
		label_fileName.setFont(new Font("Tahoma", Font.ITALIC, 16));
		label_fileName.setBounds(24, 10, 409, 25);
		footer.add(label_fileName);

		JButton button_open = new JButton("Open");
		button_open.addActionListener(action);
		button_open.addActionListener(action);
		button_open.setIcon(new ImageIcon("E:\\Code\\Java\\JavaEclipse\\MyNotepadProject\\src\\view\\icon_open.png"));
		button_open.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_open.addActionListener(action);
		button_open.setBounds(597, 6, 110, 30);
		footer.add(button_open);

		JButton button_save = new JButton("Save");
		button_save.addActionListener(action);
		button_save.setIcon(new ImageIcon("E:\\Code\\Java\\JavaEclipse\\MyNotepadProject\\src\\view\\save_icon.png"));
		button_save.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_save.addActionListener(action);
		button_save.setBounds(717, 6, 110, 30);
		footer.add(button_save);

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
		label_fileName.setText(myNotepadModel.getFileName());
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
	
	public void onDarkMode() {
		contentArea.setBackground(new Color(55, 55, 55));
		contentArea.setForeground(Color.WHITE);
		footer.setBackground(new Color(55, 55, 55));
		label_fileName.setForeground(Color.WHITE);
	}
	
	public void offDarkMode() {
		contentArea.setBackground(Color.WHITE);
		contentArea.setForeground(Color.BLACK);
		footer.setBackground(Color.WHITE);
		label_fileName.setForeground(Color.BLACK);
	}
	
	public void changeStyle(String style) {
		myNotepadModel.setStyle(style);
		if(myNotepadModel.getStyle().equals("Bold")) contentArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		else if(myNotepadModel.getStyle().equals("Italic")) contentArea.setFont(new Font("Monospaced", Font.ITALIC, 14));
		else contentArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
	}

}
