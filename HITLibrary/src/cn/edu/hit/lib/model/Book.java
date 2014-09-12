package cn.edu.hit.lib.model;


public class Book {
	public long id;
	public String title;//书名
	public String author;//作者
	public String publish;//出版日期
	public String year;//出版年份
	public String page;//页数
	public String price;
	public String ISBN;//ISBN/ISSN
	public String subject;//主题
	public String callNo;//索取号
	public String relInfo;//相关信息
	public String sort;//分类
	public String frontIntro;//简介
	
	public String toString(){
		if(title != null)
			return title;
		else
			return "null";
	}
}
