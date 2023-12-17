package src.model;

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
	
}
