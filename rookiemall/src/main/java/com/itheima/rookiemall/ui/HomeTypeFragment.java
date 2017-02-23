package com.itheima.rookiemall.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;
import com.itheima.rookiemall.R;
import com.itheima.rookiemall.base.BaseFragment;
import com.itheima.rookiemall.bean.HomeBannerBean;
import com.itheima.rookiemall.bean.HomeHotBean;
import com.itheima.rookiemall.config.Urls;
import com.itheima.rookiemall.utils.L;
import com.itheima.rookiemall.widget.CustomToolBar;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.itheima.recycler.adapter.BaseRecyclerAdapter;
import org.itheima.recycler.header.RecyclerViewHeader;
import org.itheima.recycler.viewholder.BaseRecyclerViewHolder;
import org.itheima.recycler.widget.ItheimaRecyclerView;
import org.itheima.recycler.widget.PullToLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;
import retrofit2.Call;

/**
 * 分类Fragment
 * Created by lyl on 2016/9/25.
 */

public class HomeTypeFragment extends BaseFragment {


    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.left_recycler_view)
    ItheimaRecyclerView mLeftRecyclerView;

    @BindView(R.id.right_recycler_view)
    ItheimaRecyclerView mRightRecyclerView;

    @BindView(R.id.recycler_view_header)
    RecyclerViewHeader mRecyclerViewHeader;


    @BindView(R.id.slider_layout)
    SliderLayout mSliderLayout;

    Call mCall;
    private BaseRecyclerAdapter mLeftAdapter;
    private PullToLoadMoreRecyclerView mPullToLoadMoreRecyclerView;

    @Override
    protected void initToolbar(CustomToolBar toolBar) {
        super.initToolbar(toolBar);
        toolBar.setTitle("分类");
    }

    @Override
    public int getContentViewId() {
        return R.layout.home_type_fragment;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void initView() {
        mRecyclerViewHeader.attachTo(mRightRecyclerView);
        mPullToLoadMoreRecyclerView = new PullToLoadMoreRecyclerView<HomeHotBean>(mSwipeRefreshLayout, mRightRecyclerView, MyRightViewHolder.class) {
            @Override
            public int getItemResId() {
                return R.layout.item_hometype_right_recylerview;
            }

            @Override
            public String getApi() {
                return Urls.api_hometype_right_lists;
            }
        };

        //当前页码的key
        mPullToLoadMoreRecyclerView.setCurPageKey("curPage");
        //每一页数据条数Key
        mPullToLoadMoreRecyclerView.setPageSizeKey("pageSize");
        //每一页数据条数
        mPullToLoadMoreRecyclerView.setPageSize(20);

//        mPullToLoadMoreRecyclerView.setLoadingDataListener(new PullToLoadMoreRecyclerView.LoadingDataListener<HomeHotBean>() {
//            @Override
//            public void onRefresh() {
//                super.onRefresh();
//                L.i("setLoadingDataListener onRefresh");
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                L.i("setLoadingDataListener onStart");
//            }
//
//            @Override
//            public void onSuccess(HomeHotBean o) {
//                super.onSuccess(o);
//                L.i("setLoadingDataListener onSuccess: " + o);
//            }
//
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                L.i("setLoadingDataListener onFailure");
//            }
//        });
    }

    @Override
    public void initData() {
        requestLeftData();
        requestBanner();
//
//        BaseLoadMoreRecyclerAdapter baseLoadMoreRecyclerAdapter = new BaseLoadMoreRecyclerAdapter(null, null, 0, (List) null){
//
//        };
//        baseLoadMoreRecyclerAdapter.setPullAndMoreListener(new PullToMoreListener() {
//            @Override
//            public void onRefreshLoadMore(BaseLoadMoreRecyclerAdapter.LoadMoreViewHolder loadMoreViewHolder) {
//                //加载更多
//            }
//
//            @Override
//            public void onRefresh() {
//                //下啦刷新
//            }
//        });


    }

    @Override
    public void initListener() {
    }


    public void requestLeftData() {
        mCall = ItheimaHttp.getAsync(Urls.api_hometype_left_lists, new HttpResponseListener<List<LeftBean>>() {
            @Override
            public void onResponse(List<LeftBean> leftBeen, Headers headers) {
                mLeftAdapter = new BaseRecyclerAdapter(mLeftRecyclerView
                        , MyLeftViewHolder.class
                        , R.layout.item_hometype_left
                        , leftBeen);

                requestRightData(leftBeen.get(0).id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void requestRightData(Integer categoryId) {
        mLeftAdapter.notifyDataSetChanged();
        mPullToLoadMoreRecyclerView.putParam("categoryId", String.valueOf(categoryId));
        mPullToLoadMoreRecyclerView.requestData();
    }


    public void requestBanner() {
        Request request = ItheimaHttp.newGetRequest(Urls.api_homepage_banner);
        request.putParams("type", String.valueOf(1));

        ItheimaHttp.send(request, new HttpResponseListener<List<HomeBannerBean>>() {
            @Override
            public void onResponse(List<HomeBannerBean> homeBannerBeans, Headers headers) {
                for (HomeBannerBean bannerBean : homeBannerBeans) {
                    TextSliderView textSliderView = new TextSliderView(getContext());
                    textSliderView.description(bannerBean.name)
                            .image(bannerBean.imgUrl);
                    mSliderLayout.addSlider(textSliderView);
                }
                mSliderLayout.startAutoCycle();
            }
        });

    }


    public static class MyRightViewHolder extends BaseRecyclerViewHolder<HomeHotBean.ItemHomeHotBean> {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public MyRightViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        @Override
        public void onBindRealData() {
            Picasso.with(mContext).load(mData.imgUrl).into(ivIcon);
            tvName.setText(mData.name);
            tvPrice.setText("￥" + String.valueOf(mData.price));
        }

    }

    static int mClickPosition;

    public static class MyLeftViewHolder extends BaseRecyclerViewHolder<LeftBean> {
        @BindView(R.id.tv_name)
        TextView tvName;

        public MyLeftViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        @Override
        public void onBindRealData() {
            tvName.setText(mData.name);
            tvName.setSelected(mPosition == mClickPosition);
        }

        @OnClick(R.id.tv_name)
        public void click(View v) {
            int clickPosition = getAdapterPosition();
            mClickPosition = clickPosition;
            EventBus.getDefault().post(mData.id);

        }

    }


    public class LeftBean {
        public int id;
        public String name;
        public int sort;
    }

    @Override
    public void onStop() {
        super.onStop();
        mSliderLayout.stopAutoCycle();
    }

    @Override
    public void free() {
        super.free();
        if (mLeftRecyclerView != null) {
            mLeftAdapter = null;
            mSliderLayout = null;
            mLeftRecyclerView.setAdapter(null);
            mLeftRecyclerView = null;
            mPullToLoadMoreRecyclerView.free();
            mPullToLoadMoreRecyclerView = null;

            if (mCall != null && !mCall.isCanceled()) {
                mCall.cancel();
                mCall = null;
            }
        }

    }
}
