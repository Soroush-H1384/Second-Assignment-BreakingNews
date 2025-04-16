package AP;

public class News {
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String content;

    public String getSource(){
        return source;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
    public String getContent(){
        return content;
    }

    public void setSource(String source){
        this.source = source;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setContent(String content){
        this.content = content;
    }
}
