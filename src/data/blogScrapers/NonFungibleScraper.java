package Data.BlogScrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Data.Model.BlogDataModel;
import util.path.JsonURL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NonFungibleScraper implements BlogScraper {
	@Override
    public void scrape() {
        // URL of the OpenSea blog
        String url = "https://nonfungible.com/news";

        try {
            // Fetch the HTML content from the URL
            Document document = Jsoup.connect(url).get();

            // Select all <a> tags with the specified class
            Elements links = document.select("a.MuiTypography-root.MuiTypography-inherit.MuiLink-root.MuiLink-underlineHover.css-3pyhqk");

            // Store URLs in a Set to avoid duplicates
            Set<String> urlSet = new HashSet<>();

            // Add absolute URLs containing "/news" to the set
            for (Element link : links) {
                String absoluteUrl = link.absUrl("href");
                if (absoluteUrl.contains("/news")) {
                    urlSet.add(absoluteUrl);
                }
            }

            // Create a list to store the extracted data
            List<BlogDataModel> BlogDataModelList = new ArrayList<>();

            // Extract data from each URL
            for (String uniqueUrl : urlSet) {
                try {
                    // Fetch the HTML content from the URL
                    Document urlDocument = Jsoup.connect(uniqueUrl).get();

                    // Extract title, time, content, and hashtags
                    String title = urlDocument.select("h1.MuiTypography-root.MuiTypography-h1.css-3ker6m").text();
                    String time = formatDate(urlDocument.select("span.MuiBox-root.css-k008qs").text());
                    String content = extractContent(urlDocument);

                    // Extract hashtags from <div class="MuiPaper-root MuiPaper-elevation MuiPaper-elevation5 css-1cin1l4">
                    Set<String> hashtags = extractHashtags(urlDocument);

                    // Create an BlogDataModel object and add it to the list
                    BlogDataModel BlogDataModel = new BlogDataModel(uniqueUrl, title, time, content, hashtags);
                    BlogDataModelList.add(BlogDataModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Convert the extracted data list to JSON using Gson
            String jsonString = BlogDataModelListToJson(BlogDataModelList);

            // Write JSON data to a file named "nonFungible.json"
            writeJsonToFile(jsonString, JsonURL.NONFUNGIBLE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract content from <p> and <h2> elements
	@Override
    public String extractContent(Document document) {
        Elements contentElements = document.select("p.MuiTypography-root.MuiTypography-body1.css-1r4bxmx, h2.MuiTypography-root.MuiTypography-h2.css-1yqyu40");
        StringBuilder contentBuilder = new StringBuilder();

        for (Element element : contentElements) {
            contentBuilder.append(element.text()).append("\n");
        }

        return contentBuilder.toString();
    }

    // Helper method to extract hashtags from <div class="MuiPaper-root MuiPaper-elevation MuiPaper-elevation5 css-1cin1l4">
    private static Set<String> extractHashtags(Document document) {
        Elements hashtagElements = document.select("div.MuiPaper-root.MuiPaper-elevation.MuiPaper-elevation5.css-1cin1l4");
        Set<String> hashtags = new HashSet<>();

        for (Element element : hashtagElements) {
            hashtags.add(element.text());
        }

        return hashtags;
    }

    // Helper method to convert extracted data list to JSON using Gson
    private static String BlogDataModelListToJson(List<BlogDataModel> BlogDataModelList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(BlogDataModelList);
    }

    // Helper method to write JSON data to a file
    @Override
    public void writeJsonToFile(String json, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to format the date
    private static String formatDate(String rawTime) {
        // Extract the date part from the raw time string
        String datePart = rawTime.split("\\s+")[1];

        // Parse and format the date
        try {
            java.util.Date date = new java.text.SimpleDateFormat("MM/dd/yy").parse(datePart);
            return new java.text.SimpleDateFormat("MMMM dd, yyyy").format(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return rawTime;
        }
    }

	@Override
	public BlogDataModel extractDataFromUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> extractKeywords(Document document, String selector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String extractTime(Element timeElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String blogDataModelListToJson(List<BlogDataModel> blogDataModelList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String BlogDataModelListToJson(Set<BlogDataModel> BlogDataModelList) {
		// TODO Auto-generated method stub
		return null;
	}

}
