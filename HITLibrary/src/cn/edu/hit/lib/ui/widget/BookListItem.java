package cn.edu.hit.lib.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.hit.lib.R;

public class BookListItem extends LinearLayout{
	public final static int TEXTSIZE_LARGE = 14;
	public final static int TEXTSIZE_SMALL = 12;
	public TextView tvTitle;
	public TextView tvDescription;
	
	public BookListItem(Context context){
		this(context, null);
	}

	public BookListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		int paddingHorizontal = getResources().getDimensionPixelSize(R.dimen.common_padding_horizontal);
		int paddingVertical = getResources().getDimensionPixelSize(R.dimen.common_padding_vertical);
		int margin = getResources().getDimensionPixelSize(R.dimen.list_item_margin);
		setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
		setOrientation(VERTICAL);
		
		tvTitle = new TextView(context);
		tvTitle.setTextSize(TEXTSIZE_LARGE);
		tvTitle.setTextColor(getResources().getColor(R.color.text_list_title));
		addView(tvTitle);
		
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(0, margin, 0, 0);
		tvDescription = new TextView(context);
		tvDescription.setTextSize(TEXTSIZE_SMALL);
		tvDescription.setLayoutParams(params);
		tvDescription.setTextColor(getResources().getColor(R.color.text_list_description));
		addView(tvDescription);
	}
	
	public BookListItem setTitle(String title) {
		tvTitle.setText(title);
		return this;
	}
	
	public BookListItem setDescription(String descr) {
		tvDescription.setText(descr);
		return this;
	}

}
