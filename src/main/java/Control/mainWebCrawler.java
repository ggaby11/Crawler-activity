package Control;

import Modelo.webCrawler;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * @author Ggaby.sg | Keyly.OD
 */
public class mainWebCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        webCrawler wC = new webCrawler();
        Queue<String> q = new LinkedList<>();

        System.out.print("Ingrese el link: ");
        String url = sc.nextLine();
        System.out.print("Número maximo de enlaces a buscar en cada página: ");
        wC.setMaxLevel(sc.nextInt());
        System.out.print("Cantidad de datos desea obtener: ");
        int size = sc.nextInt();

        wC.algoritmo(url, 0, size, q);

        System.out.println("Cantidad de links: " + wC.getLinks().size());
    }
}
