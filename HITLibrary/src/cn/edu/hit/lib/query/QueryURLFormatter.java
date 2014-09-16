package cn.edu.hit.lib.query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryURLFormatter {
	public static String SCRIPT_SESSIONID;
	public QueryType queryType;
	public int limit = 10;
	public int page = 1;
	public String dispatch = "smcx";
	public String type = "smcx";
	public int method1 = 1;
	public int retrieveLib = 1;
	public String author = "";												//责任者
	public String subject = "";												//主题词1
	public String title = "";												//题名1
	public String classNo = "";												//分类号
	public String ISBN = "";													//ISBN/ISSN
	public String callNo = "";												//索取号
	public String titleSecond = "";											//题名2
	public String subjectSecond = "";										//主题词2
	public String title_phrase_like = "";									//题名1相似度设定
	public String title_phrase_like_second = "";								//题名2相似度设定
	public String startYear = "";												//出版时间限定
	public String endYear = "";													//出版时间限定
	
	public QueryURLFormatter(QueryType type){
		this.queryType = type;
	}
	
	public void setLimit(int limit){
		this.limit = limit;
	}
	
	public String format(){
		String params = "";
		switch (queryType) {
			case BOOKLIST:
				params = booklistQuery();
				break;
	
			default:
				break;
		}
		return params;
	}
	
	public String booklistQuery() {
		String body = "";
		String selectType = "";
		String retrieveValue = "";
		
		if(!title.equals("")){
			selectType += "title";
			retrieveValue += " and cb.title_phrase like '" + title + "%'";
		}
		if(!author.equals("")){
			selectType += "author";
			retrieveValue += " and cba.author like '" + author + "%'";
		}
		if(!subject.equals("")){
			selectType += "subject";
			retrieveValue += " and cbs.subject like '" + subject + "%'";
		}
		if(!classNo.equals("")){
			selectType += "classno";
			retrieveValue += " and cbcl.class_no like '" + classNo + "%'";
		}
		if(!ISBN.equals("")){
			selectType += "isbn";
			retrieveValue += " and cbi.isbn like '" + ISBN + "%'";
		}
		if(!callNo.equals("")){
			selectType += "callno";
			retrieveValue += " and cbca.call_no like '" + callNo + "%'";
		}
		if(!startYear.equals("")){
			selectType += "startyear";
			retrieveValue += " and cb.publish_year >= '" + startYear + "'";
		}
		if(!endYear.equals("")){
			selectType += "endyear";
			retrieveValue += " and cb.publish_year <= '" + endYear + "'";
		}
		
		try {
			retrieveValue = URLEncoder.encode(retrieveValue, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		body = new StringBuilder()
		.append("callCount=1\n")
		.append("page=/lib/opacAction.do\n")
//		.append("httpSessionId=B6742BFEAB4E2F03BA12D9331BE9D055\n")
		.append("scriptSessionId=" + String.valueOf(SCRIPT_SESSIONID) + "\n")
//		.append("httpSessionId=B6742BFEAB4E2F03BA12D9331BE9D055\n")
//		.append("scriptSessionId=FE2A70CE1ABD5ADC1FE2824C994A943A99\n")
		.append("c0-scriptName=OpacDelegate\n")
		.append("c0-methodName=aaet\n")
		.append("c0-id=0\n")
		.append("c0-e2=string:smcx\n")
		.append("c0-e1=Array:[reference:c0-e2]\n")
		.append("c0-e4=string:" + limit + "\n")
		.append("c0-e3=Array:[reference:c0-e4]\n")
		.append("c0-e6=string:" + page + "\n")
		.append("c0-e5=Array:[reference:c0-e6]\n")
		.append("c0-e8=string:" + endYear + "\n")
		.append("c0-e7=Array:[reference:c0-e8]\n")
		.append("c0-e10=string:" + callNo + "\n")
		.append("c0-e9=Array:[reference:c0-e10]\n")
		.append("c0-e12=string:" + title_phrase_like_second + "\n")
		.append("c0-e11=Array:[reference:c0-e12]\n")
		.append("c0-e14=string:" + subjectSecond + "\n")
		.append("c0-e13=Array:[reference:c0-e14]\n")
		.append("c0-e16=string:1\n")
		.append("c0-e15=Array:[reference:c0-e16]\n")
		.append("c0-e18=string:" + subject + "\n")
		.append("c0-e17=Array:[reference:c0-e18]\n")
		.append("c0-e20=string:" + ISBN + "\n")
		.append("c0-e19=Array:[reference:c0-e20]\n")
		.append("c0-e22=string:" + retrieveValue + "\n")
		.append("c0-e21=Array:[reference:c0-e22]\n")
		.append("c0-e24=string:smcx\n")
		.append("c0-e23=Array:[reference:c0-e24]\n")
		.append("c0-e26=string:1\n")
		.append("c0-e25=Array:[reference:c0-e26]\n")
		.append("c0-e28=string:" + author + "\n")
		.append("c0-e27=Array:[reference:c0-e28]\n")
		.append("c0-e30=string:" + classNo + "\n")
		.append("c0-e29=Array:[reference:c0-e30]\n")
		.append("c0-e32=string:" + title + "\n")
		.append("c0-e31=Array:[reference:c0-e32]\n")
		.append("c0-e34=string:" + endYear + "\n")
		.append("c0-e33=Array:[reference:c0-e34]\n")
		.append("c0-e36=string:" + titleSecond + "\n")
		.append("c0-e35=Array:[reference:c0-e36]\n")
		.append("c0-e38=string:" + title_phrase_like + "\n")
		.append("c0-e37=Array:[reference:c0-e38]\n")
		.append("c0-e40=string:smcx\n")
		.append("c0-e39=Array:[reference:c0-e40]\n")
		.append("c0-e42=string:DoAjax\n")
		.append("c0-e41=Array:[reference:c0-e42]\n")
		.append("c0-e44=string:" + selectType + "\n")
		.append("c0-e43=Array:[reference:c0-e44]\n")
		.append("c0-e46=string:返回查询界面\n")
		.append("c0-e45=Array:[reference:c0-e46]\n")
		.append("c0-e48=string:10\n")
		.append("c0-e47=Array:[reference:c0-e48]\n")
		.append("c0-param0=Object_Object:{ec_i:reference:c0-e1, smcx_crd:reference:c0-e3, smcx_p:reference:c0-e5, endYear:reference:c0-e7, callNo:reference:c0-e9, title_phrase_like_second:reference:c0-e11, subjectSecond:reference:c0-e13, retrieveLib:reference:c0-e15, subject:reference:c0-e17, ISBN:reference:c0-e19, retrieveValue:reference:c0-e21, type:reference:c0-e23, method1:reference:c0-e25, author:reference:c0-e27, classNo:reference:c0-e29, title:reference:c0-e31, startYear:reference:c0-e33, titleSecond:reference:c0-e35, title_phrase_like:reference:c0-e37, dispatch:reference:c0-e39, method:reference:c0-e41, selectType:reference:c0-e43, :reference:c0-e45, smcx_rd:reference:c0-e47}\n")
		.append("c0-param1=boolean:false\n")
		.append("batchId=7\n").toString();
		
		return body;
	}

}
