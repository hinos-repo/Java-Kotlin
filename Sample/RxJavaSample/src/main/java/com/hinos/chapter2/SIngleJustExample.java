package com.hinos.chapter2;

import com.hinos.utils.DateUtil;
import com.hinos.utils.LogType;
import com.hinos.utils.Logger;
import io.reactivex.rxjava3.core.Single;

public class SIngleJustExample
{
    public static void main(String[] args) {
        Single.just(DateUtil.getNowDate())
                .subscribe(
                        data -> Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data),
                        error -> Logger.log(LogType.ON_ERROR, error)
                );
    }
}
