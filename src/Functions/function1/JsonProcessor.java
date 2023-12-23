package Functions.function1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Locale;

abstract class JsonProcessor {
    abstract boolean containsTag(JsonArray hashtagArray, String targetHashtag);
    abstract boolean containsKeyword(JsonArray keywordsArray, String targetKeyword);
    abstract void displayUniqueContent(JsonObject jsonObject);

    public static String readJsonFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }
    

    public String processJsonFile(String filePath) {
        try {
            String jsonString = readJsonFile(filePath);
            return processJsonString(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String processJsonString(String jsonString) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return null;
        }

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                if (jsonObject.has("time")) {
                    String originalTime = jsonObject.get("time").getAsString();
                    String formattedTime = convertDateFormat(originalTime);
                    jsonObject.addProperty("time", formattedTime);
                }
            }
        }

        return gson.toJson(jsonArray);
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


    public void displayJsonArray(String jsonArrayString) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonArrayString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                displayUniqueContent(element.getAsJsonObject());
            }
        }
    }

    public void filterByHashtag(String jsonString, String targetHashtag) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        boolean found = false;

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray hashtagArray = jsonObject.getAsJsonArray("hashtags");

                if (containsTag(hashtagArray, targetHashtag)) {
                    displayUniqueContent(jsonObject);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No posts found for hashtag: " + targetHashtag);
        }
    }

    public void filterByKeyword(String jsonString, String targetKeyword) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return;
        }

        boolean found = false;

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                JsonArray keywordsArray = jsonObject.getAsJsonArray("keywords");

                if (containsKeyword(keywordsArray, targetKeyword)) {
                    displayUniqueContent(jsonObject);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("No posts found for keyword: " + targetKeyword);
        }
    }

    
    public void displayMostPopular(List<JsonObject> postList) {
        // Extract hashtags from the list and find the most popular
        List<String> hashtags = new ArrayList<>();

        for (JsonObject post : postList) {
            JsonArray hashtagsArray = post.getAsJsonArray("hashtags");
            if (hashtagsArray != null) {
                for (JsonElement hashtagElement : hashtagsArray) {
                    hashtags.add(hashtagElement.getAsString());
                }
            }
        }

        // Find the most popular hashtag
        Map<String, Integer> hashtagCountMap = new HashMap<>();

        for (String hashtag : hashtags) {
            hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0) + 1);
        }

        String mostPopularHashtag = Collections.max(hashtagCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        System.out.println("Most Popular Hashtag in the List: " + mostPopularHashtag);
    }
   
    
    
    public List<JsonObject> getPostsForDay(String processedJsonString, String targetDay) {
        List<JsonObject> postsForDay = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(processedJsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return postsForDay;
        }

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                if (jsonObject.has("time")) {
                    String postTime = jsonObject.get("time").getAsString();
                    if (postTime.equals(targetDay)) {
                        postsForDay.add(jsonObject);
                    }
                }
            }
        }

        return postsForDay;
    }
    
    public List<JsonObject> getPostsForWeek(String processedJsonString, String targetWeek) {
        List<JsonObject> postsForWeek = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson(processedJsonString, JsonArray.class);

        if (jsonArray == null) {
            System.out.println("Invalid JSON array format.");
            return postsForWeek;
        }

        for (JsonElement element : jsonArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                if (jsonObject.has("time")) {
                    String postTime = jsonObject.get("time").getAsString();
                    if (isInTargetWeek(postTime, targetWeek)) {
                        postsForWeek.add(jsonObject);
                    }
                }
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

    public List<JsonObject> getPostsByWeekTwitter(String filePath, String targetWeek) {
        List<JsonObject> postsOfWeek = new ArrayList<>();
        
        try {
            String jsonString = readJsonFile(filePath);
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(jsonString, JsonArray.class);

            if (jsonArray != null) {
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        String postDateString = jsonObject.get("time").getAsString();
                        
                        // Parse the post date string into a LocalDate
                        LocalDate postDate = LocalDate.parse(postDateString, DateTimeFormatter.ISO_DATE);
                        
                        // Parse the target week start date
                        LocalDate targetWeekStart = LocalDate.parse(targetWeek, DateTimeFormatter.ISO_DATE);
                        
                        // Calculate the end date of the week
                        LocalDate targetWeekEnd = targetWeekStart.plusDays(6);

                        // Check if the post date is within the target week
                        if (!postDate.isBefore(targetWeekStart) && !postDate.isAfter(targetWeekEnd)) {
                            postsOfWeek.add(jsonObject);
                        }
                    }
                }
            } else {
                System.out.println("Invalid JSON array format.");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException appropriately
        }

        return postsOfWeek;
    }
}


