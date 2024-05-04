package Functions.function1;

import java.util.List;

public class twitterNews{
	private String author;
	private String time;
	private String content;
	private List<String> hashtags;

	public twitterNews(String author, String time, String content, List<String> hashtags) {
		// TODO Auto-generated constructor stub
		this.author = author;
		this.time = time;
		this.content= content;
		this.hashtags = hashtags;
	}
		
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getHashtagsd() {
		return hashtags;
	}
	public void setHashtagkeyword(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	
		
}
	        
		/*void displayUniqueHashtags(String jsonString) {
	        TreeSet<String> uniqueHashtags = new TreeSet<>();
	        Pattern pattern = Pattern.compile("#\\w+");
	        Matcher matcher = pattern.matcher(jsonString);

	        while (matcher.find()) {
	            uniqueHashtags.add(matcher.group());
	        }

	        System.out.print("Sorted Unique Hashtags: \n");
	        uniqueHashtags.forEach(hashtag -> System.out.print(hashtag + "\n"));
	        System.out.println();
	    }*/
	    
	        
	        
	      
	   
	    