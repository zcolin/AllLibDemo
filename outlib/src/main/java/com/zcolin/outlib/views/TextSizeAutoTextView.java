/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     17-3-3 下午2:47
 * ********************************************************
 */

/*    
 * 
 * @author		: WangLin  
 * @Company: 	：FCBN
 * @date		: 2015年5月23日 
 * @version 	: V1.0
 */
package com.zcolin.outlib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 可根据字符串所占的长度（非字符串长度）自动缩小字体大小， 以适应显示区域的宽度
 */
public class TextSizeAutoTextView extends TextView
{

	private boolean	isSet;

	public TextSizeAutoTextView (Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		refitText();
	}

	private void refitText()
	{
		int width = getWidth();
		if (width > 0 && !isSet)
		{
			isSet = true;
			String text = getText().toString();
			Paint paint = getPaint();
			
			// 获得当前TextView的有效宽度
			int availableWidth = width - this.getPaddingLeft() - this.getPaddingRight();
			int textSize = (int) getTextSize();// 这个返回的单位为px
			int curTextSize = textSize;
			paint.setTextSize(textSize);
			int textWidths = (int) paint.measureText(text);
			while (textWidths > availableWidth)
			{
				curTextSize = curTextSize - 1;
				paint.setTextSize(curTextSize);// 这里传入的单位是px
				textWidths = (int) paint.measureText(text);
			}

			if (curTextSize != textSize)
			{
				this.setTextSize(TypedValue.COMPLEX_UNIT_PX, curTextSize);// 这里制定传入的单位是px
				this.setText(getText());
			}
		}
	};

}
