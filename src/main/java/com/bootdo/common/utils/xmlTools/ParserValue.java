package com.bootdo.common.utils.xmlTools;

import org.dom4j.*;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * @author
 */
public class ParserValue {
    public  static  int kkkk =0;
    public  static int k(){
        return kkkk++;
    }





    /**
     *
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static void findRoot(Document document,String writePath,String filename) throws DocumentException {
        /*
        SAXReader saxReader=new SAXReader();
        Document document2=saxReader.read(file);*/

        Element heard1 = DocumentHelper.createElement("?xml version=\"1.0\" encoding=\"utf-8\"?");
        Element heard2 = DocumentHelper.createElement("?mso-application progid=\"Word.Document\"?");
        //取得所有标签节点
        //一级
        Element root = document.getRootElement();
        //一级
        List<Element> roots = root.elements();
        //二级
        ///////////////处理数据////////////////////
        roots.stream().forEach(i2 -> {
            if ("w:body".equals(i2.getQName().getQualifiedName())) {
                //三级
                List<Element> i3List = i2.elements();
                i3List.stream().forEach(i3 -> {
                    //四级节点中包含wp标签  和      wx:sub-section标签还需要迭代多级级才能找到书签
                        /*
                        *  ---wx:sub-section 标签多层迭代---
                        *  因为wx:sub-section 标签中含有很多层 wx:sub-section -> wp的嵌套
                            *  所以findParent方法必须接收最下层w:p标签的节点的list 才能不漏掉所有的书签节点
                        *  所以迭代很多次
                            * */
                    if ("wx:sub-section".equals(i3.getQualifiedName())) {
                        List<Element> i4List = i3.elements();
                        i4List.stream().forEach(i5 -> {
                            if ("wx:sub-section".equals(i5.getQualifiedName())) {
                                List<Element> i5List = i5.elements();
                                i5List.stream().forEach(i6 -> {
                                    if ("wx:sub-section".equals(i6.getQualifiedName())) {
                                        List<Element> i6List = i6.elements();
                                        i6List.stream().forEach(i7 -> {
                                            findParent(i7.elements());
                                        });
                                    } else {
                                        List<Element> i6List = i6.elements();
                                        findParent(i6List);
                                    }
                                });
                                //--wx:sub-section 标签多层迭代结束--
                            } else {
                                List<Element> i5List = i5.elements();
                                findParent(i5List);

                            }
                        });
                    } else {
                        List<Element> i4List = i3.elements();
                        findParent(i4List);
                    }
                });
            }
        });

        OutputFormat format = OutputFormat.createPrettyPrint();
        //  FileOutputStream out = new FileOutputStream(filePath );
        format.setEncoding("utf-8");
        //format.setEncoding("gb2312");*//**//**//* xml的编码 *//*
        XMLWriter writer = null;
        try {
            File file = new File(writePath+"/"+filename);

            writer = new XMLWriter(new FileWriter(file), format);
            writer.writeOpen(heard1);
            writer.writeOpen(heard2);
            writer.write(root);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * 处理完数据以后进行给包含书签的wp标签加list标签的方法
     * @return
     */
    public static void setList(Element wbody,List<Element> i4List,Element element) {
        Boolean flag = true;
        Element list = DocumentHelper.createElement("list");
        for (Element i5:i4List) {
            //根据书签标签找到上一级节点 处理数据
            if("aml:annotation".equals(i5.getQName().getQualifiedName())
                    && "Word.Bookmark.Start".equals(i5.attribute("type").getValue())) {
                flag = true;
                break;
            }else {
                flag = false;
            }
        }
        if(i4List.size()==0){
            System.out.print("--不正确的标签次数--"+k());

            wbody.add((Element)element.clone());
        }else {
            Element i5Parent=i4List.get(0).getParent();
            if(flag){
                System.out.print("-书签-"+i5Parent.getQName().getQualifiedName()+"--"+k());
                list.add((Element)i5Parent.clone());
                wbody.add((Element)list.clone());
            }else {
                System.out.print("-不是书签-"+i5Parent.getQName().getQualifiedName()+"--"+k());
                wbody.add((Element)i5Parent.clone());
            }
        }

    }
}