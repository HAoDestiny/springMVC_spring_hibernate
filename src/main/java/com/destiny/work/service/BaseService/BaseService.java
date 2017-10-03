package com.destiny.work.service.BaseService;

/**
 * Created by Destiny_hao on 2017/8/7.
 */
public class BaseService {

    /***
     * 得到pageCoed
     * @param pageCoed
     * @return
     */
    public static int getPageCoed(String pageCoed) {
        if(pageCoed == null || pageCoed.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(pageCoed);
    }
}
