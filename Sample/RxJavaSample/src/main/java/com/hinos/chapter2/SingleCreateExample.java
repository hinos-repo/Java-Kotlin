package com.hinos.chapter2;

import com.hinos.utils.DateUtil;
import com.hinos.utils.LogType;
import com.hinos.utils.Logger;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;


public class SingleCreateExample
{
    public static void main(String[] args) {
        Single<String> single = Single.create(new SingleOnSubscribe<String>()
        {
            @Override
            public void subscribe(@NonNull SingleEmitter<String> emitter) throws Throwable
            {
                emitter.onSuccess(DateUtil.getNowDate());
            }
        });

        single.subscribe(new SingleObserver<String>()
        {
            @Override
            public void onSubscribe(@NonNull Disposable d)
            {
                // 아무것도 하지 않음.
            }

            @Override
            public void onSuccess(@NonNull String data)
            {
                Logger.log(LogType.ON_SUCCESS, "# 날짜시각: " + data);
            }

            @Override
            public void onError(@NonNull Throwable error)
            {
                Logger.log(LogType.ON_ERROR, error);
            }
        });
    }
}
