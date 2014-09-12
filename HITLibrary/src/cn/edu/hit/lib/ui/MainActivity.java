package cn.edu.hit.lib.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import cn.edu.hit.lib.R;
import cn.edu.hit.lib.model.Book;
import cn.edu.hit.lib.query.Querier;
import cn.edu.hit.lib.query.Querier.onQueryFinishedListener;
import cn.edu.hit.lib.query.QueryResultFormatter;
import cn.edu.hit.lib.query.QueryType;
import cn.edu.hit.lib.query.QueryURLFormatter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final QueryURLFormatter queryParams = new QueryURLFormatter(QueryType.BOOKLIST);
		queryParams.setPage(2);
		queryParams.setLimit(20);
		queryParams.title = "Java";

		Querier queryBooks = new Querier(queryParams.format());
		queryBooks.setOnQueryFinishedListener(new onQueryFinishedListener() {
			
			@Override
			public void onFinished(String result) {
				if(result == null) return;
				QueryResultFormatter formatter = new QueryResultFormatter(QueryType.BOOKLIST, result);
				int i = 0;
				for(Book book : formatter.format()){
					i++;
					Log.e(String.valueOf(i), book.toString());
				}
			}
		});
		queryBooks.execute();
	}
	

}
