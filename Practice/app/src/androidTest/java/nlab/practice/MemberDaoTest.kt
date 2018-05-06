package nlab.practice

import Njava.util.time.TimeBuilder
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import nlab.practice.common.CodeDefinition
import nlab.practice.db.PracticeDatabase
import nlab.practice.db.dao.MemberDao
import nlab.practice.db.model.Member
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MemberDaoTest {

    private lateinit var database: PracticeDatabase
    private lateinit var memberDao : MemberDao

    /**
     * 데이터베이스 초기화.
     */
    @Before
    fun setDatabase() {
        val context = InstrumentationRegistry.getTargetContext()
        database = PracticeDatabase.createInstance(context)

        memberDao = database.memberDao()
    }

    @Test
    fun runDBByCRD() {
        Member(
                memberSn = 1,
                memberName = "남두현",
                genderFlag = CodeDefinition.GENDER_FLAG.Male,
                birthDay = TimeBuilder.Create("1990-10-02","yyyy-MM-dd").time,
                email = "skaengus2012@naver.com"
        ).let { memberDao.insert(it) }

        memberDao.find(1).let {
            assert((it != null))
            assert(it!!.memberName == "남두현")
        }

        memberDao.deleteByMemberSn(1)
        memberDao.find(1).let {
            assert((it == null))
        }

        println("테스트 성공.")
    }

    @After
    fun closeDataBase() = database.close()

}
