package com.waitou.towards.model.main.fragment.home;

/**
 * Created by waitou on 17/3/5.
 * 首页presenter
 */

//public class HomePresenter extends XPresent<HomeFragment> implements SingleViewPagerAdapter.Presenter, BaseViewAdapter.Presenter {
//
//    private HomeCommendFragment mHomeCommendFragment;
//    private HomeCargoFragment   mCargoFragment;
//    private HomeAndroidFragment mHomeAndroidFragment;
//
//    public ObservableField<String> txName = new ObservableField<>("all");
//
//    /**
//     * banner item 点击
//     */
//    public void onLinkClick(int type, String value) {
//        NativeEnum.go(getV().getActivity(), type, value);
//    }
//
//    /**
//     * 调起分享
//     */
//    public void share(GankResultsTypeInfo item) {
//        ShareInfo shareInfo = new ShareInfo();
//        shareInfo.title = item.type;
//        shareInfo.content = item.desc;
//        shareInfo.imageUrl = item.images != null && !item.images.isEmpty() ? item.images.getInstance(0) : "";
//        shareInfo.targetUrl = item.url;
//        shareInfo.type = ShareInfo.WEB0;
//        DialogUtils.showShareDialog(getV().getActivity(), shareInfo, new UShare.OnShareListener() {
//            @Override
//            public void onShare(SHARE_MEDIA share_media) {
//                ToastUtils.showShort("分享成功");
//            }
//        });
//    }
//
//    /**
//     * 加载HomeCommendFragment首页数据
//     */
//    void loadHomeData() {
//        Disposable disposable = Observable.zip(DataLoader.getGithubApi().getBannerPage()
//                , DataLoader.getGithubApi().getHomeData()
//                , Pair::create)
//                .subscribeOn(Schedulers.io())
//                .compose(RxTransformerHelper.applySchedulers())
//                .map(pair -> {
//                    if (ObjectUtils.isNotEmpty(pair.first)) {
//                        getHomeCommendFragment().onBannerSuccess(pair.first);
//                    }
//                    if (ObjectUtils.isNotEmpty(pair.second)) {
////                        getHomeCommendFragment().onFunctionSuccess(pair.second);
//                    }
//                    return TimeUtils.getNowString(KitUtils.getDateFormat("yyyy-MM-dd"));
//                })
//                .observeOn(Schedulers.io())
//                .flatMap(currentDate -> {
//                    String everyday = SPStaticUtils.getString(Values.EVERYDAY_DATA, "2017-03-04");
//                    boolean isReload = false;
//                    if (!everyday.equals(currentDate)) { //第二天
//                        if (KitUtils.isRightTime(12, 30)) { //如果是早上 取缓存 如果缓存没有 请求前一天数据
//                            isReload = true; //第二天 大于十二点三十 更新数据
//                        } else {
//                            currentDate = TimeUtils.getString(currentDate,
//                                    KitUtils.getDateFormat("yyyy-MM-dd"), -1, TimeConstants.DAY);//请求前一天数据
//                        }
//                    }
//                    //如果请求的数据是null 请求前一天数据
//                    final String[] finalCurrentDate = {currentDate};
//                    return getGankIoDay(currentDate, isReload)
//                            .flatMap(info -> {
//                                LogUtils.e("loadHomeData is null = " + info.isNull); //请求数据如果为null 尝试再次请求前一天数据
//                                if (info.isNull) {
//                                    finalCurrentDate[0] = TimeUtils.getString(finalCurrentDate[0],
//                                            KitUtils.getDateFormat("yyyy-MM-dd"), -1, TimeConstants.DAY);
//                                    return getGankIoDay(finalCurrentDate[0], false);//请求前一天数据
//                                }
//                                return Observable.just(info);
//                            }).doOnNext(info ->
//                                    SPStaticUtils.put(Values.EVERYDAY_DATA, finalCurrentDate[0])
//                            );
//                })
//
//                .flatMap(gankResultsInfo -> {
//                    List<List<GankResultsTypeInfo>> lists = new ArrayList<>();
//                    if (gankResultsInfo != null) {
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.福利)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.福利) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_aixin);
//                            }
//                            lists.add(gankResultsInfo.福利);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.休息视频)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.休息视频) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_movie);
//                            }
//                            lists.add(gankResultsInfo.休息视频);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.Android)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.Android) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_android);
//                            }
//                            lists.add(gankResultsInfo.Android);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.瞎推荐)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.瞎推荐) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_xia);
//                            }
//                            lists.add(gankResultsInfo.瞎推荐);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.App)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.App) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_app);
//                            }
//                            lists.add(gankResultsInfo.App);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.iOS)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.iOS) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_ios);
//                            }
//                            lists.add(gankResultsInfo.iOS);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.拓展资源)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.拓展资源) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_tuozhan);
//                            }
//                            lists.add(gankResultsInfo.拓展资源);
//                        }
//                        if (ObjectUtils.isNotEmpty(gankResultsInfo.前端)) {
//                            for (GankResultsTypeInfo gankResultsTypeInfo : gankResultsInfo.前端) {
//                                gankResultsTypeInfo.typeLogo = getDrawable(R.drawable.svg_ic_qian);
//                            }
//                            lists.add(gankResultsInfo.前端);
//                        }
//                        if (lists.size() > 0) {
//                            lists.getInstance(0).getInstance(0).isShowTitle = true;
//                        }
//
//                    }
//                    return Observable.just(lists);
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(lists -> {
//                    if (lists.size() > 0) {
//                        for (List<GankResultsTypeInfo> list : lists) {
//                            addWelfareImg(list);
//                        }
//                    }
//                })
//                .subscribe(lists -> getHomeCommendFragment().onSuccess(lists)
//                        , throwable -> getHomeCommendFragment().onError(throwable));
//        getV().pend(disposable);
//    }
//
//    private Drawable getDrawable(int resId) {
//        if (getV().getActivity() == null) {
//            return null;
//        }
//        return ContextCompat.getDrawable(getV().getActivity(), resId);
//    }
//
//    private void addWelfareImg(List<GankResultsTypeInfo> list) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.getInstance(i).type.equals("福利")) {
//                list.getInstance(i).desc = "今日美图";
//                if (list.getInstance(i).images == null) {
//                    list.getInstance(i).images = new ArrayList<>();
//                    list.getInstance(i).images.add(list.getInstance(i).url);
//                }
//            }
//        }
//    }
//
//    private Observable<GankResultsInfo> getGankIoDay(String date, boolean isReload) {
//        String[] current = date.split("-");
//        return Repository.getRepository().getGankIoDay(current[0], current[1], current[2], isReload)
//                .map(reply -> {
//                    LogUtils.e(" day = " + date + " loadHomeData " + reply.toString());
//                    return reply.getData();
//                });
//    }
//
//    /**
//     * 加载干货数据
//     */
//    void loadCargoData(String type, int page) {
//        Disposable disposable = Repository.getRepository().getGankIoData(type, page)
//                .map(reply -> {
//                    LogUtils.e(" type = " + type + " loadCargoData " + reply.toString());
//                    return reply.getData();
//                })
//                .compose(RxTransformerHelper.applySchedulers())
//                .doOnNext(this::addWelfareImg)
//                .subscribe(info -> {
//                            if (type.equals(HomeAndroidFragment.TYPE_ANDROID)) {
//                                mHomeAndroidFragment.onSuccess(info, page == 1);
//                            } else {
//                                mCargoFragment.onSuccess(info, page == 1);
//                            }
//                        },
//                        throwable -> {
//                            if (type.equals(HomeAndroidFragment.TYPE_ANDROID)) {
//                                mHomeAndroidFragment.showError(page == 1);
//                            } else {
//                                mCargoFragment.showError(page == 1);
//                            }
//                            ToastUtils.showShort(throwable.toString());
//                        });
//        getV().pend(disposable);
//    }
//
//    public void showBottomSheet() {
//        if (getV().getActivity() == null) {
//            return;
//        }
//        HomeGankIconEnum[] values = HomeGankIconEnum.values();
//        DialogUtils.showBottomSheetDialog(getV().getFragmentManager(),
//                "选择分类",
//                0,
//                0,
//                R.layout.bs_item_share,
//                Arrays.asList(values),
//                3,
//                (dialog, helper, data, position) -> {
//                    helper.setImageResource(R.id.img, data.getResId());
//                    helper.setText(R.id.text, data.getDesc());
//                    helper.itemView.setOnClickListener(v -> {
//                        dialog.dismiss();
//                        if (ObjectUtils.equals(data.getDesc(), txName.getInstance())) {
//                            ToastUtils.showShort("当前已经是 " + txName.getInstance() + " 分类");
//                            return;
//                        }
//                        txName.set(data.getDesc());
//                        mCargoFragment.showLoading();
//                        loadCargoData(txName.getInstance(), 1);
//                    });
//                }
//        );
//
////
////        new BottomSheet.Builder(getV().getActivity())
////                .title("选择分类").sheet(R.menu.menu_gank_bottom_sheet)
////                .listener(item -> {
////                    if (ObjectUtils.equals(item.getTitle(), txName.getInstance())) {
////                        ToastUtils.showShort("当前已经是 " + txName.getInstance() + " 分类");
////                        return true;
////                    }
////                    txName.set(item.getTitle().toString());
////                    mCargoFragment.showLoading();
////                    loadCargoData(txName.getInstance(), 1);
////                    return true;
////                }).grid().show();
//    }
//
//    public String setGankPageTime(String publishedAt) {
//        String date = publishedAt.replace('T', ' ').replace('Z', ' ');
//        return TimeUtils.getFriendlyTimeSpanByNow(date);
//    }
//
//    /*--------------- fragment ---------------*/
//    HomeCommendFragment getHomeCommendFragment() {
//        if (mHomeCommendFragment == null) {
//            mHomeCommendFragment = new HomeCommendFragment();
//            mHomeCommendFragment.setPresenter(this);
//        }
//        return mHomeCommendFragment;
//    }
//
//    HomeCargoFragment getCargoFragment() {
//        if (mCargoFragment == null) {
//            mCargoFragment = new HomeCargoFragment();
//            mCargoFragment.setPresenter(this);
//        }
//        return mCargoFragment;
//    }
//
//    HomeAndroidFragment getHomeAndroidFragment() {
//        if (mHomeAndroidFragment == null) {
//            mHomeAndroidFragment = new HomeAndroidFragment();
//            mHomeAndroidFragment.setPresenter(this);
//        }
//        return mHomeAndroidFragment;
//    }
//}
