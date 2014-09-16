package cn.edu.hit.lib.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import cn.edu.hit.lib.R;

public class SearchEditText extends EditText{
	public final static int TEXTSIZE = 14;
	public OnSendListener listener;

	public SearchEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setBackgroundResource(R.drawable.border_bottom);
		setHint(R.string.search_hint);
		setTextSize(TEXTSIZE);
		setSingleLine();
		setMaxLines(1);
		
		int paddingHorizontal = getResources().getDimensionPixelSize(R.dimen.common_padding_horizontal);
		int paddingVertical = getResources().getDimensionPixelSize(R.dimen.common_padding_medium);		
		setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);
		
		setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(KeyEvent.KEYCODE_ENTER == keyCode && event.getAction() == KeyEvent.ACTION_DOWN){
					if(listener != null){
						listener.onSend();
						return true;
					}
					return false;
				}
				return false;
			}
		});
	}
	
	public void setOnSendListener(OnSendListener listener) {
		this.listener = listener;
	}
	
	public interface OnSendListener{
		public void onSend();
	}

}
