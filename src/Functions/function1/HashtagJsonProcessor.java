package Functions.function1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HashtagJsonProcessor extends JsonProcessor {
	private JsonArray hashtagArray;
	
	public HashtagJsonProcessor(JsonArray initialHashtagArray) {
        this.hashtagArray = initialHashtagArray;
    }
	
    @Override
    boolean containsTag(JsonArray hashtagArray, String targetHashtag) {
        // Implementation specific to Twitter posts
    	if (hashtagArray == null) {
    		return false;
    	}
    	Iterator<JsonElement> iterator = hashtagArray.iterator();
        while (iterator.hasNext()) {
            // Your existing code to process the array elements
        	return true;
        }
       /* for (JsonElement hashtagElement : hashtagArray) {
            if (hashtagElement.getAsString().equalsIgnoreCase(targetHashtag)) {
                return true;
            }
        }*/
        return false;
    }

    @Override
    boolean containsKeyword(JsonArray keywordsArray, String targetKeyword) {
        // Twitter posts do not have keywords, so the method is not implemented
        return false;
    }

    @Override
    void displayUniqueContent(JsonObject jsonObject) {
    	if (jsonObject.has("author")) {
    		System.out.println("Twitter Content: " + jsonObject.get("content").getAsString());
            System.out.println("Author: " + jsonObject.get("author").getAsString());
            System.out.println("Time: " + jsonObject.get("time").getAsString());
            System.out.println("Hashtags: " + jsonObject.getAsJsonArray("hashtags"));
            System.out.println("\n");
    	}else {
    		System.out.println("Blog Content: " + jsonObject.get("content").getAsString());
            System.out.println("Time: " + jsonObject.get("time").getAsString());
            if (jsonObject.has("hashtags")) {
                System.out.println("Hashtags: " + jsonObject.getAsJsonArray("hashtags"));
            } else if (jsonObject.has("url") && jsonObject.has("title")) {
                System.out.println("URL: " + jsonObject.get("url").getAsString());
                System.out.println("Title: " + jsonObject.get("title").getAsString());
            }
            System.out.println("\n");
    	}
        
    }

    void displayUniqueHashtags(String jsonString) {
        TreeSet<String> uniqueHashtags = new TreeSet<>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(jsonString);

        while (matcher.find()) {
            uniqueHashtags.add(matcher.group());
        }

        System.out.print("Sorted Unique Hashtags: \n");
        uniqueHashtags.forEach(hashtag -> System.out.print(hashtag + "\n"));
        System.out.println();
    }
    
    public String findMostPopularHashtag(List<JsonObject> posts) {
        // Create a map to store the count of each hashtag
        Map<String, Integer> hashtagCountMap = new HashMap<>();

        // Iterate through each post and count the hashtags
        for (JsonObject post : posts) {
            JsonArray hashtags = post.getAsJsonArray("hashtags");
            if (hashtags != null) {
                for (int i = 0; i < hashtags.size(); i++) {
                    String hashtag = hashtags.get(i).getAsString();
                    hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        // Find the most popular hashtag
        String mostPopularHashtag = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : hashtagCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostPopularHashtag = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostPopularHashtag;
    }
}
    
    
    
