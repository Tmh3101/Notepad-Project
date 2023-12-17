package src.test;

import javax.swing.UIManager;

import src.view.MyNotepadView;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			new MyNotepadView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
