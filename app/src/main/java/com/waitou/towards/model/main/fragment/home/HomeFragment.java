package com.waitou.towards.model.main.fragment.home;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.waitou.towards.BR;
import com.waitou.towards.R;
import com.waitou.towards.databinding.IncludeViewPagerBinding;
import com.waitou.towards.databinding.ViewSmartLayoutBinding;
import com.waitou.wt_library.base.XFragment;
import com.waitou.wt_library.base.XFragmentAdapter;


/**
 * Created by waitou on 16/12/23.
 * 首页
 */

public class HomeFragment extends XFragment<HomePresenter, IncludeViewPagerBinding> implements SmartTabLayout.TabProvider {

    private ViewSmartLayoutBinding mLayoutBinding;

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.include_view_pager;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initToolBar();
        XFragmentAdapter adapter = new XFragmentAdapter(getChildFragmentManager(), getP().getHomeCommendFragment(), getP().getCargoFragment(), getP().getHomeAndroidFragment());
        getBinding().setAdapter(adapter);
        mLayoutBinding.setViewPager(getBinding().viewPager);
    }

    @Override
    public void reloadData() {
    }

    public ViewDataBinding initToolBar() {
        if (mLayoutBinding == null) {
            mLayoutBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_smart_layout, null, false);
            mLayoutBinding.setProvider(this);
        }
        return mLayoutBinding;
    }

    public ViewDataBinding getHomeToolbar() {
        return initToolBar();
    }

    @Override
    public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
        ViewDataBinding viewDataBinding = bindingInflate(R.layout.toolbar_home_title, null);
        viewDataBinding.setVariable(BR.position, position);
        viewDataBinding.setVariable(BR.colorId, R.color.skin_tab_icon_not);
        View root = viewDataBinding.getRoot();
        root.setLayoutParams(new ViewGroup.LayoutParams(SizeUtils.dp2px(40), SizeUtils.dp2px(40)));
        return root;
    }
}
