package com.itheima.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class IndexWriterTest {


    /**
     * 创建索引的方法
     */
    @Test
    public void indexWriteTest01() throws IOException {

        //1. 创建索引库存放的目录结构:  磁盘的目录    内存目录
        Directory directory = FSDirectory.open(new File("D:\\index"));

        //2. 创建分词器  和索引写入器的配置对象
        /**
         * 标准分词器:不支持中文
         */
        Analyzer analyzer = new StandardAnalyzer();
        /**
         * 第一参数:  版本
         * 第二个参数: 分词器
         */
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);

        //3.  创建索引写入器
        /**
         * 第一个参数: 写入的目录
         * 第二个参数:  写入器配置对象
         */
        IndexWriter indexWriter = new IndexWriter(directory,config);


        //4. 创建document文档对象
        Document doc = new Document();
        /**
         * stringField: 不分词  可以搜索   存储到索引库
         */
        doc.add(new StringField("id","11455479426697134444", Field.Store.YES));

        /**
         * TextField:  分词   可以搜索    存储到索引
         */
        doc.add(new TextField("title","张柏芝和儿子生活丰富多彩，次子当街热舞潮范十足", Field.Store.YES));


        doc.add(new TextField("content","张柏芝和儿子生活丰富多彩，次子当街热舞潮范十足", Field.Store.NO));


        /**
         * 只存储, 不分词  不能搜索
         */
        doc.add(new StoredField("url","https://xw.qq.com/cmsid/20190630V0BKPQ00"));


        /**
         * LongField:  可以搜索   分词    存储到索引库
         */
        doc.add(new LongField("click",10000l, Field.Store.YES));


        //5. 调用索引写入器的索引写入方法
        indexWriter.addDocument(doc);


        //6. 提交
        indexWriter.commit();

        //7. 关闭资源
        indexWriter.close();
        directory.close();

    }



    @Test
    public void indexWriteIKTest01() throws IOException {

        //1. 创建索引库存放的目录结构:  磁盘的目录    内存目录
        Directory directory = FSDirectory.open(new File("D:\\ikindex"));

        //2. 创建分词器  和索引写入器的配置对象
        /**
         * 使用ik分词器: 对中文很好分词
         */
        Analyzer analyzer = new IKAnalyzer();
        /**
         * 第一参数:  版本
         * 第二个参数: 分词器
         */
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);

        //3.  创建索引写入器
        /**
         * 第一个参数: 写入的目录
         * 第二个参数:  写入器配置对象
         */
        IndexWriter indexWriter = new IndexWriter(directory,config);


        //4. 创建document文档对象
        Document doc = new Document();
        /**
         * stringField: 不分词  可以搜索   存储到索引库
         */
        doc.add(new StringField("id","1145547942669713414", Field.Store.YES));

        /**
         * TextField:  分词   可以搜索    存储到索引
         */
        doc.add(new TextField("title","张柏芝和腿玩年儿子生活丰富多彩,蓝瘦香菇,我好爱你盘她，次子当街热舞潮范十足", Field.Store.YES));


        doc.add(new TextField("content","张柏芝腿玩年和儿子生活丰富多彩，次子当街热舞潮盘她范十足", Field.Store.YES));


        /**
         * 只存储, 不分词  不能搜索
         */
        doc.add(new StoredField("url","https://xw.qq.com/cmsid/20190630V0BKPQ00"));


        /**
         * LongField:  可以搜索   分词    存储到索引库
         */
        doc.add(new LongField("click",10000l, Field.Store.YES));


        //5. 调用索引写入器的索引写入方法
        indexWriter.addDocument(doc);


        //6. 提交
        indexWriter.commit();

        //7. 关闭资源
        indexWriter.close();
        directory.close();

    }



}
