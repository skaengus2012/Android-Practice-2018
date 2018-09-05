package nlab.practice.issue30.page


import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_note.view.*

import nlab.practice.R
import nlab.practice.issue30.NavigationActivity
import nlab.practice.util.V4Pair
import nlab.practice.util.view.NavigationController
import nlab.practice.util.view.SharedElementFragmentSupportable

private const val USER_ID = "N"

/**
 * Note 화면 정의
 *
 * @author Doohyun
 */
class NoteFragment : Fragment() {

    private var _view : View? = null

    private lateinit var _navigationController : NavigationController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (_view == null) {
            _view = inflater.inflate(R.layout.fragment_note, container, false)
        }

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _navigationController = NavigationController(R.id.layoutFragment, childFragmentManager)

        val isNavigationSupport = activity is NavigationActivity
        val isSdkSupportSharedElement = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        if (isNavigationSupport && isSdkSupportSharedElement) {
            // 클릭리스너들의 지원은 오직 NavigationActivity 를 통해 조회되었을 때만 행위 수행.

            view.ivProfile.setOnClickListener { _navigationController.goToUserEnd(USER_ID) }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun initializeTransitionName(view : View) {
        view.ivProfile.transitionName = UserEndFragment.createProfileTransitionName(USER_ID)
    }
}
