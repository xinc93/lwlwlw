package com.bootdo.thesisMamager.controller.api;

import org.dom4j.*;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * @author
 */
public class Test {
    public  static  int kkkk =0;
    public  static int k(){
        return kkkk++;
    }
    public static void main(String[] args) throws IOException, DocumentException {

        File file = new File("./src/vvv.xml");
        SAXReader saxReader=new SAXReader();
        Document document=saxReader.read(file);

            //w:r标签变量


        //取得所有标签节点
            //一级
            Element root = document.getRootElement();
            //一级
            List<Element> roots = root.elements();
            //二级
            roots.stream().forEach(i2->{
                if("w:body".equals(i2.getQName().getQualifiedName())){
                    //三级
                    List<Element> i3List = i2.elements();
                    i3List.stream().forEach(i3->{
                        //四级节点中包含wp标签  和      wx:sub-section标签还需要迭代多级级才能找到书签
                        /*
                        *  ---wx:sub-section 标签多层迭代---
                        *  因为wx:sub-section 标签中含有很多层 wx:sub-section -> wp的嵌套
                        *  所以findParent方法必须接收最下层w:p标签的节点的list 才能不漏掉所有的书签节点
                        *  所以迭代很多次
                        * */
                        if("wx:sub-section".equals(i3.getQualifiedName())){
                            List<Element> i4List = i3.elements();
                            i4List.stream().forEach(i5->{
                                if("wx:sub-section".equals(i5.getQualifiedName())){
                                    List<Element> i5List = i5.elements();
                                    i5List.stream().forEach(i6->{
                                        if("wx:sub-section".equals(i6.getQualifiedName())){
                                            List<Element> i6List = i6.elements();
                                            i6List.stream().forEach(i7->{
                                                findParent( i7.elements());
                                            });
                                        }else{
                                            List<Element> i6List = i6.elements();
                                            findParent(i6List);
                                        }
                                    });
                                //--wx:sub-section 标签多层迭代结束--
                                }else{
                                    List<Element> i5List = i5.elements();
                                    findParent(i5List);
                                }
                            });
                        }else{
                            List<Element> i4List = i3.elements();
                            List<Element> i4List2 = i3.elements();

                            //findParent(i4List);
                        }

                    });
                }
            });

           OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(
                    new FileWriter(new File("./src/vvv.xml")), format);
            writer.write(root);
            writer.close();
    }

    public static void changeXML(String eleName1,String eleName2,String OutPath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document document = builder.parse("src/book.xml");
        Document doc = DOMDocumentFactory.getInstance().createDocument(); SAXReader reader = new SAXReader();
        File file = new File("./src/vvv.xml");
        Document document;
        try {
            document = reader.read(file);
            Element root = document.getRootElement();
            Element r = DocumentHelper.createElement(root.getName());
            doc.add(r);
            Element e = DocumentHelper.createElement(eleName1);
            Element ele = DocumentHelper.createElement(eleName2);
            List<Element> eleList = root.elements();
            for(Element element:eleList){
                if(element.getName().contains(eleName1)){
                    e.add((Element)(element.clone()));
                }else if(element.getName().contains(eleName2)){
                    ele.add((Element)(element.clone()));
                }else{
                    r.add((Element)(element.clone()));
                }
            }
            r.add(e);
            r.add(ele);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter writer = new XMLWriter(
                    new FileWriter(new File("./src/vvv.xml")), format);
            writer.write(doc);
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();  }
    }


    /**
     *  替换最下层wp节点中wt标签里的值的方法
     * @param iList wp下所有的标签List
     */
    public static void findParent(List<Element> iList){
        Element wr = DocumentHelper.createElement("w:r");
        for (Element i5:iList) {
            //根据书签标签找到上一级节点 处理数据
            if("aml:annotation".equals(i5.getQName().getQualifiedName()) && "Word.Bookmark.Start".equals(i5.attribute("type").getValue())){
                String[] sss = i5.attribute("name").getValue().split("_");
                String wName =sss[0]+"_"+sss[1] ;
                System.out.print("--"+wName+"--"+k());
                //根据书签父节点  找到节点需要替换的标签数据 wp -> wr -> wt
                int k =1;
                Element i5Parent=i5.getParent();
                List<Element> elementList =i5Parent.elements();
                //迭代wp标签list  获取 wr
                Iterator iteratorWp = elementList.iterator();
                while(iteratorWp.hasNext()){
                    //wp下的节点们
                    Element elementWpNext = (Element) iteratorWp.next();
                    if("r".equals(elementWpNext.getName())){
                        List<Element> elementWrList = elementWpNext.elements();
                        //循环wr标签list 获得wt 并且  修改内容和复制节点
                        if(k == 1){
                            k++;
                            for (Element elementWt:elementWrList) {
                                if("t".equals(elementWt.getName())){
                                    elementWt.setText("${"+wName+"}");
                                    wr.add((Element)elementWt.clone());
                                }else{
                                    wr.add((Element)elementWt.clone());
                                }
                            }
                        }
                        //删除多余的节点
                        i5Parent.remove(elementWpNext);
                    }
                }
                //将多余的非wr的节点和组装成的wr节点加回原始的wp
                i5Parent.add((Element)wr.clone());
            }
        }
    }
}