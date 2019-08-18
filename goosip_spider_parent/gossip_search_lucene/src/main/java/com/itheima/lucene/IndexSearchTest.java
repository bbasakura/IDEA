package com.itheima.lucene;

import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class IndexSearchTest {


    /**
     * 搜索索引库:  创建索引使用什么分词器, 查询就使用什么分词器
     */
    @Test
    public void indexSearcherTest() throws IOException, ParseException, ParseException {
        //1. 创建索引库目录对象
        FSDirectory directory = FSDirectory.open(new File("D:\\ikindex"));


        //2. 索引读取器: 读取索引库
        DirectoryReader directoryReader = DirectoryReader.open(directory);


        //3. 创建索引搜索器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);


        //4. 创建查询对象
        //带分词器的查询
        String  fieldName = "title";
        /**
         * 第一个参数: 查询的字段名称
         * 第二个参数: 分词器类型
         */
        QueryParser queryParser = new QueryParser(fieldName, new IKAnalyzer());

        //创建查询对象:
        Query query = queryParser.parse("蓝瘦香菇");

        //5. 调用索引搜索器的方法进行搜索
        /**
         * 第一个参数: 查询对象
         * 第二个参数: 返回记录的个数
         */
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);


        //6 获取查询结果总条数
        int totalHits = topDocs.totalHits;
        System.out.println("为您找到了记录个数:" + totalHits);


        //7 获取搜索结果的文档id数组
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            float score = scoreDoc.score;

            System.out.println("当前文档的分数:" + score);

            //获取文档的id
            int docId = scoreDoc.doc;
            //根据文档id获取文档的对象
            Document document = indexSearcher.doc(docId);

            //文档字段的id
            String id = document.get("id");

            //文档的标题
            String title = document.get("title");

            //文档的内容
            String content = document.get("content");

            //文档的url
            String url = document.get("url");
            //文档的点击量
            String click = document.get("click");

            //打印结果
            System.out.println("id: " + id );
            System.out.println("title: "  + title);
            System.out.println("content: "  + content );
            System.out.println("url: " + url);
            System.out.println("click: "  + click);
        }


        //8. 关闭资源
        directoryReader.close();
        directory.close();


    }

    /**
     * 抽取出来的一个方法:共用的,只需要传递query查询对象就可以了
     * @param query
     */
    public void executeQuery(Query query) throws IOException {
        //1. 创建索引库的目录对象
        FSDirectory directory = FSDirectory.open(new File("D:\\ikindex"));

        //2. 创建索引读取器
        DirectoryReader directoryReader = DirectoryReader.open(directory);

        //3. 创建索引搜索器
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        //4.执行查询
        TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);


        //5. 获取结果总记录数
        int totalHits = topDocs.totalHits;
        System.out.println("获取总记录数:" + totalHits);


        //6. 获取搜索到的文档id集合
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        //7.遍历集合,获取文档对象, 打印结果
        for (ScoreDoc scoreDoc : scoreDocs) {

            float score = scoreDoc.score;
            System.out.println("得分: " + score);

            int docId = scoreDoc.doc;
            Document document = indexSearcher.doc(docId);

            //文档字段的id
            String id = document.get("id");

            //文档的标题
            String title = document.get("title");

            //文档的内容
            String content = document.get("content");

            //文档的url
            String url = document.get("url");
            //文档的点击量
            String click = document.get("click");

            //打印结果
            System.out.println("id: " + id );
            System.out.println("title: "  + title);
            System.out.println("content: "  + content );
            System.out.println("url: " + url);
            System.out.println("click: "  + click);

        }

        //8. 关闭资源
        directoryReader.close();
        directory.close();
    }

    /**
     * 使用TermQuery查询对象进行查询
     * term : 词条
     */
    @Test
    public void indexSearchTermQuery() throws IOException {
        //创建TermQuery对象
        /**
         * 词条不会进行分词的, 词条可以是一个字, 也可以是一句话
         *
         * 必须完全匹配索引库中的一个词条,才能搜索出来结果
         *
         *
         */
        String fieldName = "title";
        String queryTermString = "蓝瘦香菇";
        TermQuery query = new TermQuery(new Term(fieldName, queryTermString));

        //调用共用的执行查询的方法
        executeQuery(query);
    }


    /**
     * 通配符查询:  *  0-n    ?  1个字符
     *
     * 数据库的通配符:   %      _
     */
    @Test
    public void wildCardQuery() throws IOException {

        //1. 创建一个查询对象
        String  fieldName = "title";
        WildcardQuery wildcardQuery = new WildcardQuery(new Term(fieldName, "蓝瘦香*"));


        //查询语法:    title:蓝瘦香*
        System.out.println(wildcardQuery);


        //2. 调用查询的方法
        executeQuery(wildcardQuery);
    }


    /**
     * 模糊查询:   只要你的查询字符串经过  最多两次编辑 能够匹配上索引库中的词条
     *   移动位置, 调换位置  补位
     */
    @Test
    public void FuzzQueryTest() throws IOException {
        //1. 创建查询对象
        String  fieldName = "title";
        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term(fieldName, "蓝瘦菇香"),1);

        //title:蓝瘦菇香~2   title:蓝瘦菇香~1
        System.out.println("查询语法: " + fuzzyQuery);

        //2. 执行查询打印结果
        executeQuery(fuzzyQuery);
    }


    /**
     * 范围查询:  点击量 [1000,20000]     价格  [6720,8200]
     *
     * 应该对于可量化的字段进行:  点击量   价格    销量    排名    成绩
     */
    @Test
    public void numbericRangeQueryTest() throws IOException {

        //1. 创建查询对象
        String fieldName = "click";
        Long min = 10000l;
        Long max = 20050l;
        /**
         * 第一个参数: 搜索的字段
         * 第二个参数: 最小值
         * 第三个参数: 最大值
         * 第四个参数: 是否包含最小值
         * 第五个参数: 是否包含最大值
         */
        NumericRangeQuery<Long> rangeQuery = NumericRangeQuery.newLongRange(fieldName, min, max, false, true);

        //查询语法: click:[10000 TO 20050]   查询语法: click:{10000 TO 20050]
        System.out.println("查询语法: " + rangeQuery);

        //2. 执行查询
        executeQuery(rangeQuery);
    }



    /**
     * 布尔查询:  && 与  || 或    ! 非
     *
     * MUST
     *
     * SHOULD
     *
     * MUST_NOT
     *
     */
    @Test
    public void booleanQuery() throws IOException {

        BooleanQuery booleanClauses = new BooleanQuery();

        String fieldName = "title";
        WildcardQuery wildcardQuery = new WildcardQuery(new Term(fieldName, "蓝瘦香*"));

        booleanClauses.add(wildcardQuery, BooleanClause.Occur.MUST);

        fieldName = "click";

        NumericRangeQuery<Long> rangeQuery = NumericRangeQuery.newLongRange(fieldName, 10000l, 20050l, false, true);


        booleanClauses.add(rangeQuery, BooleanClause.Occur.MUST_NOT);

        //查询语法: +title:蓝瘦香* +click:{10000 TO 20050]
        //查询语法: +title:蓝瘦香* -click:{10000 TO 20050]
        System.out.println("查询语法: " +  booleanClauses);

        //执行查询
        executeQuery(booleanClauses);

    }


    /**
     * 修改索引库:  先删除原来的  然后创建新的
     */
    @Test
    public  void updateIndex() throws IOException {
        //1.获取索引写入器
        IndexWriter indexWriter = getIndexWriter();

        //2. 修改索引库:
        //2.1 创建新的文档对象
        Document doc = new Document();
        doc.add(new StringField("id","6666666", Field.Store.YES));
        doc.add(new TextField("title","我爱中华人民共和国,盘她,蓝瘦香菇真是好东西,腿玩年", Field.Store.YES));

        doc.add(new LongField("click",100000l, Field.Store.YES));
        doc.add(new StoredField("url","http://www.itcast.cn"));


        //根据id字段进行修改: id是我们自己的字段id
        indexWriter.updateDocument(new Term("id","100"),doc);


        //3. 提交
        indexWriter.commit();

        //4. 关闭资源
        indexWriter.close();

    }


    /**
     * 抽取方法
     * @return
     * @throws IOException
     */
    private IndexWriter getIndexWriter() throws IOException {
        //1. 创建索引目录对象
        FSDirectory directory = FSDirectory.open(new File("D:\\ikindex"));


        //2. 创建ik分词器
        IKAnalyzer ikAnalyzer = new IKAnalyzer();


        //3. 创建索引写入配置对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, ikAnalyzer);

        //4. 创建索引写入器
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        return indexWriter;

    }

    /**
     * 删除索引库:  根据查询条件删除   根据term删除     删除所有
     */
    @Test
    public void deleteIndex() throws IOException {

        //1. 获取索引写入器
        IndexWriter indexWriter = getIndexWriter();


        //2. 进行删除
        //2.1 根据查询删除
        /*Query query = new TermQuery(new Term("id","88"));
        indexWriter.deleteDocuments(query);*/
        //2.2 根据term删除
        indexWriter.deleteDocuments(new Term("id","66"));


        //2.3 删除所有
//        indexWriter.deleteAll();


        //3. 提交
        indexWriter.commit();

        //4. 关闭资源
        indexWriter.close();
    }






}
