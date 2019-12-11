package main.java.at.fhv.teama.easyticket.server.messaging;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import main.java.at.fhv.teama.easyticket.server.messaging.RssMessage;

public class XMLParser {

//to use: ArrayList<RssMessage> rssMessages = XMLParser.parseXML("https://www.festivalticker.de/rss-festivalfeed/festivalkalender.xml");
    public static ArrayList<RssMessage> parseXML(String url)
    {
        ArrayList<RssMessage> rssMessages = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL(url).openStream());
            NodeList nodeList = doc.getElementsByTagName("item");

            for(int i = 0; i < nodeList.getLength(); i++)
            {
                Node nNode = nodeList.item(i);
                Element eElement = (Element) nNode;
                String title = eElement
                        .getElementsByTagName("title")
                        .item(0)
                        .getTextContent();
                String pubDate = eElement
                        .getElementsByTagName("pubDate")
                        .item(0)
                        .getTextContent();
                String description = eElement
                        .getElementsByTagName("description")
                        .item(0)
                        .getTextContent();
                String link = eElement
                        .getElementsByTagName("link")
                        .item(0)
                        .getTextContent();

                RssMessage rssMessage = new RssMessage(title,url,description,pubDate);
                rssMessages.add(rssMessage);
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return rssMessages;
    }
}
