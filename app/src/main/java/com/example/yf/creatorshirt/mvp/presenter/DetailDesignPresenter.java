package com.example.yf.creatorshirt.mvp.presenter;

import com.example.yf.creatorshirt.http.DataManager;
import com.example.yf.creatorshirt.http.HttpResponse;
import com.example.yf.creatorshirt.mvp.model.detaildesign.DetailStyleBean;
import com.example.yf.creatorshirt.mvp.presenter.base.RxPresenter;
import com.example.yf.creatorshirt.mvp.presenter.contract.DetailDesignContract;
import com.example.yf.creatorshirt.utils.RxUtils;
import com.example.yf.creatorshirt.widget.CommonSubscriber;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by yangfang on 2017/8/19.
 */

public class DetailDesignPresenter extends RxPresenter<DetailDesignContract.DetailDesignView>
        implements DetailDesignContract.Presenter {

    private DataManager manager;

    @Inject
    public DetailDesignPresenter(DataManager dataManager) {
        this.manager = dataManager;
    }

    @Override
    public void getDetailDesign(String gender, String type) {
        JSONObject root = new JSONObject();
        final JSONObject request = new JSONObject();
        try {
            request.put("Gender", gender);
            request.put("Typeversion", type);
            root.put("baseInfo", request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (root != null) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), root.toString());
            addSubscribe(manager.getDetailDesign(requestBody)
                    .compose(RxUtils.<HttpResponse<DetailStyleBean>>rxSchedulerHelper())
                    .compose(RxUtils.<DetailStyleBean>handleResult())
                    .subscribeWith(new CommonSubscriber<DetailStyleBean>(mView, "请求数据失败") {
                        @Override
                        public void onNext(DetailStyleBean detailStyleBean) {
                            mView.showSuccessData(detailStyleBean);
                        }
                    })

            );
//            TestRequestServer.getInstance().getDetailDesign(requestBody).enqueue(new Callback<HttpResponse>() {
//                @Override
//                public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
//                    Log.e("tag","ddddd"+response.body().getResult());
//
//                }
//
//                @Override
//                public void onFailure(Call<HttpResponse> call, Throwable t) {
//
//                }
//            });
        }


    }
}
