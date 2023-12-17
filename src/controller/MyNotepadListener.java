package src.controller;

import src.view.MyNotepadView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;

public class MyNotepadListener implements ActionListener{
	private MyNotepadView myNotepadView;
	
	public MyNotepadListener(MyNotepadView myNotepadView) {
		this.myNotepadView = myNotepadView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Open")) {
			JFileChooser fc = new JFileChooser();
			if(myNotepadView.showDialog(fc, e.getActionCommand()) == JFileChooser.APPROVE_OPTION) {
				myNotepadView.setFileName(fc.getSelectedFile().getAbsolutePath());
				try {
					List<String> allLine = Files.readAllLines(fc.getSelectedFile().toPath(), StandardCharsets.UTF_8);
					String allText = new String();
					for (String str: allLine) {
						allText += str;
						allText += "\n";
					}
					myNotepadView.setTextArea(allText);	
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}	
			
		} else if(e.getActionCommand().equals("Save")) {
			JFileChooser fc = new JFileChooser();
			if(myNotepadView.getMyNotepadModel().getFileName().length() > 0) {
				myNotepadView.setContent();
				save(myNotepadView.getMyNotepadModel().getFileName());
				
			} else {
				if(myNotepadView.showDialog(fc, e.getActionCommand()) == JFileChooser.APPROVE_OPTION) {
					myNotepadView.setFileName(fc.getSelectedFile().getAbsolutePath());
					myNotepadView.setContent();
					save(myNotepadView.getMyNotepadModel().getFileName());
				}
			}
			
		} else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
			
		} else if(e.getActionCommand().equals("Bold") || e.getActionCommand().equals("Italic") || e.getActionCommand().equals("Plain")) {
			myNotepadView.changeStyle(e.getActionCommand());
		} else if(e.getActionCommand().equals("Dark Mode")) {
			AbstractButton ab = (AbstractButton)e.getSource();
			if(ab.getModel().isSelected()) myNotepadView.onDarkMode();
			else myNotepadView.offDarkMode();
		}
	}
	
	public void save(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
			pw.print(myNotepadView.getMyNotepadModel().getContent());
			pw.flush();
			pw.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
