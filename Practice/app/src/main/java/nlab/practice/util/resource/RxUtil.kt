package nlab.practice.util.resource

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Rx 관련 유틸 정의
 *
 * @author Doohyun
 */

/**
 * CompositeDisposable 에 Disposable 을 삽입하는 확장함수 정의
 */
fun Disposable.add(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)