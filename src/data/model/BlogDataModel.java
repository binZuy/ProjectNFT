package Data.Model;

import java.util.Set;

//Data class to represent the extracted data
public class BlogDataModel {
    private final String url;
    private final String title;
    private final String time;
    private final String content;
    private final Set<String> keywords;

    public BlogDataModel(String url, String title, String time, String content, Set<String> keywords) {
        this.url = url;
        this.title = title;
        this.time = time;
        this.content = content;
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public Set<String> getKeywords() {
        return keywords;
    }
}
