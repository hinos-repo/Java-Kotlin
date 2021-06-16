package com.hinos.chapter1;

import com.hinos.utils.LogType;
import com.hinos.utils.Logger;
import com.hinos.utils.TimeUtil;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 *
 * Flowable : 배압 처리가 되어 있기 때문에 생성자에서 데이터를 발행 및 전송하는 속도와 소비자에서 데이터 처리하는 속도가 클 때 에러가 발생한다.
 * io.reactivex.rxjava3.exceptions.MissingBackpressureException: Can't deliver value 128 due to lack of requests
 *
 * Obserbable : 배압 처리가 없기 때문에 에러가 발생하지 않는다.
 *
 */
public class MissingBackpressureExceptionExample
{
    public static void main(String[] args) throws InterruptedException {
//        flowableFunc();
        observableFunc();
    }

    private static void flowableFunc() throws InterruptedException
    {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> {
                            Logger.log(LogType.PRINT, "# 소비자 처리 대기 중..");
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        Thread.sleep(2000L);
    }

    private static void observableFunc() throws InterruptedException
    {
        Observable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .observeOn(Schedulers.computation())
                .subscribe(
                        data -> {
                            Logger.log(LogType.PRINT, "# 소비자 처리 대기 중..");
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );

        Thread.sleep(2000L);
    }
}
