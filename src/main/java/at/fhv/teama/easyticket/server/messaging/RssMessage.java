package main.java.at.fhv.teama.easyticket.server.messaging;

public class RssMessage {

    private String title;
    private String url;
    private String description;
    private String pubDate;

    public RssMessage(String title, String url, String description, String pubDate) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
