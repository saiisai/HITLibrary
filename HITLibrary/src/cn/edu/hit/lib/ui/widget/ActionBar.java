package cn.edu.hit.lib.ui.widget;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import cn.edu.hit.lib.R;

public class ActionBar extends LinearLayout{
	
	private ArrayList<ActionBarItem> items;

	public ActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		final int height = getResources().getDimensionPixelSize(R.dimen.actionbar_height);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
		setBackgroundResource(R.color.bg_actionbar);
		setGravity(Gravity.CENTER_VERTICAL);
		setOrientation(HORIZONTAL);
		setVisibility(GONE);

		items = new ArrayList<ActionBarItem>();
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if(items.size() > 0) setCurrentItem(0);
				setVisibility(VISIBLE);
				TranslateAnimation anim = new TranslateAnimation(0, 0, -height, 0);
				anim.setDuration(500);
				startAnimation(anim);
			}
		}, 100);
	}
	
	public void addItem(ActionBarItem item){
		items.add(item);
		item.clearCurrent();
		addView(item);
	}
	
	public void setCurrentItem(int position){
		try {
			for (int i = 0; i < items.size(); i++) {
				if(i != position)
					items.get(i).clearCurrent();
			}
			items.get(position).setCurrent();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public void setOnItemClickListener(final onItemClickListener listener){
		for (int i = 0; i < items.size(); i++) {
			final ActionBarItem item = items.get(i);
			if(item != null){
				item.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						ActionBarItem view = (ActionBarItem)v;
						int position = items.indexOf(view);
						setCurrentItem(position);
						listener.onItemClick(view, position);
					}
				});
			}
		}
	}
	
	public void removeItem(int index) {
		removeView(items.get(index));
		items.remove(index);
	}
	
	public interface onItemClickListener{
		public void onItemClick(ActionBarItem view, int position);
	}

}
