package nlab.practice.common

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import nlab.practice.PracticeApplication
import nlab.practice.common.dao.UserDao
import nlab.practice.common.model.User

/**
 * 이 앱에서 사용하는 로컬 DB 프로퍼티를 정의
 *
 * @author Doohyun
 */
@Database(entities = [User::class], version = CodeDefinition.DB_INFO.VERSION)
abstract class PracticeDataBase : RoomDatabase() {

    companion object {
        private val instance : PracticeDataBase =
                Room.databaseBuilder(
                            PracticeApplication.getContext(),
                            PracticeDataBase::class.java,
                            CodeDefinition.DB_INFO.NAME)
                        .build()

        fun getInstance() : PracticeDataBase = instance
    }

    /**
     * UserDao 출력.
     *
     * @return
     */
    abstract fun userDao() : UserDao
}