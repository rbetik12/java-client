package io.rbetik12.gui;

import io.rbetik12.models.AuthWindowTranslation;
import io.rbetik12.models.Languages;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TranslationManager {

    public TranslationManager() {
    }

    public AuthWindowTranslation getAuthTranslation(Languages language) {
        List<String> list = new ArrayList<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("config.xml");
            Document document = documentBuilder.parse(is);
            NodeList authTranslationNodeList = document.getElementsByTagName("auth");
            Node authTranslationRoot = authTranslationNodeList.item(0);

            Node translationNode = null;
            switch (language) {
                case Russian:
                    translationNode = authTranslationRoot.getChildNodes().item(1);
                    break;
                case Swedish:
                    translationNode = authTranslationRoot.getChildNodes().item(3);
                    break;
                case German:
                    translationNode = authTranslationRoot.getChildNodes().item(5);
                    break;
                case English:
                    translationNode = authTranslationRoot.getChildNodes().item(7);
                    break;
            }

            NodeList translationNodes = translationNode.getChildNodes();
            for (int i = 0; i < translationNodes.getLength(); i++) {
                Node node = translationNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    list.add(node.getTextContent());
                }
            }

            AuthWindowTranslation authWindowTranslation = new AuthWindowTranslation();
            authWindowTranslation.username = list.get(0);
            authWindowTranslation.password = list.get(1);
            authWindowTranslation.submit = list.get(2);
            authWindowTranslation.close = list.get(3);
            authWindowTranslation.error = list.get(4);
            return authWindowTranslation;
        } catch (ParserConfigurationException e) {
            System.out.println("Can't create document builder: " + e);
        } catch (SAXException e) {
            System.out.println("Can't parse XML: " + e);
        } catch (IOException e) {
            System.out.println("Can't open file: " + e);
        }
        return null;
    }
}
