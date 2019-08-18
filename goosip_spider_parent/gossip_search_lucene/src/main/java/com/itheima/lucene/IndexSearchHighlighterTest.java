package com.itheima.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class IndexSearchHighlighterTest {

    /**
     * 高亮的原理: 对关键字加高亮前缀<font color="#cc0000">  关键字:大数据  高亮后缀</font>
     * <p>
     * 索引库中没有高亮(词条在文档的位置信息), 在查询结果出来后,二次添加高亮信息
     */
    @Test
    public void highLightTest() throws IOException, InvalidTokenOffsetsException, ParseException {
        //1. 获取索引搜索器
        IndexSearcher indexSearcher = getIndexSearcher();


        //2.创建查询对象
        /**
         * 带分词的查询，对你输入的内容先分词，再执行查询
         */
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("蓝瘦香菇是好东西，我们都需要");


        //设置高亮信息
        String preTag = "<font color='#cc0000'>";
        String postTag = "</font>";
        Formatter formatter = new SimpleHTMLFormatter(preTag, postTag);
        Scorer scorer = new QueryScorer(query);
        ;
        Highlighter highlighter = new Highlighter(formatter, scorer);


        //3. 执行查询
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);


        //4. 遍历结果:
        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 获取文档编号
            int docID = scoreDoc.doc;
            Document doc = indexSearcher.doc(docID);
            System.out.println("id: " + doc.get("id"));

            String title = doc.get("title");
            System.out.println("未高亮的：" + title);

            // 用高亮工具处理普通的查询结果,参数：分词器，要高亮的字段的名称，高亮字段的原始值
            String hTitle = highlighter.getBestFragment(new IKAnalyzer(), "title", title);

            System.out.println("高亮的结果: " + hTitle);

            // 获取文档的得分
            System.out.println("得分：" + scoreDoc.score);
        }

        //关闭资源

    }

    /**
     * 获取索引搜索器
     *
     * @return
     */
    public IndexSearcher getIndexSearcher() throws IOException {
        //1. 创建索引库目录对象
        FSDirectory directory = FSDirectory.open(new File("D:\\ikindex"));


        //2. 索引读取器: 读取索引库
        DirectoryReader directoryReader = DirectoryReader.open(directory);


        //3. 创建索引搜索器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);


        return indexSearcher;

    }


    /**
     * 分页查询: 不需要掌握   后面掌握solr的分页
     */
    @Test
    public void  pageQuery() throws IOException, ParseException {

        Integer page = 1;

        Integer pageSize = 15;

        Integer start =  ( page - 1 ) * pageSize ;

        Integer  end = start + pageSize - 1;


        //获取索引搜索器
        IndexSearcher indexSearcher = getIndexSearcher();

        //1. 获取查询对象
        QueryParser parser = new QueryParser("title", new IKAnalyzer());
        Query query = parser.parse("蓝瘦香菇是好东西，我们都需要");

        //2. 执行查询
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);

        System.out.println("本次搜索共" + topDocs.totalHits + "条数据");

        //3. 获取分页结果
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //获取第二页的数据
        for(int i = start; i<= end; i++){
            ScoreDoc scoreDoc = scoreDocs[i];

            // 获取文档编号
            int docID = scoreDoc.doc;
            Document doc = indexSearcher.doc(docID);
            System.out.println("id: " + doc.get("id"));

            String title = doc.get("title");
            System.out.println("未高亮的：" +title);


            String click = doc.get("click");
            System.out.println("点击量:" + click);


            // 获取文档的得分
            System.out.println("得分：" + scoreDoc.score);

        }

    }

    /**
     * 使用ik分词器创建索引: 使用了加权
     */
    @Test
    public void indexWriteBoostTest01() throws IOException {

        //1. 创建索引库存放的目录结构:  磁盘的目录    内存目录
        Directory directory = FSDirectory.open(new File("D:\\ikindex"));

        //2. 创建分词器  和索引写入器的配置对象
        /**
         * 标准分词器:不支持中文
         */
        /*Analyzer analyzer = new StandardAnalyzer();*/
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
        doc.add(new StringField("id","999999999999999", Field.Store.YES));

        /**
         * TextField:  分词   可以搜索    存储到索引
         */
        TextField title = new TextField("title", "我爱中华人民共和国,盘她", Field.Store.YES);
        // 加权重
        title.setBoost(10000f);
        doc.add(title);


        doc.add(new TextField("content","张柏芝腿玩年和儿子生活丰富多彩，次子当街热舞潮盘她范十足", Field.Store.YES));


        /**
         * 只存储, 不分词  不能搜索
         */
        doc.add(new StoredField("url","https://xw.qq.com/cmsid/20190630V0BKPQ00"));


        /**
         * LongField:  可以搜索   分词    存储到索引库
         */
        doc.add(new LongField("click",888888888l, Field.Store.YES));


        //5. 调用索引写入器的索引写入方法
        indexWriter.addDocument(doc);


        //6. 提交
        indexWriter.commit();

        //7. 关闭资源
        indexWriter.close();
        directory.close();

    }


}


