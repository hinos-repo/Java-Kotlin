package com.hinos.chapter1;

import com.hinos.utils.LogType;
import com.hinos.utils.Logger;
import com.hinos.utils.TimeUtil;
import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/*** 배압 전략
 * Rxjava에서는 BackpressureStrategy를 통해 Flowable이 통지 대기 중인 데이터를 어떻게 다룰지에 대한 배압 전략을 제공한다.
 *
 * 1. Missing 전략
 * - 배압을 적용하지 않는다.
 * - 나중에 onBackpressureXXX()로 배압 적용을 할 수 있다.
 *
 * 2. Error 전략
 * - 통지된 데이터가 버퍼의 크기를 초과하면 MissingBackpressureException 에러를 통지한다.
 * - 즉, 소비자가 생산자의 통지 속도를 따라 잡지 못할 때 발생한다.
 *
 * 3. 버퍼 전략
 * ● DROP_LATEST
 * - 버퍼가 가득 찬 시점에 버퍼내에서 가장 최근에 버퍼로 들어온 데이터를 DROP 한다.
 * - DROP된 빈 자리에 버퍼 밖에서 대기하던 데이터를 채운다.
 *
 * ● DROP_OLDEST
 * - 버퍼가 가득 찬 시점에 버퍼내에서 가장 오래전에 버퍼로 들어온 데이터를 DROP 한다.
 * - DROP 된 빈 자리에는 버퍼 밖에서 대기하던 데이터를 채운다.
 *
 * 4. DROP 전략
 * - 버퍼에 데이터가 모두 채워진 상태가 되면 이후에 생성되는 데이터를 버리고(DROP),
 * 버퍼가 비워지는 시점에 DROP 되지 않은 데이터부터 다시 버퍼에 담는다.
 *
 * 5. LATEST 전략
 * - 버퍼에 데이터가 모두 채워진 상태가 되면 버퍼가 비워질 때까지 통지된 데이터는 버퍼 밖에서 대기하며
 * 버퍼가 비워지는 시점에 가장 최근에 통지된 데이터부터 버퍼에 담는다.
 **/

public class BackpressureBufferExample01
{
    public static void main(String[] args) {
        System.out.println("# start : " + TimeUtil.getCurrentTimeFormatted());
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> { // interval에서 통지한 데이터가 출력
                    Logger.log("#inverval doOnNext()", data);
                })
                .onBackpressureBuffer(
                        2,
                        () -> {
                            Logger.log("overflow!"); // 버퍼에 데이터가 다 찼을 때 overflow 출력
                        },
                        BackpressureOverflowStrategy.DROP_LATEST)
                .doOnNext( // 버퍼 내에서 데이터가 통지될 떄 로그 출력
                        data -> {
                            Logger.log("#onBackpressureBuffer doOnNext()", data);
                        }
                )
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> { // 소비자에서 데이터를 처리할 때 로그 출력
                            TimeUtil.sleep(1000L);
                            Logger.log(LogType.ON_NEXT, data);
                        },
                        error -> { // 에러 발생
                            Logger.log(LogType.ON_ERROR, error);
                        }
                );

        TimeUtil.sleep(2800L);
    }
}
