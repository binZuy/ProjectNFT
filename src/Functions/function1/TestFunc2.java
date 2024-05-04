package Functions.function1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

	/*public static void main(String[] args) {
	    HashtagJsonProcessor hashtagJsonProcessor = new HashtagJsonProcessor();
	    String twitterString = null;
		try {
			twitterString = JsonProcessor.readJsonFile("src/data/outputData/TwitterData/twitter.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    KeywordJsonProcessor keywordJsonProcessor = new KeywordJsonProcessor();
	    String modifiedJsonString = keywordJsonProcessor.processJsonFile("src/data/outputData/blogData/nftically.json");
	    String modifiedJsonString2 = keywordJsonProcessor.processJsonFile("src/data/outputData/blogData/airnft.json");
	    String modifiedJsonString3 = keywordJsonProcessor.processJsonFile("src/data/outputData/blogData/nftnewstoday.json");
	    String modifiedJsonString4 = keywordJsonProcessor.processJsonFile("src/data/outputData/blogData/nonFungible.json");
	    
	    if (twitterString != null) {
	        String targetDay = "2023-12-19"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForDay = hashtagJsonProcessor.getPostsForDay(twitterString, targetDay);

	        if (!postsForDay.isEmpty()) {
	            System.out.println("Posts for day " + targetDay + ":");
	            for (JsonObject post : postsForDay) {
	                hashtagJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularHashtag = hashtagJsonProcessor.findMostPopularHashtag(postsForDay);

	            // Print the result
	            System.out.println("Most Popular Hashtag: " + mostPopularHashtag);
	        } else {
	            System.out.println("No posts found for day " + targetDay);
	        }
	    } else {
	        System.out.println("Error processing JSON file.");
	    }
	    
	    if (twitterString != null) {
	        String targetWeek = "2023-12-19"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForWeek = hashtagJsonProcessor.getPostsForWeek(twitterString, targetWeek);

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
	    
	    if (modifiedJsonString != null) {
	        String targetDay = "2021-07-06"; // Replace with the desired day in yyyy-MM-dd format
	        List<JsonObject> postsForDay = keywordJsonProcessor.getPostsForDay(modifiedJsonString, targetDay);
	        System.out.println(modifiedJsonString);

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
	    
	    if (modifiedJsonString != null) {
	        String targetWeek = "2021-07-06"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForWeek = keywordJsonProcessor.getPostsForDay(modifiedJsonString, targetWeek);
	        System.out.println(modifiedJsonString);

	        if (!postsForWeek.isEmpty()) {
	            System.out.println("Posts for week starting on " + targetWeek + ":");
	            for (JsonObject post : postsForWeek) {
	                keywordJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularKeyword = keywordJsonProcessor.findMostPopularKeyword(postsForWeek);

	            // Print the result
	            System.out.println("Most Popular Keyword: " + mostPopularKeyword);
	        } else {
	            System.out.println("No posts found for week " + targetWeek);
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
	    
	    if (modifiedJsonString2 != null) {
	        String targetWeek2 = "2021-07-06"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForWeek2 = keywordJsonProcessor.getPostsForDay(modifiedJsonString2, targetWeek2);
	        System.out.println(modifiedJsonString2);

	        if (!postsForWeek2.isEmpty()) {
	            System.out.println("Posts for week starting on " + targetWeek2 + ":");
	            for (JsonObject post : postsForWeek2) {
	                keywordJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularKeyword2 = keywordJsonProcessor.findMostPopularKeyword(postsForWeek2);

	            // Print the result
	            System.out.println("Most Popular Keyword: " + mostPopularKeyword2);
	        } else {
	            System.out.println("No posts found for week " + targetWeek2);
	        }
	    } else {
	        System.out.println("Error processing JSON file.");
	    }

	    if (modifiedJsonString3 != null) {
	        String targetDay = "2021-07-06"; // Replace with the desired day in yyyy-MM-dd format
	        List<JsonObject> postsForDay = keywordJsonProcessor.getPostsForDay(modifiedJsonString3, targetDay);
	        System.out.println(modifiedJsonString3);

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
	    
	    if (modifiedJsonString3 != null) {
	        String targetWeek3 = "2021-07-06"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForWeek3 = keywordJsonProcessor.getPostsForDay(modifiedJsonString3, targetWeek3);
	        System.out.println(modifiedJsonString3);

	        if (!postsForWeek3.isEmpty()) {
	            System.out.println("Posts for week starting on " + targetWeek3 + ":");
	            for (JsonObject post : postsForWeek3) {
	                keywordJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularKeyword3 = keywordJsonProcessor.findMostPopularKeyword(postsForWeek3);

	            // Print the result
	            System.out.println("Most Popular Keyword: " + mostPopularKeyword3);
	        } else {
	            System.out.println("No posts found for week " + targetWeek3);
	        }
	    } else {
	        System.out.println("Error processing JSON file.");
	    }
	    
	    if (modifiedJsonString4 != null) {
	        String targetDay = "2021-07-06"; // Replace with the desired day in yyyy-MM-dd format
	        List<JsonObject> postsForDay = keywordJsonProcessor.getPostsForDay(modifiedJsonString4, targetDay);
	        System.out.println(modifiedJsonString4);

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
	    
	    if (modifiedJsonString4 != null) {
	        String targetWeek4 = "2021-07-06"; // Replace with the desired week in yyyy-MM-dd format
	        List<JsonObject> postsForWeek4 = keywordJsonProcessor.getPostsForDay(modifiedJsonString4, targetWeek4);
	        System.out.println(modifiedJsonString4);

	        if (!postsForWeek4.isEmpty()) {
	            System.out.println("Posts for week starting on " + targetWeek4 + ":");
	            for (JsonObject post : postsForWeek4) {
	                keywordJsonProcessor.displayUniqueContent(post);
	            }
	            String mostPopularKeyword4 = keywordJsonProcessor.findMostPopularKeyword(postsForWeek4);

	            // Print the result
	            System.out.println("Most Popular Keyword: " + mostPopularKeyword4);
	        } else {
	            System.out.println("No posts found for week " + targetWeek4);
	        }
	    } else {
	        System.out.println("Error processing JSON file.");
	    }
<<<<<<< HEAD
	}*/
}

