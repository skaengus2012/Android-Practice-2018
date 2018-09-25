package nlab.practice.util.view

import android.os.Build
import android.support.annotation.IdRes
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import nlab.practice.common.model.User
import nlab.practice.issue30.page.NoteFragment
import nlab.practice.issue30.page.ShareFragment
import nlab.practice.issue30.page.UserEndFragment
import nlab.practice.issue30.page.UserFragment
import nlab.practice.issue32.DaggerTestFragment


/**
 * Fragment 를 자유자제로 이동하는 네비게이션 컨트롤러 정의
 *
 * @author Doohyun
 */
class NavigationController(
        @IdRes val _containerIdRes: Int,
        val _fragmentManager: FragmentManager) {

    /**
     * [T] 에 해당하는 프래그먼트로 대체.
     *
     * 만약 존재하지 않는다면 [supplier] 에서 생성된 프래그먼트로 대체
     * -> 해당 프래그먼트는 첫번째로 쌓이는 프래그먼트에 사용
     */
    private inline fun <reified T : Fragment> replace(supplier : () -> T) {
        val fragmentTransaction = _fragmentManager.beginTransaction()
        val currentShowFragment = findFragmentByTag(fragmentTransaction, getDefaultNavigationTagName<T>() , supplier)

        // Primary 를 현재 제작되는 프래그먼트로 세팅
        fragmentTransaction
                .hideFragmentIfPrimaryFragmentNoneEquals(currentShowFragment)
                .setPrimaryNavigationFragment(currentShowFragment)
                .setReorderingAllowed(true)
                .commitAllowingStateLoss()
    }

    /**
     * [T] 에 해당하는 프래그먼트 추가.
     *
     * 만약 존재하지 않는다면 [supplier] 에서 생성된 프래그먼트로 추가
     */
    private inline fun <reified T : Fragment> add(tag: String, supplier : () -> T) {

        val fragmentTransaction = _fragmentManager.beginTransaction()
        val currentShowFragment = findFragmentByTag(fragmentTransaction, tag, supplier)

        fragmentTransaction
                .hideFragmentIfPrimaryFragmentNoneEquals(currentShowFragment)
                .setPrimaryNavigationFragment(currentShowFragment)
                .addToBackStack(null)
                .commit()
    }

    /**
     * [tag] 로 Fragment 를 찾는다.
     *
     * 만약 존재한다면 표시, 없다면, [supplier] 로 생산 후, [tag] 이름으로 저장한다.
     */
    private inline fun <reified T : Fragment> findFragmentByTag(
            fragmentTransaction : FragmentTransaction,
            tag: String,
            supplier : () -> T) : Fragment {
        return _fragmentManager.findFragmentByTag(tag)
                ?.apply { fragmentTransaction.show(this) }
                ?: supplier().apply { fragmentTransaction.add(_containerIdRes, this, tag) }
    }

    /**
     * 현재 Primary Fragment 가 [fragment] 와 다르다면 숨김 처리를 한다.
     */
    fun FragmentTransaction.hideFragmentIfPrimaryFragmentNoneEquals(fragment : Fragment) : FragmentTransaction {
        _fragmentManager.primaryNavigationFragment?.takeIf { it != fragment }?.let { hide(it) }

        return this
    }

    /**
     * [sharedElementSupportable] 에 포함된 공유요소를 추가한다.
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun FragmentTransaction.addSharedElements(
            sharedElementSupportable: SharedElementFragmentSupportable) : FragmentTransaction{

        // SharedElement 세팅하기
        sharedElementSupportable.getSharedElementPairs()
                .forEach {
                    val view = it.first
                    val transitionName = it.second

                    view?.transitionName = transitionName

                    addSharedElement(view, transitionName)
                }

        return this
    }

    /**
     * SharedElement Transaction 을 이용한 Fragment 이동
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    inline fun <reified T : Fragment> replaceWithSharedElement(
            tag: String?,
            sharedElementSupportable: SharedElementFragmentSupportable,
            supplier : () -> T) {

        val fragment = supplier()

        _fragmentManager.beginTransaction()
                .addSharedElements(sharedElementSupportable)
                .add(_containerIdRes, fragment, tag)
                .hideFragmentIfPrimaryFragmentNoneEquals(fragment)
                .setPrimaryNavigationFragment(fragment)
                .addToBackStack(null)
                .setReorderingAllowed(true)
                .commitAllowingStateLoss()
    }

    fun goToUser() = replace { UserFragment() }

    fun goToNote() = replace { NoteFragment() }

    fun goToShare() = replace { ShareFragment() }

    fun goToUserEnd(user: User) = add("${getDefaultNavigationTagName<UserEndFragment>()}:${user.userId}") {
        UserEndFragment.create(user)
    }

    fun goToDaggerTestFragment() = replace { DaggerTestFragment() }
}

inline fun <reified T> getDefaultNavigationTagName() : String = T::class.java.canonicalName