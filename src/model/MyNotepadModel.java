package src.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MyNotepadModel {
	private String fileName, content, style;

	public MyNotepadModel() {
		fileName = new String();
		content = new String();
		style = new String();
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void save(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
			pw.print(content);
			pw.flush();
			pw.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
}
