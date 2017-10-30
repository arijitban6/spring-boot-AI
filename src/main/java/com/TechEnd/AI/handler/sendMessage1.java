package com.TechEnd.AI.handler;

import com.TechEnd.AI.domain.SearchResult;
import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.buttons.Button;
import com.github.messenger4j.send.templates.GenericTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arijit on 10/28/2017.
 */
public class sendMessage1 {
    private static final Logger logger = LoggerFactory.getLogger(sendMessage1.class);

    com.TechEnd.AI.event.Action Action;
    MessengerSendClient sendClient;
    SendTextMessage textMessage= new SendTextMessage(sendClient);

    private void sendSpringDoc(String recipientId, String keyword) throws MessengerApiException, MessengerIOException, IOException {

        Document doc = Jsoup.connect(("https://spring.io/search?q=").concat(keyword)).get();
        String countResult = doc.select("div.search-results--count").first().ownText();
        Elements searchResult = doc.select("section.search-result");
        List<SearchResult> searchResults = searchResult.stream().map(element ->
                new SearchResult(element.select("a").first().ownText(),
                        element.select("a").first().absUrl("href"),
                        element.select("div.search-result--subtitle").first().ownText(),
                        element.select("div.search-result--summary").first().ownText())
        ).limit(3).collect(Collectors.toList());

        final List<Button> firstLink = Button.newListBuilder()
                .addUrlButton("Open Link", searchResults.get(0).getLink()).toList()
                .build();
        final List<Button> secondLink = Button.newListBuilder()
                .addUrlButton("Open Link", searchResults.get(1).getLink()).toList()
                .build();
        final List<Button> thirdtLink = Button.newListBuilder()
                .addUrlButton("Open Link", searchResults.get(2).getLink()).toList()
                .build();
        final List<Button> searchLink = Button.newListBuilder()
                .addUrlButton("Open Link", ("https://spring.io/search?q=").concat(keyword)).toList()
                .build();



        final GenericTemplate genericTemplate = GenericTemplate.newBuilder()
                .addElements()
                .addElement(searchResults.get(0).getTitle())
                .subtitle(searchResults.get(0).getSubtitle())
                .itemUrl(searchResults.get(0).getLink())
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                .buttons(firstLink)
                .toList()
                .addElement(searchResults.get(1).getTitle())
                .subtitle(searchResults.get(1).getSubtitle())
                .itemUrl(searchResults.get(1).getLink())
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                .buttons(secondLink)
                .toList()
                .addElement(searchResults.get(2).getTitle())
                .subtitle(searchResults.get(2).getSubtitle())
                .itemUrl(searchResults.get(2).getLink())
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                .buttons(thirdtLink)
                .toList()
                .addElement("All results " + countResult)
                .subtitle("Spring Search Result")
                .itemUrl(("https://spring.io/search?q=").concat(keyword))
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/2/20/Pivotal_Java_Spring_Logo.png")
                .buttons(searchLink)
                .toList()
                .done()
                .build();

        this.sendClient.sendTemplate(recipientId, genericTemplate);
    }



}
