package cn.edu.hit.lib.model;

import cn.edu.hit.lib.R;
import android.content.Context;

public class ColorSet{
	private Context context;

	public enum ColorType {
		DEFAULT, PRIMARY, SUCCESS, INFO, WARNING, DANGER;
	}
	
	public ColorSet(Context context){
		this.context = context;
	}

	public int getColor(ColorType type){
		int result = 0;
		switch (type) {
			case DEFAULT:
				result = context.getResources().getColor(R.color.colorset_default);
				break;
			case PRIMARY:
				result = context.getResources().getColor(R.color.colorset_primary);
				break;
			case SUCCESS:
				result = context.getResources().getColor(R.color.colorset_success);
				break;
			case INFO:
				result = context.getResources().getColor(R.color.colorset_info);
				break;
			case WARNING:
				result = context.getResources().getColor(R.color.colorset_warning);
				break;
			case DANGER:
				result = context.getResources().getColor(R.color.colorset_danger);
				break;
	
			default:
				result = context.getResources().getColor(R.color.colorset_default);
				break;
		}
		return result;
	}
}
