package com.hhh.lib_api.services.impl;

import com.hhh.lib_api.APIHelper;
import com.hhh.lib_api.path.IndexPath;
import com.hhh.lib_api.services.interfaces.IHttpClient;
import com.hhh.lib_api.services.interfaces.IIndexService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by huhanghao on 2018/2/27.
 */

public class IndexServiceImp implements IIndexService {

    private IHttpClient mHttpClientService;

    private IndexServiceImp() {
        mHttpClientService = IHttpClientImp.getInstance();
    }

    public static IndexServiceImp create() {
        return new IndexServiceImp();
    }

    @Override
    public Observable<Boolean> addNewsComment(String id, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("content", content);

        return mHttpClientService.postWithoutRep(
                APIHelper.getBaseUrl().concat(String.format(IndexPath.NEWS_ADD_COMMENT.path, id)), params);
    }


    @Override
    public Observable<Boolean> deleteNewsComment(String commentID) {

        return mHttpClientService.delete(
                APIHelper.getBaseUrl().concat(String.format(IndexPath.NEWS_DELETE_COMMENT.path, commentID)));
    }


}
