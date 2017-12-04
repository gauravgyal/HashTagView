package com.gauravgoyal.hashtagtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gauravgoyal on 04/12/17.
 */

public class HashTagTextView extends AppCompatTextView {

    private TagClickListener tagClickListener;
    private int tagColor;
    boolean allowUnderline;

    public HashTagTextView(Context context) {
        super(context);
        init(context,null);
    }

    public HashTagTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HashTag,
                0, 0);
        try {
            tagColor = a.getInt(R.styleable.HashTag_tagTextColor, Color.BLACK);
            allowUnderline = a.getBoolean(R.styleable.HashTag_allowUnderline, false);
            setHashTagText(a.getString(R.styleable.HashTag_tagText));

        } catch (Exception e) {
            ;
        } finally {
            a.recycle();
        }

    }

    public SpannableString getCustomSpannableString(int tagColor, CharSequence text) {
        SpannableString hashtagintitle = new SpannableString(text);
        Matcher matcher = Pattern.compile("#([A-Za-z0-9_-]+)").matcher(text);
        while (matcher.find()) {
            hashtagintitle.setSpan(new TextClickableSpan(), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            hashtagintitle.setSpan(new ForegroundColorSpan(tagColor), matcher.start(), matcher.end(), 0);
        }
        setMovementMethod(LinkMovementMethod.getInstance());
        return hashtagintitle;
    }

    public void setHashTagText(int tagColor, CharSequence text) {
        setText(getCustomSpannableString(tagColor, text));
    }

    public void setHashTagText(CharSequence text) {
        setText(getCustomSpannableString(tagColor, text));
    }

    public interface TagClickListener {
        public void onTagClick();
    }

    public class TextClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            tagClickListener.onTagClick();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(allowUnderline);
        }
    }

    public void setOnTagListener(TagClickListener l) {
        this.tagClickListener = l;
    }

}
