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
	
	private int height;
	private int minWidth;
	private boolean isCurrent = false;
	
	public ActionBarItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		int padding = getResources().getDimensionPixelSize(R.dimen.tab_padding);
		int icScale = getResources().getDimensionPixelSize(R.dimen.ic_tab);
		int icMargin = getResources().getDimensionPixelSize(R.dimen.ic_tab_margin);
		height = getResources().getDimensionPixelSize(R.dimen.actionbar_height);
		minWidth = padding*2 + icScale;

		setLayoutParams(new LayoutParams(minWidth, height));
		setBackgroundResource(R.drawable.actionbar_item);
		setPadding(padding, padding, padding, padding);
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);
		
		ic = new ImageView(context);
		ic.setLayoutParams(new LayoutParams(icScale, icScale));
		addView(ic);

		title = new TextView(context);
		title.setTextColor(getResources().getColor(R.color.text_actionbar));
		title.setPadding(icMargin, 0, 0, 0);
		title.setTextSize(13);
		title.setMaxLines(1);
		addView(title);

	}

	public ActionBarItem init(int text, int drawable){
		title.setText(text);
		ic.setImageResource(drawable);
		setTint(ic, getResources().getColor(R.color.text_actionbar));
		return this;
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(!isCurrent){
			setBackgroundResource(R.color.bg_actionbar_hover);
		}
		if(event.getAction() == MotionEvent.ACTION_UP){
			performClick();
		}
		return super.onTouchEvent(event);
	}

	public boolean isCurrent() {
		return isCurrent;
	}
	
	public void setCurrent() {
		if(!isCurrent){
			isCurrent = true;
			setBackgroundResource(R.color.bg_actionbar_pressed);
			title.setTextColor(getResources().getColor(R.color.text_actionbar_pressed));
			setTint(ic, getResources().getColor(R.color.text_actionbar_pressed));
			setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, height));
		}
	}
	
	public void clearCurrent() {
		if(isCurrent){
			isCurrent = false;
			setBackgroundResource(R.drawable.actionbar_item);
			title.setTextColor(getResources().getColor(R.color.text_actionbar));
			setTint(ic, getResources().getColor(R.color.text_actionbar));
			setLayoutParams(new LayoutParams(minWidth, height));
		}
	}
	
	public void setTint(ImageView view, int color) {
		Mode mode = Mode.SRC_ATOP;
		Drawable d = view.getDrawable();
		d.mutate().setColorFilter(color, mode);
		view.setImageDrawable(d);
	}
}
