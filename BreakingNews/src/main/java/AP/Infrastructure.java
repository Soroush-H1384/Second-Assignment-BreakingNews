package AP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;




public class Infrastructure {

    private final String URL;
    private final String APIKEY;
    private final String JSONRESULT;
    private ArrayList<News> newsList = new ArrayList<News>(); // TODO: Create the News class


    public Infrastructure(String APIKEY) {
        this.APIKEY = APIKEY;
        this.URL = "https://newsapi.org/v2/everything?q=tesla&from=" + LocalDate.now().minusDays(1) + "&sortBy=publishedAt&apiKey=";
        this.JSONRESULT = getInformation();
        parseInformation();

    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    private String getInformation() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + APIKEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("HTTP error code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("!!Exception : " + e.getMessage());
        }
        return null;
    }
    private void parseInformation() {
        // TODO: Get the first 20 news from the articles array of the json result
        //  and parse the information of each on of them to be mapped to News class
        //  finally add them to newsList in this class to display them in the output
        JSONObject jsonObject = new JSONObject(JSONRESULT);
        JSONArray jsonArray = jsonObject.getJSONArray("articles");
        for (int i = 0; i < 20; i++) {
            JSONObject article = jsonArray.getJSONObject(i);
            News news = new News();
            news.setTitle(article.getString("title"));
//            news.setAuthor(article.getString("author"));
            JSONObject source = article.getJSONObject("source");
            news.setSource(source.getString("name"));
//            news.setDescription(article.getString("description"));
            news.setContent(article.getString("content"));
            news.setUrl(article.getString("url"));
            newsList.add(news);
        }
    }

    public void displayNewsList() {
        // TODO: Display titles of the news you got from api
        //  and print them in a way that user can choose one
        //  to see the full information of the news
        for (int i = 0; i < getNewsList().size(); i++) {
            System.out.println((i + 1) +  "--" + getNewsList().get(i).getTitle());
            System.out.println(getNewsList().get(i).getDescription());
            System.out.println("----------------");
        }
        Scanner newsNumber = new Scanner(System.in);
        System.out.println("Enter the number of news you want to read");
        int number = newsNumber.nextInt();
        number--;
        System.out.println(getNewsList().get(number).getTitle());
        System.out.println("---------------------------------");
        System.out.println("author is: " + getNewsList().get(number).getAuthor());
        System.out.println("source is: " + getNewsList().get(number).getSource());
        System.out.println("---------------------------------");
        System.out.println(getNewsList().get(number).getContent());
        System.out.println("more information >" + getNewsList().get(0).getUrl());

    }

}
