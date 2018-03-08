package com.bootdo.thesisMamager.controller.api;


import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author
 */
public class ParserList {
    private static String fileName = "./src/bbb.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fileName);
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("aml:annotation");
        ArrayList nodeList = new ArrayList();
        for (int i = 0; i < nodes.getLength(); i++)
        {
            nodeList.add(i, (Node) nodes.item(i));
        }
        if ((nodeList != null) && (nodeList.size() > 0)) {
            for (int i = 0; i < nodeList.size(); i++) {
                Node node = (Node) nodeList.get(i);
                if (node instanceof Element) {
                    if ((node != null) && (node.getNodeName() != null)&& (node.getNodeName().equals("aml:annotation"))) {
                        //遍历整个xml某节点指定的属性
                        NamedNodeMap attrs=node.getAttributes();
                        if(attrs.getLength()>0 && attrs!=null){
                            Node biaoqian=attrs.getNamedItem("w:type");
                            if(biaoqian != null && "Word.Bookmark.Start".equals(biaoqian.getNodeValue())){
                                Node list=doc.createElement("list");
                                Node parent2 = node.getParentNode().getParentNode();
                                Node parent1 = node.getParentNode();
                                parent2.replaceChild(list,parent1);
                                list.appendChild(parent1);
                            }
                        }
                    }
                }
            }
        }
        //开始把Document映射到文件
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transFormer = transFactory.newTransformer();
        //设置输出结果
        DOMSource domSource = new DOMSource(doc);

         //生成xml文件
         File file = new File("./src/bbb.xml");

        //判断是否存在,如果不存在,则创建

        //文件输出流
        FileOutputStream out = new FileOutputStream(file);

        //设置输入源
        StreamResult xmlResult = new StreamResult(out);

        //输出xml文件
        transFormer.transform(domSource, xmlResult);

        //测试文件输出的路径
        System.out.println(file.getAbsolutePath());


    }


}
