/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchangerates;

import dao.RatesDao;
import entity.ExchangeRate;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import us.monoid.web.Resty;
import us.monoid.web.XMLResource;

/**
 *
 * @author Nikolaj
 */
public class ExchangeChecker implements Runnable {

    RatesDao DB = new RatesDao();
    
    @Override
    public void run() {
        try {
            //Get the current date
            Date currentDate = new Date();
            
            //Call api with Resty
            Resty r = new Resty();
            XMLResource xml = r.xml("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");

            //Find all currency elements
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml.stream());
            NodeList nlist = doc.getElementsByTagName("currency");

            //Loop through elements and populate ExchangeRates
            for (int i = 0; i < nlist.getLength(); i++) {
                NamedNodeMap attributesMap = nlist.item(i).getAttributes();
                
                String rateValue = attributesMap.getNamedItem("rate").getNodeValue(); 
                
                ExchangeRate rate = new ExchangeRate(
                        attributesMap.getNamedItem("code").getNodeValue(), 
                        attributesMap.getNamedItem("desc").getNodeValue(), 
                        Double.parseDouble(rateValue.equals("-") ? "0.0" : rateValue ),
                        currentDate
                );
                
                DB.create(rate);
            }                      
        } catch (IOException ex) {
            Logger.getLogger(ExchangeChecker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException | SAXException | DOMException | NumberFormatException ex) {
            Logger.getLogger(ExchangeChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
