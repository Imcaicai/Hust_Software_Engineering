package com.example.library.view.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
//import android.support.v4.widget.EdgeEffectCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.library.R;

public class Editor extends LinearLayout {
    EditText et;
    private Drawable bDrawable, lDrawable;
    private Drawable afterDrawable;
    private Drawable beforeDrawable;
    private ImageView etd;
    private TextView etBt;
    private RelativeLayout etl;

    public Editor(Context context){
        super(context);
    }

    @SuppressLint({"Recycle", "SetTextI18n", "UseCompatLoadingForDrawables"})
    public Editor(Context context, AttributeSet attrs){ //使用AttributeSet自定义控件
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.editor_btn,this,true);
        init();


    }

    private void init(){
        et = findViewById(R.id.et);
        etd = findViewById(R.id.etDrawable);
        etl = findViewById(R.id.elt);
        etBt = findViewById(R.id.etBt);
    }

    public void setBtDrawable(Drawable drawable){
        etBt.setBackground(drawable);
    }

    public String getBtText(){
        return etBt.getText() + "";
    }

    public void setBtText(String text){
        etBt.setText(text);
    }

    public void setBtOnClick(OnClickListener click){
        etBt.setOnClickListener(click);
    }

    public void setBtEnabled(boolean enabled){
        etBt.setEnabled(enabled);
    }

    public EditText getEditor(){
        return et;
    }

    public void setLeftDrawable(int resource){
        @SuppressLint("UseCompatLoadingForDrawables")Drawable drawable = getResources().getDrawable(resource);
        setLeftDrawable(drawable);
    }

    public void setHint(String text){
        et.setHint(text);
    }

    public void setLeftDrawable(Drawable drawable){
        etd.setImageDrawable(drawable);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setLeftDrawableAfterFocus(int resource){
        afterDrawable = getResources().getDrawable(resource);
        bindAfterDrawable();
    }

    public void setLeftDrawableAfterFocus(Drawable drawable) {
        afterDrawable = drawable;
        bindAfterDrawable();
    }

    public Editable getText(){
        return et.getText();
    }

    public void setText(String text){
        et.setText(text);
    }

    private void bindAfterDrawable(){
        et.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                etl.setBackground(bDrawable);
                if (afterDrawable != null) {
                    afterDrawable.setTint(getResources().getColor(R.color.color1));
                    setLeftDrawable(afterDrawable);
                } else if (beforeDrawable != null) {
                    beforeDrawable.setTint(getResources().getColor(R.color.color1));
                    setLeftDrawable(beforeDrawable);
                }
            } else {
                etl.setBackground(lDrawable);
                if (beforeDrawable != null) {
                    beforeDrawable.setTint(getResources().getColor(R.color.color2));
                    setLeftDrawable(beforeDrawable);
                }
            }

        });
    }

}
