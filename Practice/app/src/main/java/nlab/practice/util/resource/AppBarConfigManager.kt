package nlab.practice.util.resource

import android.app.Activity
import nlab.practice.R
import nlab.practice.issue10.DocumentCentricActivity
import nlab.practice.issue8.DevTutorialActivity
import nlab.practice.issue9.DataConserveActivity
import nlab.practice.issue20.FragmentChangeActivity
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
                    DocumentCentricActivity::class to convertString(R.string.title_document_centric)
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