package nlab.practice.db

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import nlab.practice.PracticeApplication
import nlab.practice.common.CodeDefinition
import nlab.practice.db.dao.MemberDao
import nlab.practice.db.model.Member

/**
 * 연습 디비에 대한 정보 정의.
 */
@Database(entities = [Member::class], version = CodeDefinition.DB_INFO.VERSION)
abstract class PracticeDatabase : RoomDatabase() {

    private class ManagerHolder {
        companion object {
            private val INSTANCE = PracticeDatabase.createInstance(PracticeApplication.getContext())
        }
    }

    companion object {
        fun createInstance(context : Context) : PracticeDatabase
                = Room.databaseBuilder(
                        context
                        , PracticeDatabase::class.java
                        , CodeDefinition.DB_INFO.NAME)
                        .build()

    }

    abstract fun memberDao() : MemberDao

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}