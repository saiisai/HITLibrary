package cn.edu.hit.lib.ui.widget;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.hit.lib.R;

public class ActionBarItem extends LinearLayout{
	private ImageView ic;
	private TextView title;
	
	private int HEIGHT;
	private int MIN_WIDTH;
	private boolean isCurrent = false;
	
	public ActionBarItem(Context context, AttributeSet attrs) {
		super(context, attrs);

		int padding = getResources().getDimensionPixelSize(R.dimen.common_padding);
		int icScale = getResources().getDimensionPixelSize(R.dimen.ic_tab);
		int icMargin = getResources().getDimensionPixelSize(R.dimen.ic_tab_margin);
		HEIGHT = getResources().getDimensionPixelSize(R.dimen.actionbar_height);
		MIN_WIDTH = getResources().getDimensionPixelSize(R.dimen.actionbar_height);

		setLayoutParams(new LayoutParams(MIN_WIDTH, HEIGHT));
		setBackgroundResource(R.drawable.actionbar_item);
		setPadding(padding, padding, padding, padding);
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER);
		
		ic = new ImageView(context);
		ic.setLayoutParams(new LayoutParams(icScale, icScale));
		addView(ic);

		title = new TextView(context);
		title.setTextColor(getResources().getColor(R.color.text_actionbar));
		title.setPadding(icMargin, 0, 0, 0);
		title.setVisibility(GONE);
		title.setTextSize(13);
		title.setMaxLines(1);
		addView(title);

	}

	public ActionBarItem init(int text, int drawable){
		title.setText(text);
		ic.setImageResource(drawable);
		setTint(ic, getResources().getColor(R.color.fg_actionbar));
		return this;
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(!isCurrent){
			setBackgroundResource(R.color.bg_actionbar_hover);
		}
		return super.onTouchEvent(event);
	}

	public boolean isCurrent() {
		return isCurrent;
	}
	
	public void setCurrent() {
		if(!isCurrent){
			isCurrent = true;
			title.setVisibility(VISIBLE);
			setBackgroundResource(R.color.bg_actionbar_pressed);
			setTint(ic, getResources().getColor(R.color.fg_actionbar_pressed));
			setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, HEIGHT));
		}
	}
	
	public void clearCurrent() {
		if(isCurrent){
			isCurrent = false;
			title.setVisibility(GONE);
			setBackgroundResource(R.drawable.actionbar_item);
			setTint(ic, getResources().getColor(R.color.fg_actionbar));
			setLayoutParams(new LayoutParams(MIN_WIDTH, HEIGHT));
		}
	}
	
	public void setTint(ImageView view, int color) {
		Mode mode = Mode.SRC_ATOP;
		Drawable d = view.getDrawable();
		d.mutate().setColorFilter(color, mode);
		view.setImageDrawable(d);
	}
}
