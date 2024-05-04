package Functions.function1;

import java.util.List;

public class blogNews{
	private String time;
	private String content;
	private String url;
	private String title;
	private List<String> keywords;
	
	public blogNews(String url, String title, String time, String content, List<String> keywords) {
		this.url =url;
		this.title = title;
		this.time = time;
		this.content = content;
		this.keywords = keywords;
	}

	public String getTime() {
		return time;
	}

	public String getContent() {
		return content;
	}

	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	
	
}
