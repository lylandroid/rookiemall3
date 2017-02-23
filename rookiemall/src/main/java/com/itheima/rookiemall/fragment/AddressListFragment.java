package com.itheima.rookiemall.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.itheima.retrofitutils.ItheimaHttp;
import com.itheima.retrofitutils.Request;
import com.itheima.retrofitutils.listener.HttpResponseListener;
import com.itheima.rookiemall.R;
import com.itheima.rookiemall.base.BaseFragment;
import com.itheima.rookiemall.bean.AddressListBean;
import com.itheima.rookiemall.bean.BaseBean;
import com.itheima.rookiemall.config.Constants;
import com.itheima.rookiemall.config.Urls;
import com.itheima.rookiemall.utils.SPUtils;
import com.itheima.rookiemall.utils.UIUtils;

import com.itheima.rookiemall.widget.CustomToolBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.itheima.recycler.adapter.BaseRecyclerAdapter;
import org.itheima.recycler.viewholder.BaseRecyclerViewHolder;
import org.itheima.recycler.widget.ItheimaRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by lyl on 2016/10/15.
 */

public class AddressListFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    ItheimaRecyclerView mRecyclerView;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_address_list;
    }

    @Override
    protected void initToolbar(CustomToolBar toolBar) {
        super.initToolbar(toolBar);
        toolBar.setTitle("地址列表");
        toolBar.setRightViewBackground(R.drawable.icon_add);
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void rightClick(View v) {
        startDetailsActivity(R.layout.fragment_add_address, false);
    }

    @Override
    protected boolean isShowBackView() {
        return true;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        httpAddressList(true);

    }

    @Override
    public void initListener() {

    }

    @Subscribe
    public void httpAddressList(Boolean isSendGetAddressHttp) {
        if (!isSendGetAddressHttp) {
            return;
        }
        Map<String, Object> param = new HashMap<>();
        param.put(Constants.USER_ID, SPUtils.getUserId(mContext));
        param.put(Constants.TOKEN, SPUtils.getToken(mContext));
        Request request = ItheimaHttp.newGetRequest(Urls.api_address_list);
        request.putParamsMap(param);
        ItheimaHttp.send(request, new HttpResponseListener<List<AddressListBean>>() {
            @Override
            public void onResponse(List<AddressListBean> addressListBean, Headers headers) {
                new BaseRecyclerAdapter(mRecyclerView
                        , AddressListViewHolder.class
                        , R.layout.item_fragment_address_list
                        , addressListBean);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                super.onFailure(call, e);
            }
        });
    }

    public static class AddressListViewHolder extends BaseRecyclerViewHolder<AddressListBean> {
        @BindView(R.id.txt_name)
        TextView mTvName;
        @BindView(R.id.txt_phone)
        TextView mTvPhone;
        @BindView(R.id.txt_address)
        TextView mTvAddress;
        @BindView(R.id.cb_is_defualt)
        CheckBox mCbisDef;
        @BindView(R.id.txt_edit)
        TextView mTvEdit;
        @BindView(R.id.txt_del)
        TextView mTvDel;

        public AddressListViewHolder(ViewGroup parentView, int itemResId) {
            super(parentView, itemResId);
        }

        @OnClick(R.id.txt_del)
        public void click(View v) {
            Map<String, Object> param = new HashMap<>();
            param.put(Constants.USER_ID, SPUtils.getUserId(mContext));
            param.put(Constants.TOKEN, SPUtils.getToken(mContext));
            param.put("id", mData.id);
            Request request = ItheimaHttp.newGetRequest(Urls.api_del_address);
            request.putParamsMap(param);
            ItheimaHttp.send(request, new HttpResponseListener<BaseBean>() {
                @Override
                public void onResponse(BaseBean baseBean, Headers headers) {
                    UIUtils.showDefToast(mContext, baseBean.message);
                    EventBus.getDefault().post(true);

                }
            });
        }

        @Override
        protected void onBindRealData() {
            mTvName.setText(mData.consignee);
            mTvPhone.setText(mData.phone);
            mTvAddress.setText(mData.addr);
            mCbisDef.setChecked(mData.isDefault);

        }


    }
}
