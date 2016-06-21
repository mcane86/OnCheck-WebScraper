package Scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class Scraper {

    ArrayList<Parser> inspections = new ArrayList<>();
    URL url;
    InputStream is = null;
    BufferedReader br;
    BufferedWriter bw;
    String full = "empty";
    String line;
    private File file = new File("/Users/michaelcane/Dev/Labs/WebScraper/Web_Scraper/data/stateData.html"); //This is a local file
    private Elements rows;


    /**
     * This method retrieves the html file from the DHSS Food Establishment Inspection Reports webpage.
     * This method, due to the Thread.sleep functionality and due to the size of the html file, takes
     * roughly 4 minutes and 40 seconds. The Thread.sleep is 3 minutes to give the webpage time to load.
     *
     * @throws IOException
     */
    public File getDocument() throws IOException {
        String inspection = "http://dhss.delaware.gov/dhss/dph/hsp/Default.aspx?listAll=1&sort=Establishment";
        File stateData = new File("/Users/michaelcane/Dev/Labs/WebScraper/Web_Scraper/data/stateData.html");
        URL url = new URL(inspection);
        try {
            is = url.openStream();
            try {
                Thread.sleep(210 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            downloadFileFromSite(inspection, stateData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stateData;
    }

    /**
     * This will connect to the @param dataSite and download the html file to the @param destination.
     */
    public static void downloadFileFromSite(String dataSite, File destination) {
        try {
            URL website = new URL(dataSite);
            ReadableByteChannel readable;
            readable = Channels.newChannel(website.openStream());
            FileOutputStream fileOutput = new FileOutputStream(destination);
            fileOutput.getChannel().transferFrom(readable, 0, Long.MAX_VALUE);
            fileOutput.close();
            readable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Only used for http
     * @return
     * @param
     */
//    public File getDocument() {
//        Document doc = null;
//        String str = "";
//
//        // Creating a File object that represents the disk file.
//        PrintStream o = null;
//        try {
//            o = new PrintStream(new File("Test.html"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // Store current System.out before assigning a new value
//        PrintStream console = System.out;
//
//        // Assign o to output stream
//        System.setOut(o);
//
//        try {
//            url = new URL("http://dhss.delaware.gov/dhss/dph/hsp/Default.aspx?listAll=1&sort=Establishment");
//            is = url.openStream();
//            try {
//                Thread.sleep(210*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            br = new BufferedReader(new InputStreamReader(is));
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.setOut(console);
//        return file;
//    }

    /**
     * Converts local html file to Jsoup Document
     *
     * @param file
     * @return
     */
    public Document fileToDocument(File file) {
        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * Gets the table of health inspection info
     *
     * @param doc
     * @return
     */
    public Element getTableElement(Document doc) {
        Element table = doc.select("table.datagrid").first();
        return table;
    }

    /**
     * Gets the first headers for the table
     *
     * @param table
     * @return
     */
    public Element getTableHeaders(Element table) {
        Element row = table.select("tr.datagridHeader").first();
        return row;
    }

    /**
     * Gets every row of data in the table
     *
     * @param table
     */
    public Elements getRows(Element table) {
        rows = table.select("tr.datagridItem, tr.datagridAlternatingItem");
        return rows;
    }

    /**
     * Removes HTML and populates ArrayList of inspection objects
     *
     * @param rows
     */
    public void removeHTML(Elements rows) {
        int count = 0;
        for (Element row : rows) {
            inspections.add(new Parser());
            String[] line = row.toString().split("<td(.*?)>");
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].replaceAll("<a(.*?)?>|<tr(.*?)?>|<td(.*?)?>|<span(.*?)?>|</span>|</td>|</a>|</tr>|\\n", "");
                line[i] = line[i].trim();
            }
//            restaurantManager.saveRestaurant(line[1], line[2]);
            count++;
        }


    }

}
