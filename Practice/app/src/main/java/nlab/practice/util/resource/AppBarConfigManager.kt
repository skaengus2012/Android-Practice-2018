package nlab.practice.util.resource

import android.app.Activity
import nlab.practice.R
import nlab.practice.issue10.DocumentCentricActivity
import nlab.practice.issue11.CustomViewTutorialActivity
import nlab.practice.issue12.ConstraintLayoutTutorialActivity
import nlab.practice.issue13.ConstraintSetTutorialActivity
import nlab.practice.issue14.AppBarTutorialActivity
import nlab.practice.issue15.ViewStubTutorialActivity
import nlab.practice.issue16.ShadowClippingActivity
import nlab.practice.issue17.CustomToolBarTutorialActivity
import nlab.practice.issue18.DragAndDropTutorialActivity
import nlab.practice.issue8.DevTutorialActivity
import nlab.practice.issue9.DataConserveActivity
import nlab.practice.issue20.FragmentChangeActivity
import nlab.practice.issue22.UserActivity
import nlab.practice.issue24.LiveEventTutorialActivity
import nlab.practice.issue24.ObservableDataBindingActivity
import nlab.practice.issue27.RecyclerOptimizeTestActivity
import kotlin.reflect.KClass

/**
 * 액티비티의 설정 정보를 가진 매니저 정의
 *
 * @author ndh1002
 */
object AppBarConfigManager {

    /**
     * activity 명에 대한 라벨 그룹.
     */
    private val titleGroup : Map<KClass<out Activity>, String> =
            linkedMapOf(
                    DevTutorialActivity::class to convertString(R.string.title_dev_tutorial),
                    DataConserveActivity::class to convertString(R.string.title_data_conserve),
                    FragmentChangeActivity::class to convertString(R.string.title_fragment_changed),
                    DocumentCentricActivity::class to convertString(R.string.title_document_centric),
                    CustomViewTutorialActivity::class to convertString(R.string.title_custom_view),
                    ConstraintLayoutTutorialActivity::class to convertString(R.string.title_constraint_layout),
                    ConstraintSetTutorialActivity::class to convertString(R.string.title_constraint_set_animation),
                    AppBarTutorialActivity::class to convertString(R.string.title_app_bar_tutorial),
                    CustomToolBarTutorialActivity::class to convertString(R.string.title_app_bar_custom_bar),
                    ViewStubTutorialActivity::class to convertString(R.string.title_view_stub_tutorial),
                    ShadowClippingActivity::class to convertString(R.string.title_shadow_and_clipping_tutorial),
                    DragAndDropTutorialActivity::class to convertString(R.string.title_drag_and_drop_tutorial),
                    UserActivity::class to convertString(R.string.title_aac_tutorial),
                    ObservableDataBindingActivity::class to convertString(R.string.title_observable_data_binding_tutorial),
                    LiveEventTutorialActivity::class to convertString(R.string.title_action_live_event_tutorial),
                    RecyclerOptimizeTestActivity::class to convertString(R.string.title_recycler_view_optimize)
            )

    /**
     * 액티비티 [clazz] 를 받아 라벨이 있는지 확인
     *
     * @param clazz
     * @return
     */
    fun isHaveLabel(clazz: KClass<out Activity>) : Boolean = clazz in titleGroup

    /**
     * 액티비티 [clazz] 를 받아 라벨을 출력한다.
     *
     * @param clazz
     * @return
     */
    fun getLabel(clazz: KClass<out Activity>) : String? = titleGroup[clazz]

    /**
     * 등록된 액티비티 클래스들을 출력한다.
     *
     * @return
     */
    fun getActivityKClassis() : List<KClass<out Activity>> = titleGroup.keys.toList()
}