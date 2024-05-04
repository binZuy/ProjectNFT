package Functions.function1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class detailBlog {
		private ObservableList<blogNews> listBlog = FXCollections.observableArrayList();
		
		public ObservableList<blogNews>getListBlog(){
			return listBlog;
		}
		
		public ObservableList<blogNews> loadData(String jsonString) {
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
	                blogNews obj = gson.fromJson(jsonObject, blogNews.class);
	                listBlog.add(obj);
	            }
	        }
	        return listBlog;
		}
		
		private String convertDateFormat(String originalTime) {
	        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date date = inputFormat.parse(originalTime);
	            return outputFormat.format(date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return originalTime; // return original time if parsing fails
	        }
	    }
		
		public void displayJsonArray(ObservableList<blogNews> list) {
	        // Your logic to display the content of the ObservableList
	        for (blogNews obj : list) {
	            System.out.println(obj.getUrl()+"\n"+ obj.getTitle() +"\n"+ obj.getTime()+"\n" +obj.getContent()+"\n" + obj.getKeywords()); 
	        }
	    }
		
		public void filterByKeyword(ObservableList<blogNews> listBlog, String targetKeyword) {
	        if (listBlog == null) {
	            System.out.println("Invalid Blog list.");
	            return;
	        }

	        ObservableList<blogNews> filteredList = FXCollections.observableArrayList();
	        
	        boolean found = false;
	        for (blogNews blogObject : listBlog) {
	            // Assuming TwitterObject has a method getHashtags() to retrieve hashtags
	            List<String> hashtags = blogObject.getKeywords();

	            if (hashtags.contains(targetKeyword)) {
	                filteredList.add(blogObject);
	                found = true; // If you want to keep track of at least one match
	                System.out.println("Success");
	            }
	        }

	        if (!found) {
	            System.out.println("No tweets found with the hashtag: " + targetKeyword);
	        }
	    }
		
		public String findMostPopularKeyword(ObservableList<blogNews> posts) {
	        // Create a map to store the count of each hashtag
	        Map<String, Integer> keywordCountMap = new HashMap<>();

	        // Iterate through each post and count the hashtags
	        for (blogNews post : posts) {
	        	List<String> keywords = post.getKeywords();
	            if (keywords != null) {
	                for (int i = 0; i < keywords.size(); i++) {
	                    String keyword = keywords.get(i);
	                    keywordCountMap.put(keyword, keywordCountMap.getOrDefault(keyword, 0) + 1);
	                }
	            }
	        }

	        // Find the most popular hashtag
	        String mostPopularHashtag = null;
	        int maxCount = 0;

	        for (Map.Entry<String, Integer> entry : keywordCountMap.entrySet()) {
	            if (entry.getValue() > maxCount) {
	                mostPopularHashtag = entry.getKey();
	                maxCount = entry.getValue();
	            }
	        }

	        return mostPopularHashtag;
	    }
		
		 public ObservableList<blogNews> getPostsForDay(ObservableList<blogNews> listBlog, String targetDay) {
		        ObservableList<blogNews> postsForDay = FXCollections.observableArrayList();

		        for (blogNews news : listBlog) {
		            // Assuming getDay() returns the day in the format you are comparing with (e.g., "yyyy-MM-dd").
		            if (news.getTime().equals(targetDay)) {
		                postsForDay.add(news);
		            }
		        }

		        return postsForDay;
		    }

		 public ObservableList<blogNews> getPostsForWeek(ObservableList<blogNews> listBlog, String targetWeek) {
			 ObservableList<blogNews> postsForWeek = FXCollections.observableArrayList();

		        // Assuming getDay() returns the day in the format you are comparing with (e.g., "yyyy-MM-dd").
		        for (blogNews news : listBlog) {
		            if (isInTargetWeek(news.getTime(), targetWeek)) {
		                postsForWeek.add(news);
		            }
		        }

		        return postsForWeek;
		    }
		 
		 public boolean isInTargetWeek(String postTime, String targetWeek) {
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
		 
		 public ObservableList<blogNews> getPostsForMonth(ObservableList<blogNews> listBlog, String targetMonth) {
		        ObservableList<blogNews> postsForMonth = FXCollections.observableArrayList();

		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

		        for (blogNews news : listBlog) {
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
