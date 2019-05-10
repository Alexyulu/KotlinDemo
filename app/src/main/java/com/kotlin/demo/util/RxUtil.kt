package com.kotlin.demo.util

import com.kotlin.demo.model.http.exception.ApiException
import com.kotlin.demo.model.http.response.BaseResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2017/9/18
 * Desc :
 */
object RxUtil {

    /**
     * 统一线程处理
     * @param <T> T
     * @return FlowableTransformer
    </T> */
    fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> {    //compose简化线程
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 统一返回结果处理
     * @param <T> T
     * @return FlowableTransformer
    </T> */
    fun <T> handleResult(): FlowableTransformer<BaseResponse<T>, T> {   //compose判断结果
        return FlowableTransformer { httpResponseFlowable ->
            httpResponseFlowable.flatMap { tBaseResponse ->
                val code = tBaseResponse.code
                val msg = tBaseResponse.message

                /*if (code == 200) {
                    createData(tBaseResponse.data)
                } else {
                    Flowable.error(ApiException(msg, code))
                }*/
                if (tBaseResponse.isSuccess()) {
                    if (tBaseResponse.data == null) {
                        Flowable.just(Any() as T)
                    } else {
                        createData(tBaseResponse.data)
                    }
                } else {
                    Flowable.error(ApiException(msg, code))
                }
            }
        }
    }

    fun <T> done() : FlowableTransformer<BaseResponse<T>, T> {
        return FlowableTransformer {
            it.compose(rxSchedulerHelper()).compose(handleResult())
        }
    }

    /**
     * 生成Flowable
     * @param <T> T
     * @return Flowable<T>
    </T></T> */
    private fun <T> createData(t: T): Flowable<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }
}
