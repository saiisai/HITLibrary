package cn.edu.hit.lib.ui;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.BaseAdapter;
import cn.edu.hit.lib.R;
import cn.edu.hit.lib.model.Book;
import cn.edu.hit.lib.query.Querier;
import cn.edu.hit.lib.query.Querier.onQueryFinishedListener;
import cn.edu.hit.lib.query.QueryResultFormatter;
import cn.edu.hit.lib.query.QueryType;
import cn.edu.hit.lib.query.QueryURLFormatter;
import cn.edu.hit.lib.ui.widget.BookList;
import cn.edu.hit.lib.ui.widget.BookList.OnScrollToEndListener;
import cn.edu.hit.lib.ui.widget.BookListItem;

@SuppressLint("InflateParams")
public class SearchFragment extends Fragment{
	public int page;
	public BookList mainList;
	public Querier queryBooks;
	public QueryURLFormatter queryParams;
	public ArrayList<BookListItem> booklist;
	public LayoutAnimationController animationController;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View mView = (View)inflater.inflate(R.layout.home_fragment, null);
		mainList = (BookList)mView.findViewById(R.id.mainList);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		queryParams = new QueryURLFormatter(QueryType.BOOKLIST);
		queryParams.setLimit(30);
		queryParams.title = "Java";
		loadBookList();

		animationController = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left_animation));  
		animationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
		animationController.setDelay((float) 0.4);
		booklist = new ArrayList<BookListItem>();
		mainList.setAdapter(new MainListViewAdapter(booklist));
		mainList.setOnScrollToEndListener(new OnScrollToEndListener() {
			
			@Override
			public void onPostExecute() {
				loadBookList();
				
			}
		});
	}
	
	public void loadBookList() {
		queryParams.setPage(page);
		queryBooks = new Querier(queryParams.format());
		queryBooks.setOnQueryFinishedListener(new onQueryFinishedListener() {
			
			@Override
			public void onFinished(String result) {
				if(result == null) return;
				QueryResultFormatter formatter = new QueryResultFormatter(QueryType.BOOKLIST, result);
				for(Book book : formatter.format()){
					booklist.add(new BookListItem(getActivity()).setTitle(book.title).setDescription(book.publish));
				}
				((MainListViewAdapter)mainList.getAdapter()).notifyDataSetChanged();
				page++;
				mainList.setPostExecuteFinished();
			}
		});
		queryBooks.execute();
	}
	
	public class MainListViewAdapter extends BaseAdapter{
		public ArrayList<BookListItem> items;
		
		public MainListViewAdapter(ArrayList<BookListItem> items){
			this.items = items;
		}

		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public BookListItem getItem(int position) {
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return items.get(position);
		}
		
	}
}
