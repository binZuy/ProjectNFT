package Functions.function1;



import java.io.IOException;

import com.google.gson.JsonArray;



public class Test {

    public static void main(String[] args) {
        // Example usage
    	String twitterFile = "src/data/outputData/TwitterData/twitter.json";
    	String blogFile = "src/data/outputData/blogData/nftically.json";
    	String blog2File = "src/data/outputData/blogData/nonFungible.json";
    	String blog3File = "src/data/outputData/blogData/nftnewstoday.json";
    	String blog4File = "src/data/outputData/blogData/airnft.json";
    	
    	String twitterJsonString = null;
    	String blogJsonString = null;
    	String blogHashtagJsonString = null;
    	String blog2JsonString = null;
    	String blog3JsonString = null;

    	
    	
		try {
			twitterJsonString = JsonProcessor.readJsonFile(twitterFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		try {
			blogJsonString = JsonProcessor.readJsonFile(blogFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blog2JsonString = JsonProcessor.readJsonFile(blog3File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blog3JsonString = JsonProcessor.readJsonFile(blog4File);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			blogHashtagJsonString = JsonProcessor.readJsonFile(blog2File);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		JsonArray hashtagArray = new JsonArray();
        HashtagJsonProcessor twitterProcessor = new HashtagJsonProcessor(hashtagArray);
        KeywordJsonProcessor blogProcessor = new KeywordJsonProcessor();

        // Filter Twitter posts by hashtag
        twitterProcessor.displayUniqueHashtags(twitterJsonString);
        blogProcessor.displayUniqueKeywords(blogHashtagJsonString);

        twitterProcessor.filterByHashtag(twitterJsonString, "#NFT");
        blogProcessor.filterByKeyword(blogHashtagJsonString, "#Virtual Races");

        // Filter Blog posts by keyword
        blogProcessor.displayUniqueKeywords(blogJsonString);

        //Filter Blog posts by hashtag

        blogProcessor.displayUniqueKeywords(blog2JsonString);
        blogProcessor.displayUniqueKeywords(blog3JsonString);
        
        
        blogProcessor.filterByKeyword(blogJsonString, "");
        blogProcessor.filterByKeyword(blog2JsonString, "");
        blogProcessor.filterByKeyword(blog3JsonString, "");


    }
}
