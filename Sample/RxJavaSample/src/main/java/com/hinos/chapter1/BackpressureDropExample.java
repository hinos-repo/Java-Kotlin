package com.hinos.chapter1;

import com.hinos.utils.LogType;
import com.hinos.utils.Logger;
import com.hinos.utils.TimeUtil;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * 버퍼가 가득차면 버퍼 바깥쪽에서 통지 대기중인 데이터들은 계속 파기(DROP)하고
 * 버퍼를 비운 시점에 Drop되지 않고 대기중인 데이터부터 버퍼에 담는다.
 */
public class BackpressureDropExample {
    public static void main(String[] args){
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log("#inverval doOnNext()", data))
                .onBackpressureDrop(dropData -> Logger.log(LogType.PRINT, dropData + " Drop!"))
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error)
                );

        TimeUtil.sleep(5500L);
    }
}
