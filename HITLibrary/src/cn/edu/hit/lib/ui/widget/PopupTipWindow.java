package cn.edu.hit.lib.ui.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.edu.hit.lib.R;
import cn.edu.hit.lib.model.ColorSet;
import cn.edu.hit.lib.model.ColorSet.ColorType;

public class PopupTipWindow extends PopupWindow{
	public final static int TEXTSIZE = 14;
	public final static int TEXTSIZE_LARGE = 16;
	public final static String close = "¡Á";

	public PopupTipWindow(Context context, String text) {
		this(context, ColorType.DEFAULT, text);
	}
	
	public PopupTipWindow(Context context, ColorType type, String text) {
		LinearLayout mView = new LinearLayout(context);
		mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		mView.setBackgroundColor(new ColorSet(context).getColor(type));
		mView.setOrientation(LinearLayout.HORIZONTAL);
		mView.setGravity(Gravity.CENTER_VERTICAL);
		
		int paddingHorizontal = context.getResources().getDimensionPixelSize(R.dimen.common_padding_horizontal);
		int paddingVertical = context.getResources().getDimensionPixelSize(R.dimen.common_padding_vertical);		
		mView.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);

		TextView tv = new TextView(context);
		tv.setTextColor(context.getResources().getColor(R.color.text_light));
		tv.setTextSize(TEXTSIZE);
		tv.setText(text);
		mView.addView(tv);
		
		LinearLayout rightLayout = new LinearLayout(context);
		rightLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		rightLayout.setGravity(Gravity.RIGHT);
		
		TextView btnClose = new TextView(context);
		btnClose.setTextColor(context.getResources().getColor(R.color.text_light));
		btnClose.setTextSize(TEXTSIZE_LARGE);
		btnClose.setText(close);
		
		rightLayout.addView(btnClose);
		mView.addView(rightLayout);
		
		btnClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		setContentView(mView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setAnimationStyle(R.style.FadeTransAnimation);
		setOutsideTouchable(true);
		update();
	}
	
	public void show(View parent){
		showAtLocation(parent, Gravity.BOTTOM, 0, 0);
	}
}
