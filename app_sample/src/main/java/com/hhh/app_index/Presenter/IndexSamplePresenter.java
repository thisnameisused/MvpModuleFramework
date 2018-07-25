package com.hhh.app_index.Presenter;

import com.hhh.app_index.IndexActivity;
import com.hhh.app_index.V.IIndexActivityView;
import com.hhh.lib_api.services.impl.SampleServiceImp;
import com.hhh.lib_api.services.interfaces.ISampleService;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.functions.Consumer;

/**
 * 复用获取基础数据presenter的IndexSamplePresenter，实现presenter的复用
 */
public class IndexSamplePresenter extends IGetBaseInfoSamplePresenter<IIndexActivityView> {

    private ISampleService mBookService;

    public void getDataFromNet(IndexActivity indexActivity, String param1, String param2) {
        mBookService = SampleServiceImp.create();
        mBookService.addNewsComment(param1, param2)
                .compose(indexActivity.bindUntilEvent(ActivityEvent.PAUSE))  // 绑定生命周期
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        getV().returnSampleData("返回sample数据");
                        getV().returnBaseInfo("返回基础数据");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }


}