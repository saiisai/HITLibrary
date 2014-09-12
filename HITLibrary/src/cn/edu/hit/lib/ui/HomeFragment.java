package cn.edu.hit.lib.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.hit.lib.model.Book;
import cn.edu.hit.lib.query.Querier;
import cn.edu.hit.lib.query.Querier.onQueryFinishedListener;
import cn.edu.hit.lib.query.QueryResultFormatter;
import cn.edu.hit.lib.query.QueryType;
import cn.edu.hit.lib.query.QueryURLFormatter;

public class HomeFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
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
