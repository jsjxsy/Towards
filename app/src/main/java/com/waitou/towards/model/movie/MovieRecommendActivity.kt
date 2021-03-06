package com.waitou.towards.model.movie


/**
 * Created by waitou on 17/5/25.
 * 影视推荐
 */
//class MovieRecommendActivity : XActivity<MovieTelevisionPresenter, ActivityMovieBinding>() {
//
//    private var adapter: MultiTypeAdapter<Displayable>? = null
//    private var movieResInfo: MovieResInfo? = null
//
//    override fun createPresenter(): MovieTelevisionPresenter {
//        return MovieTelevisionPresenter()
//    }
//
//    override fun getContentViewId(): Int {
//        return R.layout.activity_movie
//    }
//
//    override fun afterCreate(savedInstanceState: Bundle?) {
//        bar?.initializeHeader("影视精选")
//        bar?.setRightText("专题") {
//            if (movieResInfo != null) {
//                Router.newIntent()
//                        .from(this)
//                        .to(MovieProjectActivity::class.java)
//                        .putSerializable(Values.MOVIE_PROJECT, movieResInfo)
//                        .launch()
//            }
//        }
//        (xBinding.xContentLayout.layoutParams as CoordinatorLayout.LayoutParams).behavior = null
//        xBinding.toolbar.layoutParams.height = SizeUtils.dp2px(73f)
//        xBinding.toolbar.setPadding(0, SizeUtils.dp2px(25f), 0, 0)
//        xBinding.toolbar.visibility = View.GONE
//        adapter = MultiTypeAdapter<Displayable>(this)
//        adapter!!.addViewTypeToLayoutMap(0, R.layout.item_banner_search)
//        adapter!!.addViewTypeToLayoutMap(1, R.layout.item_movie_title)
//        adapter!!.addViewTypeToLayoutMap(2, R.layout.item_movie_piture)
//        binding.manager = LayoutManagerUtil.getVerticalLayoutManager(this)!!
//        binding.adapter = adapter
//        binding.xList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            private val argbEvaluator = ArgbEvaluator()
//            private val height = SizeUtils.dp2px(175f)
//            private val startColor = 0x00ffffff
//            private val endColor = ThemeUtils.getThemeAttrColor(this@MovieRecommendActivity, R.attr.colorPrimary)
//            private var mDy = 0
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                mDy += dy
//                val parent = bar.parent as View
//                if (mDy < height) {
//                    val alpha = mDy * 1.0f / height
//                    val color = argbEvaluator.evaluate(alpha, startColor, endColor) as Int
//                    parent.setBackgroundColor(color)
//                    parent.visibility = if (alpha > 0) View.VISIBLE else View.GONE
//                    xBinding.toolbar.alpha = alpha
//                } else {
//                    parent.setBackgroundColor(endColor)
//                    xBinding.toolbar.alpha = 1.0f
//                }
//            }
//        })
//        reloadData()
//    }
//
////    inline fun <T> with(t: T, body: T.() -> Unit) {
////        t.body()
////    }
//
//    override fun reloadData() {
//        p.start()
//    }
//
//    fun onSuccess(it: MovieResInfo?) {
//        movieResInfo = it
//        adapter!!.clear()
//        showContent()
//        val typeList = it!!.list
//        for (info in typeList!!) {
//            if (info.showType == "banner") {
//                val bannerList = info.childList
//                bannerList!!.filter { it.loadType != "video" }
//                        .forEach { bannerList.remove(it) }
//                val bannerAdapter = SingleViewPagerAdapter<MovieInfo>(this, bannerList, R.layout.item_banner_image_movie)
//                bannerAdapter.setPresenter(p)
//                val qyIndicator = Indicator(this)
//                        .setGravity(Gravity.CENTER)
//                        .setIndicatorInColor(ThemeUtils.getThemeAttrColor(this, R.attr.colorPrimary))
//                adapter!!.add(0, BannerAdapterInfo(bannerAdapter,qyIndicator), 0)
//            } else if (info.showType == "IN") {
//                adapter!!.add(TitleInfo(info.title!!), 1)
//                val movieList = info.childList
//                adapter!!.addAll(movieList, 2)
//            }
//        }
//    }
//}