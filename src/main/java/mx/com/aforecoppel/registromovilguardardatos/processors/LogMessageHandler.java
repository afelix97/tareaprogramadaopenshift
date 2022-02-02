package mx.com.aforecoppel.registromovilguardardatos.processors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.soap.Node;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class LogMessageHandler implements SOAPHandler<SOAPMessageContext> {


    final static org.slf4j.Logger logger = LoggerFactory.getLogger(LogMessageHandler.class);

    private String sInCommingMessage;
    private String sOutgoingMessage;

    public LogMessageHandler() {
    }

    @Override
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage msg = context.getMessage();
        String prettyXML = null;

        try {

            Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            String result = null;
            ByteArrayOutputStream baos = null;
            baos = new ByteArrayOutputStream();
            msg.writeTo(baos);
            result = baos.toString();
            //if (outboundProperty.booleanValue()) {
            logger.debug("::[WSREGISTROMOVILGUARDARDATOS]:: -----------------------------------");
            logger.debug("::[WSREGISTROMOVILGUARDARDATOS]:: TIPO RECIBIDO--" + outboundProperty);
            logger.debug("::[WSREGISTROMOVILGUARDARDATOS]:: PETICION....." + printPrettyXML(result));
            logger.debug("-----------------------------------");
            if (outboundProperty.booleanValue()) {
                sInCommingMessage = printPrettyXML(result);
            } else {
                sOutgoingMessage = printPrettyXML(result);
            }


        } catch (Exception ex) {
            Logger.getLogger(LogMessageHandler.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    public String printPrettyXML(String xml) {
        String prettyXML = null;
        Document document = null;
        XPath xPath = null;
        NodeList nodeList = null;
        Transformer transformer = null;
        StringWriter stringWriter = null;
        StreamResult streamResult = null;

        try {
            document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(new ByteArrayInputStream(xml
                            .getBytes("utf-8"))));
            xPath = XPathFactory.newInstance().newXPath();
            nodeList = (NodeList) xPath.evaluate(
                    "//text()[normalize-space()='']", document,
                    XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }

            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                    "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");

            stringWriter = new StringWriter();
            streamResult = new StreamResult(stringWriter);

            transformer.transform(new DOMSource(document), streamResult);


        } catch (Exception e) {

        } finally {

        }

        return stringWriter.toString().trim();

    }

    public String getsInCommingMessage() {
        return sInCommingMessage;
    }

    public void setsInCommingMessage(String sInCommingMessage) {
        this.sInCommingMessage = sInCommingMessage;
    }

    public String getsOutgoingMessage() {
        return sOutgoingMessage;
    }

    public void setsOutgoingMessage(String sOutgoingMessage) {
        this.sOutgoingMessage = sOutgoingMessage;
    }
}