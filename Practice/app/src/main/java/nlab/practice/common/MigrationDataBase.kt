package nlab.practice.common

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

/**
 * DB Migration 에 필요한 파일 정의
 *
 * @author Doohyun
 * @since 2018. 09. 03
 */

/**
 * 마이그레이션 1-> 2
 *
 * - USER 테이블에 profile 필드가 추가됨
 */
val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE USER ADD COLUMN profile TEXT")
    }
}