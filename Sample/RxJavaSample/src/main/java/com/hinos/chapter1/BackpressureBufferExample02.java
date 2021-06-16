package com.hinos.chapter1;

import com.hinos.utils.LogType;
import com.hinos.utils.Logger;
import com.hinos.utils.TimeUtil;
import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class BackpressureBufferExample02
{
    public static void main(String[] args){
        System.out.println("# start : " + TimeUtil.getCurrentTimeFormatted());
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log("#inverval doOnNext()", data))
                .onBackpressureBuffer(
                        2, //버퍼 옹량 2개
                        () -> Logger.log("overflow!"),
                        BackpressureOverflowStrategy.DROP_OLDEST)
                .doOnNext(data -> Logger.log("#onBackpressureBuffer doOnNext()", data))
                .observeOn(Schedulers.computation(), false, 1) //요청하는 데이터 개수
                .subscribe(
                        data -> {
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error)
                );

        TimeUtil.sleep(2500L);
    }
}
