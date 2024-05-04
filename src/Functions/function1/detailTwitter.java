package Functions.function1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class detailTwitter {
	private ObservableList<twitterNews> listTwitter = FXCollections.observableArrayList();
	
	public ObservableList<twitterNews>getListTwitter(){
		return listTwitter;
	}
	
	public ObservableList<twitterNews> loadData(String jsonString) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return FXCollections.observableArrayList();
        }

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                if (jsonObject.has("time")) {
                    String originalTime = jsonObject.get("time").getAsString();
                    String formattedTime = convertDateFormat(originalTime);
                    jsonObject.addProperty("time", formattedTime);
                }
                twitterNews obj = gson.fromJson(jsonObject, twitterNews.class);
                listTwitter.add(obj);
            }
        }
        return listTwitter;
	}
	
	private String convertDateFormat(String originalTime) {
		if (originalTime == null || originalTime.isEmpty()) {
	        // Handle the case where the date string is null or empty
	        System.out.println("Invalid date string: " + originalTime);
	        return originalTime;
	    }
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(originalTime);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parsing date: " + originalTime);
            return originalTime; // return original time if parsing fails
        }
    }
	
	public void displayJsonArray(ObservableList<twitterNews> list) {
        // Your logic to display the content of the ObservableList
        for (twitterNews obj : listTwitter) {
            System.out.println(obj.getAuthor() +"\n"+ obj.getTime()+"\n" +obj.getContent()+"\n" + obj.getHashtags()); 
        }
    }
	
	public void filterByHashtag(ObservableList<twitterNews> listTwitter, String targetHashtag) {
        if (listTwitter == null) {
            System.out.println("Invalid Twitter list.");
            return;
        }

        ObservableList<twitterNews> filteredList = FXCollections.observableArrayList();
        
        boolean found = false;
        for (twitterNews twitterObject : listTwitter) {
            // Assuming TwitterObject has a method getHashtags() to retrieve hashtags
            List<String> hashtags = twitterObject.getHashtags();

            if (hashtags.contains(targetHashtag)) {
                filteredList.add(twitterObject);
                found = true; // If you want to keep track of at least one match
            }
        }

        if (!found) {
            System.out.println("No tweets found with the hashtag: " + targetHashtag);
        }
    }
	
	 public String findMostPopularHashtag(ObservableList<twitterNews> posts) {
	        // Create a map to store the count of each hashtag
	        Map<String, Integer> hashtagCountMap = new HashMap<>();

	        // Iterate through each post and count the hashtags
	        for (twitterNews post : posts) {
	        	List<String> hashtags = post.getHashtags();
	            if (hashtags != null) {
	                for (int i = 0; i < hashtags.size(); i++) {
	                    String hashtag = hashtags.get(i);
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
	 public ObservableList<twitterNews> getPostsForDay(ObservableList<twitterNews> listTwitter, String targetDay) {
	        ObservableList<twitterNews> postsForDay = FXCollections.observableArrayList();

	        for (twitterNews news : listTwitter) {
	            // Assuming getDay() returns the day in the format you are comparing with (e.g., "yyyy-MM-dd").
	            if (news.getTime().equals(targetDay)) {
	                postsForDay.add(news);
	            }
	        }

	        return postsForDay;
	    }

	 public ObservableList<twitterNews> getPostsForWeek(ObservableList<twitterNews> listTwitter, String targetWeek) {
		 ObservableList<twitterNews> postsForWeek = FXCollections.observableArrayList();

	        // Assuming getDay() returns the day in the format you are comparing with (e.g., "yyyy-MM-dd").
	        for (twitterNews news : listTwitter) {
	            if (isInTargetWeek(news.getTime(), targetWeek)) {
	                postsForWeek.add(news);
	            }
	        }

	        return postsForWeek;
	    }
	 
	 private boolean isInTargetWeek(String postTime, String targetWeek) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Date postDate = sdf.parse(postTime);
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(postDate);
	            int postWeek = cal.get(Calendar.WEEK_OF_YEAR);

	            Date targetDate = sdf.parse(targetWeek);
	            cal.setTime(targetDate);
	            int targetWeekNumber = cal.get(Calendar.WEEK_OF_YEAR);

	            return postWeek == targetWeekNumber;
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public ObservableList<twitterNews> getPostsForMonth(ObservableList<twitterNews> listTwitter, String targetMonth) {
	        ObservableList<twitterNews> postsForMonth = FXCollections.observableArrayList();

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

	        for (twitterNews news : listTwitter) {
	            String newsTime = news.getTime();
	            LocalDate postDate = LocalDate.parse(newsTime, formatter);
	            // Parsing the time String to extract the month
	    
	            LocalDate targetMonthStart = LocalDate.parse(targetMonth, formatter);
	            
	            LocalDate targetMonthEnd= targetMonthStart.plusDays(30);
	            // Comparing the month with the target month
	            if (!postDate.isBefore(targetMonthStart) && !postDate.isAfter(targetMonthEnd)) {
	            	postsForMonth.add(news);
	            }
	        }

	        return postsForMonth;
	    }
}
