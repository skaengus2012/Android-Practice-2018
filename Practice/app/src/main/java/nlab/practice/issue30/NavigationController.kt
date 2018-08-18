package nlab.practice.issue30

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import nlab.practice.issue30.page.NoteFragment
import nlab.practice.issue30.page.ShareFragment
import nlab.practice.issue30.page.UserEndFragment
import nlab.practice.issue30.page.UserFragment


/**
 * Fragment 를 자유자제로 이동하는 네비게이션 컨트롤러 정의
 *
 * @author Doohyun
 */
class NavigationController(
        @IdRes private val _containerIdRes: Int,
        private val _fragmentManager: FragmentManager) {

    /**
     * 네비게이션에 쌓이기 위해 화면마다 고유한 이름이 추가되어야함
     *
     * -> proguard 에서 simpleName 을 사용하는 것은 위험한 행위일 수 있음
     *
     * @see { https://medium.com/@elye.project/the-danger-of-using-class-getsimplename-as-tag-for-fragment-5cdf3a35bfe2 }
     *
     * @return
     */
    private inline fun <reified T> getNavigationTagName() : String = T::class.java.canonicalName

    /**
     * [supplier] 타입의 Fragment 를 담은 FragmentTransaction 를 출력한다.
     *
     * @param supplier
     * @return
     */
    private inline fun <reified T : Fragment> getCommonFragmentTransaction(supplier : () -> T) : FragmentTransaction {

        val navigationTag = getNavigationTagName<T>()

        val fragmentTransaction = _fragmentManager.beginTransaction()

        // 태그로 프래그먼트 찾고, 해당 프로그래먼트를 표시
        // 입력된 프래그먼트가 존재하지 않을 경우, 파라미터를 트랜잭션에 추가
        val currentShowFragment = _fragmentManager.findFragmentByTag(navigationTag)
                ?.let {
                    fragmentTransaction.show(it)
                    it
                }
                ?: supplier().apply {
                    fragmentTransaction.add(_containerIdRes, this, navigationTag)
                }

        // 현재 최상단 프래그먼트 숨기기
        _fragmentManager.primaryNavigationFragment
                ?.takeIf { it != currentShowFragment }
                ?.let { fragmentTransaction.hide(it) }

        // 최상단 프래그먼트로 담기
        return fragmentTransaction.setPrimaryNavigationFragment(currentShowFragment)
    }

    /**
     * [T] 에 해당하는 프래그먼트로 대체.
     *
     * 만약 존재하지 않는다면 [supplier] 에서 생성된 프래그먼트로 대체
     *
     * @param supplier
     */
    private inline fun <reified T : Fragment> replaceFragment(supplier : () -> T) =
            getCommonFragmentTransaction(supplier)
                .setReorderingAllowed(true)
                .commitNowAllowingStateLoss()


    /**
     * [T] 에 해당하는 프래그먼트 추가.
     *
     * 만약 존재하지 않는다면 [supplier] 에서 생성된 프래그먼트로 추가
     *
     * @param supplier
     */
    private inline fun <reified T : Fragment> addFragment(supplier : () -> T) =
            getCommonFragmentTransaction(supplier)
                    .addToBackStack(null)
                    .commit()


    /**
     * User 화면으로 이동
     */
    fun goToUser() = replaceFragment{UserFragment()}

    /**
     * User End 화면으로 이동
     */
    fun goToUserEnd() = addFragment {UserEndFragment()}

    /**
     * Note 화면으로 이동
     */
    fun goToNote() = replaceFragment {NoteFragment()}

    /**
     * Share 화면으로 이동
     */
    fun goToShare() = replaceFragment {ShareFragment()}

}