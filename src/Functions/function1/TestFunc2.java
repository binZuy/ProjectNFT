package Functions.function1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestFunc2 {

	public static void main(String[] args) {
		JsonArray hashtagArray = new JsonArray();
	    HashtagJsonProcessor hashtagJsonProcessor = new HashtagJsonProcessor(hashtagArray);
	    String modifiedJsonString = hashtagJsonProcessor.processJsonFile("src/data/outputData/blogData/nonFungible.json");
	    KeywordJsonProcessor keywordJsonProcessor = new KeywordJsonProcessor();
	    String modifiedJsonString2 = keywordJsonProcessor.processJsonFile("src/data/outputData/blogData/nftically.json");

	    
	    if (modifiedJsonString != null) {
	        String targetWeek = "2022-4-15"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForWeek = hashtagJsonProcessor.getPostsForWeek(modifiedJsonString, targetWeek);

	        if (!postsForWeek.isEmpty()) {
	            System.out.println("Posts for week starting on " + targetWeek + ":");
	            for (JsonObject post : postsForWeek) {
	                hashtagJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularHashtag = hashtagJsonProcessor.findMostPopularHashtag(postsForWeek);

	            // Print the result
	            System.out.println("Most Popular Hashtag: " + mostPopularHashtag);
	        } else {
	            System.out.println("No posts found for week starting on " + targetWeek);
	        }
	    } else {
	        System.out.println("Error processing JSON file.");
	    }
	    
	    if (modifiedJsonString2 != null) {
	        String targetDay = "2021-07-06"; // Replace with the desired day in yyyy-MM-dd format
	        List<JsonObject> postsForDay = keywordJsonProcessor.getPostsForDay(modifiedJsonString2, targetDay);
	        System.out.println(modifiedJsonString2);

	        if (!postsForDay.isEmpty()) {
	            System.out.println("Posts for day starting on " + targetDay + ":");
	            for (JsonObject post : postsForDay) {
	                keywordJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularKeyword = keywordJsonProcessor.findMostPopularKeyword(postsForDay);

	            // Print the result
	            System.out.println("Most Popular Keyword: " + mostPopularKeyword);
	        } else {
	            System.out.println("No posts found for day " + targetDay);
	        }
	    } else {
	        System.out.println("Error processing JSON file.");
	    }
	}
}
