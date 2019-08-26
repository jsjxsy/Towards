package com.waitou.towards.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;

import com.to.aboomy.statusbar_lib.StatusBarUtil;
import com.to.aboomy.theme_lib.compat.SkinCompatSupportable;
import com.waitou.towards.R;

/**
 * Created by waitou on 17/3/5.
 */

public class SkinDrawerLayout extends DrawerLayout implements SkinCompatSupportable {

    public SkinDrawerLayout(Context context) {
        this(context, null);
    }

    public SkinDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void applySkin() {
        StatusBarUtil.drawerLayoutForColor((Activity) getContext(), ContextCompat.getColor(getContext(), R.color.bg_grey), this);
    }
}
