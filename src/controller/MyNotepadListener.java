package src.controller;

import src.view.MyNotepadView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
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
					String text = new String();
					for (String line: allLine) {
						text += line;
						text += "\n";
					}
					myNotepadView.setTextArea(text);	
				} catch (IOException e1) {
					e1.printStackTrace();
				}	
			}	
			
		} else if(e.getActionCommand().equals("Save")) {
			JFileChooser fc = new JFileChooser();
			if(myNotepadView.getMyNotepadModel().getFileName().length() > 0) {
				myNotepadView.setContent();
				myNotepadView.getMyNotepadModel().save(myNotepadView.getMyNotepadModel().getFileName());
				
			} else {
				if(myNotepadView.showDialog(fc, e.getActionCommand()) == JFileChooser.APPROVE_OPTION) {
					myNotepadView.setFileName(fc.getSelectedFile().getAbsolutePath());
					myNotepadView.setContent();
					myNotepadView.getMyNotepadModel().save(myNotepadView.getMyNotepadModel().getFileName());
				}
			}
			
		} else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
			
		} else if(e.getActionCommand().equals("Bold") || e.getActionCommand().equals("Italic") || e.getActionCommand().equals("Plain")) {
			myNotepadView.changeStyle(e.getActionCommand());
		} else if(e.getActionCommand().equals("Dark Mode")) {
			myNotepadView.changeView();
		}
	}
	
}
