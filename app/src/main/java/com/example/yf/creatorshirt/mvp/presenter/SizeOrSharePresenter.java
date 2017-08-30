package com.example.yf.creatorshirt.mvp.presenter;

import android.util.Log;

import com.example.yf.creatorshirt.http.DataManager;
import com.example.yf.creatorshirt.http.HttpResponse;
import com.example.yf.creatorshirt.mvp.model.detaildesign.CommonStyleData;
import com.example.yf.creatorshirt.mvp.presenter.base.RxPresenter;
import com.example.yf.creatorshirt.mvp.presenter.contract.SizeOrShareContract;
import com.example.yf.creatorshirt.utils.RxUtils;
import com.example.yf.creatorshirt.utils.Utils;
import com.example.yf.creatorshirt.widget.CommonSubscriber;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by yangfang on 2017/8/28.
 */

public class SizeOrSharePresenter extends RxPresenter<SizeOrShareContract.SizeOrShareView> implements SizeOrShareContract.Presenter {

    private DataManager manager;
    private String QiniuToken;
    UploadManager uploadManager = new UploadManager();
    private String userToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOjExMTEsIm1vYmlsZSI6IjE1ODY4MTc3NTQyIiwibmFtZSI6IuacseWTpTEiLCJsYXN0TG9naW50aW1lIjoiMjAxNy0wOC0yOFQxMTowMjoyNy4wMDBaIiwicGFzc3dvcmQiOiI3NDkyIiwiaGVhZEltYWdlIjoiaHR0cDovL291YjNuc2pnaC5ia3QuY2xvdWRkbi5jb20vcGF0dGVybl80LnBuZyIsImlzTmV3IjpmYWxzZSwiaWF0IjoxNTAzOTkyODcyLCJleHAiOjE1MDUyODg4NzJ9.cCA5l3Dw64yR3HAV_rTuQT3vuuj1DBZReAvHi6BLRXqCtqRY-jLhoQk4SdFnWcZp0LaTvm-YbFHxqAx5yn0ssNuUMub3Npqplc636llMF30eCixNhBvIF12UdpGtq-SEO4R8vL791gmfzqrc3QoWy-GBuC8HrBcJqZK2Vi33cpA";
    private String UserId = "1111_";
    private String imageUrl;

    @Inject
    public SizeOrSharePresenter(DataManager manager) {
        this.manager = manager;
    }

//    @Override
//    public void sendOrderData(String jsonString) {
//
//        //构建body

////        addSubscribe(manager.saveOrderData(body)
////        .compose(RxUtils.<HttpResponse>rxSchedulerHelper())
////        .compose(RxUtils.<String>handleResult())
////        .subscribeWith());
//    }

    public void saveImage(String mFrontImageUrl) {
        String key = UserId + Utils.getTime();
        uploadManager.put(mFrontImageUrl, key, QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                if (info.isOK()) {
                    Log.e("qiniu", "UPLOAD SUCCESS");
                } else {
                    Log.e("qiniu", "UPLOAD fail");
                }
                Log.e("qiniu", "UPLOAD fail" + key + ":" + info + ":" + response);
                imageUrl = "http://oub3nsjgh.bkt.clouddn.com/" + key;
            }
        }, null);
    }

    /**
     * 获取七牛token
     */
    public void getToken() {
        addSubscribe(manager.getQiToken(userToken)
                .compose(RxUtils.<HttpResponse<String>>rxSchedulerHelper())
                .compose(RxUtils.<String>handleResult())
                .subscribeWith(new CommonSubscriber<String>(mView) {
                    @Override
                    public void onNext(String s) {
                        QiniuToken = s;
                    }
                })
        );
    }

    /**
     * {"gender":"","baseId":"","styleContext":"",
     * "height":170,"color":"#FFFFFF",
     * "orderType":"Check/Share",
     * "size":"","address":"","zipCode":"","finishImage":""}
     *
     * @param mFrontData
     * @param mBackData
     * @param styleContext
     */
    @Override
    public void sendOrderData(CommonStyleData mFrontData, CommonStyleData mBackData, String styleContext) {
        JSONObject request = new JSONObject();
        try {
            request.put("gender", mBackData.getGender());
            request.put("baseId", Integer.getInteger(mBackData.getType()));
            request.put("styleContext", styleContext);
            request.put("height", 170);
            request.put("color", "#FFFFFF");
            request.put("orderType", "Check");
            request.put("size", "170cm");
            request.put("address", "");
            request.put("zioCode", "");
            request.put("finishImage", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), request.toString());
        addSubscribe(manager.saveOrderData(requestBody)
                .compose(RxUtils.<HttpResponse>rxSchedulerHelper())
                .subscribeWith();
    }
}
