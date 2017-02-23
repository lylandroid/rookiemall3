package com.itheima.rookiemall.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;
import com.itheima.rookiemall.R;
import com.itheima.rookiemall.base.BaseFragment;
import com.itheima.rookiemall.bean.HomeBannerBean;
import com.itheima.rookiemall.bean.HomeListsBean;
import com.itheima.rookiemall.config.Urls;
import com.itheima.rookiemall.widget.CustomToolBar;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.itheima.recycler.adapter.BaseRecyclerAdapter;
import org.itheima.recycler.header.RecyclerViewHeader;
import org.itheima.recycler.listener.RecyclerItemClickListener;
import org.itheima.recycler.viewholder.BaseRecyclerViewHolder;
import org.itheima.recycler.widget.ItheimaRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;
import retrofit2.Call;

/**
 * 主页Fragment
 * Created by lyl on 2016/9/25.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    ItheimaRecyclerView mRecyclerView;

    @BindView(R.id.recycler_header)
    RecyclerViewHeader mRecyclerViewHeader;

    @BindView(R.id.slider)
    SliderLayout mSliderLayout;
    private Call mBannerCall;
    private Call mListCall;

    @Override
    public int getContentViewId() {
        return R.layout.home_homepage_fragment;
    }

    @Override
    protected void initToolbar(CustomToolBar toolBar) {
        super.initToolbar(toolBar);
        toolBar.setShowSearchView();
    }


    @Override
    public void initView() {
        mRecyclerViewHeader.attachTo(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"onItemClick",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext,"onItemLongClick",Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public void initData() {
        httpBanner();
        httpHomeDatas();
    }


    @Override
    public void initListener() {

    }


    public void httpBanner() {
        Request request = ItheimaHttp.newGetRequest(Urls.api_homepage_banner);
        request.putParams("type", "1");
        mBannerCall = ItheimaHttp.send(request, new HttpResponseListener<List<HomeBannerBean>>() {
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

    private void httpHomeDatas() {
        mListCall = ItheimaHttp.getAsync(Urls.api_homepage_lists, new HttpResponseListener<List<HomeListsBean>>() {
            @Override
            public void onResponse(List<HomeListsBean> homeListsBeen, Headers headers) {
                new HomeAdapter(mRecyclerView, homeListsBeen);
            }
        });


    }

    class HomeAdapter extends BaseRecyclerAdapter {
        private final int ITEM_RIGHT = 0;
        private final int ITEM_LEFT = 1;

        public HomeAdapter(RecyclerView recyclerView, List datas) {
            super(recyclerView, null, 0, datas);
        }

        @Override
        public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_LEFT) {
                return new HomeViewHolder(parent, R.layout.item_homepage_left_recyclerview);
            }
            return new HomeViewHolder(parent, R.layout.item_homepage_right_recyclerview);
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? ITEM_LEFT : ITEM_RIGHT;
        }
    }

    class HomeViewHolder extends BaseRecyclerViewHolder<HomeListsBean> {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_left_top)
        ImageView ivLeftTop;
        @BindView(R.id.iv_left_bottom)
        ImageView ivLeftBottom;
        @BindView(R.id.iv_right)
        ImageView ivRight;

        public HomeViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        @Override
        public void onBindRealData() {
            tvTitle.setText(mData.title);
            Picasso.with(mContext).load(mData.cpOne.imgUrl).into(ivRight);
            Picasso.with(mContext).load(mData.cpTwo.imgUrl).into(ivLeftTop);
            Picasso.with(mContext).load(mData.cpThree.imgUrl).into(ivLeftBottom);
        }

        @OnClick({R.id.iv_right, R.id.iv_left_top, R.id.iv_left_bottom})
        void onClick(View v) {
            ValueAnimator animator = ObjectAnimator.ofFloat(v, "rotationX", 0f, 360f);
            animator.setDuration(200);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    startDetailsActivity(R.layout.fragment_home_list_detail, false);
                }
            });
            int id = 0;
            switch (v.getId()) {
                case R.id.iv_right:
                    id = mData.cpOne.id;
                    break;
                case R.id.iv_left_top:
                    id = mData.cpTwo.id;
                    break;
                case R.id.iv_left_bottom:
                    id = mData.cpThree.id;
                    break;
            }
            EventBus.getDefault().postSticky(id);

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        mSliderLayout.stopAutoCycle();
    }

    @Override
    public void free() {
        super.free();
        if (mSliderLayout != null) {
            mSliderLayout = null;
            mRecyclerViewHeader.detach();
            mRecyclerViewHeader = null;

            mBannerCall.cancel();
            mBannerCall = null;

            mListCall.cancel();
            mListCall = null;
        }

    }
}
