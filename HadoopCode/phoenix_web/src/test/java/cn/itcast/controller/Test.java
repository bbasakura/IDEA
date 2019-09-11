package cn.itcast.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

public class Test {
public static void main(String[] args) {
	 //在xml配置文件中找到htemplate   
	 ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });   
	 BeanFactory factory = (BeanFactory) context;   
	 HbaseTemplate htemplate = (HbaseTemplate) factory.getBean("htemplate");	
	 
	 query01(htemplate);
	 htemplate.execute("t_user_info", new TableCallback<List<Result>>() {
         List<Result> list = new ArrayList<Result>();
         public List<Result> doInTable(HTableInterface table) throws Throwable {
             FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
             FamilyFilter ff = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("info")));  
             fl.addFilter(ff);
             Scan scan = new Scan();
             scan.setFilter(fl);// 为查询设置过滤器的list
             ResultScanner rscanner = table.getScanner(scan);
             for(Result result : rscanner){
                 list.add(result);
                 queryResult(result);
             }
             return list;
         }

     });
  }

private static void queryResult(Result result) throws IOException {
	CellScanner cellScanner = result.cellScanner();
		while (cellScanner.advance()) {
			Cell current = cellScanner.current();
			byte[] familyArray = current.getFamilyArray();
			byte[] valueArray = current.getValueArray();
			byte[] qualifierArray = current.getQualifierArray();
			byte[] rowArray = current.getRowArray();

			System.out.println(new String(rowArray, current.getRowOffset(), current.getRowLength()));
			System.out.print(new String(familyArray, current.getFamilyOffset(), current.getFamilyLength()));
			System.out.print(":" + new String(qualifierArray, current.getQualifierOffset(), current.getQualifierLength()));
			System.out.println(" " + new String(valueArray, current.getValueOffset(), current.getValueLength()));
		}
		System.out.println("-----------------------");
}

private static void query01(HbaseTemplate htemplate) {
	//使用find方法查找  test2为表名 ，zb为列族名称及family   
	 htemplate.find("t_user_info", "base_info", new RowMapper<String>() {    
	 //result为得到的结果集   
	 public String mapRow(Result result, int rowNum) throws Exception { 
	    //循环行      
//		for (KeyValue kv : result.raw()) { 
//			byte[] rowkey = kv.getKey();
//			//得到列族组成列qualifier     
//			String key = new String(kv.getQualifier());     
//			//得到值      
//			String value = new String(kv.getValue());      
//			System.out.println("rowkey= "+Bytes.toString(rowkey)+key + "= "        + Bytes.toString(value.getBytes()));     
//			} 
		 queryResult(result);
		return null;     
	 }    
	});
	}

}
