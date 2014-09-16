package cn.edu.hit.lib.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import cn.edu.hit.lib.R;

public class BookList extends ListView{
	public OnScrollToEndListener listener;
	public int lastBottomItemIndex;
	public boolean scrollPostExecuteFinished = false;

	public BookList(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSelector(R.drawable.listview_item);		
	}
	
	public BookList setOnScrollToEndListener(OnScrollToEndListener listener){
		this.listener = listener;
		return this;
	}
	
	public void setPostExecuteFinished() {
		this.scrollPostExecuteFinished = true;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if(scrollPostExecuteFinished && listener != null && 
				(getLastVisiblePosition() == getAdapter().getCount() - 1)){
			this.scrollPostExecuteFinished = false;
			listener.onPostExecute();
		}
		if(getAdapter() != null && getAdapter().getCount() > 0 && getLastVisiblePosition() > lastBottomItemIndex){
			((BookListItem)getAdapter().getItem(getLastVisiblePosition())).startAnimation(
					AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_animation)
				);
		}
		lastBottomItemIndex = getLastVisiblePosition();
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public interface OnScrollToEndListener{
		public void onPostExecute();
	}

}
