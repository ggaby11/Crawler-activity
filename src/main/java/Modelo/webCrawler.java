package Modelo;

import java.util.Queue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.select.Elements;

/**
 * @author Ggaby.sg | Keyly.OD
 */

public class webCrawler {

    private Queue<String> links = new LinkedList<>(); //Cola de los link que va encontrando
    private int maxLevel; //cantidad max de enlaces que se buscaran por pagina

    public void algoritmo(String url, int i, int queueSize, Queue<String> linksAnteriores) {
        if (i < maxLevel && linksAnteriores.size() < queueSize) {
            Document doc = request(url, linksAnteriores);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextUrl = link.absUrl("href");
                    
                    if (addLink(nextUrl)) 
                        algoritmo(nextUrl, i++, queueSize--, linksAnteriores); //RECURSIVIDAD
                }
            }
        }
    }

    public boolean addLink(String url) {
        if (!(this.links.contains(url))) {
            this.links.add(url);
            return true;
        }
        return false;
    }

    private static Document request(String url, Queue<String> linksAnteriores) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                System.out.println("Página: " + doc.title());
                System.out.println("Link: " + url + "\n");
                linksAnteriores.add(url);

                return doc;
            }
        } catch (IOException e) {
            System.out.println("Quizas la página no existe, prueba otra...");
        } catch (IllegalArgumentException ie){
            System.out.println("La página debe iniciar con http:// o https:// \n");
        }

        return null;
    }

    public Queue getLinks() {
        return links;
    }

    public void setLinks(Queue links) {
        this.links = links;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
