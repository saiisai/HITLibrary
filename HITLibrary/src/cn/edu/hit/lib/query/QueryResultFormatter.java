package cn.edu.hit.lib.query;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.util.Log;
import cn.edu.hit.lib.model.Book;
import cn.edu.hit.lib.util.UnicodeToString;

public class QueryResultFormatter {
	public QueryType type;
	public String result;
	
	public QueryResultFormatter(QueryType type, String result){
		this.type = type;
		this.result = result;
	}
	
	public ArrayList<Book> format(){
		ArrayList<Book> books = new ArrayList<Book>();
		result = result.replaceAll("\\\\\"", "\\\"");
		Document document = Jsoup.parse(result);
		Elements elements = document.getElementsByClass("tableBody");
		for (int i = 0; i < elements.size(); i++) {
			Elements tr = elements.get(i).select("tr");
			for (int j = 0; j < tr.size(); j++) {
				//Init a new book
				Book book = new Book();
				Elements td = tr.get(j).select("td");
				for (int k = 0; k < td.size(); k++) {
					switch (k) {
						case 0:
							book.title = UnicodeToString.convert(td.get(k).text());
							break;
						case 1:
							book.author = UnicodeToString.convert(td.get(k).text());
							break;
						case 2:
							book.publish = UnicodeToString.convert(td.get(k).text());
							break;
						case 3:
							book.year = UnicodeToString.convert(td.get(k).text());
							break;
						case 4:
							book.page = UnicodeToString.convert(td.get(k).text());
							break;
						case 5:
							book.callNo = UnicodeToString.convert(td.get(k).text());
							break;
						case 6: //Last column of a row, get book's ID
							Elements a = td.get(k).select("a");
							if(a.size() > 0){
								String ID_raw = a.get(0).attr("onclick");
								Pattern pattern = Pattern.compile("showDetail\\(\\\\\'([\\d]+)\\\\\'");
								Matcher matcher = pattern.matcher(ID_raw);
								if(matcher.find()){
									book.id = Long.parseLong(matcher.group(1));
								}else{
									Log.e("ID", "Invalid");
								}
							}
							break;
	
						default:
							break;
					}
				}
				if(book.id > 0)
					books.add(book);
			}
		}
		return books;
	}

}
