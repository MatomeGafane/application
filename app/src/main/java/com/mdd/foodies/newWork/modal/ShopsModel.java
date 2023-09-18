package com.mdd.foodies.newWork.modal;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopsModel {
    Bitmap img;
    String textView;

    public ShopsModel(Bitmap img, String textView) {
        this.img = img;
        this.textView = textView;
    }

    public Bitmap getImg() {
        return img;
    }

    public String getTextView() {
        return textView;
    }
}
