package com.bootdo.common.utils.xmlTools;


import com.bootdo.thesisMamager.dao.ThesisTemplateAttrDao;
import com.bootdo.thesisMamager.domain.ThesisTemplateAttrDO;
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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 */
public class ParserList {

    static int k = 0;
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
        }*/
        //开始把Document映射到文件
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transFormer = transFactory.newTransformer();
        //设置输出结果
        DOMSource domSource = new DOMSource(null);

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

    public static byte[] parseList(String filePath,String fileName) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileDoc=new File(filePath+"/"+fileName);
            Document doc = builder.parse(fileDoc);
            Element root = doc.getDocumentElement();
            NodeList nodes = root.getElementsByTagName("aml:annotation");
            ArrayList nodeList = new ArrayList();
            for (int i = 0; i < nodes.getLength(); i++) {
                nodeList.add(i, (Node) nodes.item(i));
            }
            if ((nodeList != null) && (nodeList.size() > 0)) {
                for (int i = 0; i < nodeList.size(); i++) {
                    Node node = (Node) nodeList.get(i);
                    if (node instanceof Element) {
                        if ((node != null) && (node.getNodeName() != null) && (node.getNodeName().equals("aml:annotation"))) {
                            //遍历整个xml某节点指定的属性
                            NamedNodeMap attrs = node.getAttributes();
                            if (attrs.getLength() > 0 && attrs != null) {
                                Node biaoqian = attrs.getNamedItem("w:type");
                                if (biaoqian != null && "Word.Bookmark.Start".equals(biaoqian.getNodeValue())) {
                                    Node list = doc.createElement("list");
                                    Node parent2 = node.getParentNode().getParentNode();
                                    Node parent1 = node.getParentNode();
                                    parent2.replaceChild(list, parent1);
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
            File file = new File(filePath+"/"+fileName);

            //判断是否存在,如果不存在,则创建

            //文件输出流
            FileOutputStream out = new FileOutputStream(file);


            //设置输入源
            StreamResult xmlResult = new StreamResult(out);

            //输出xml文件
            transFormer.transform(domSource, xmlResult);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return File2byte(filePath+"/"+fileName);
    }


    public static byte[] operationFile(String filePath, String fileName, List<ThesisTemplateAttrDO> attrList) {
        File f = new File(filePath+"/"+fileName);
        String filename = f.getName();
        // tmpfile为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
        File tmpfile = new File(f.getParentFile().getAbsolutePath()
                + "\\" + filename + ".tmp");
        try {
            InputStream is = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

            boolean flag = false;
            String str = null;
            while (true) {
                str = reader.readLine();

                if (str == null)
                    break;

                if (str.contains("<list>")) {
                    ThesisTemplateAttrDO thesisTemplateAttr = (ThesisTemplateAttrDO)attrList.get(k);
                    str = str.replace("<list>","<#list "+thesisTemplateAttr.getAttrbuteccode()+" as "+thesisTemplateAttr.getAttrType()+"_"+thesisTemplateAttr.getAttrbuteccode()+">"+ "\n");
                    writer.write(str);
                    k=k+1;
                    flag = true;
                }else if (str.contains("</list>")) {
                    str = str.replace("</list>","</#list>");
                    writer.write(str);

                    flag = true;
                }  else{
                    writer.write(str + "\n");
                }
            }

            is.close();

            writer.flush();
            writer.close();

            if (flag) {
                f.delete();
                tmpfile.renameTo(new File(f.getAbsolutePath()));
            } else
                tmpfile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return File2byte(tmpfile.getAbsolutePath());
    }


    public static byte[] File2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

}
