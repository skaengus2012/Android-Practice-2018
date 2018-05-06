package nlab.practice.common;

/**
 * 코드 정의 항목.
 *
 * @author ndh1002
 */
public class CodeDefinition {

    public static final class CODE {
        public static final int DB_INFO = 1;            // (1) DB 프로퍼티 정의.
        public static final int GENDER_FLAG = 2;        // (2) 성별
    }

    /**
     * (1) DB 프로퍼티 정의.
     */
    public static final class DB_INFO {
        public static final String NAME ="practice-db";     // DB 이름
        public static final int VERSION = 1;                // 버전
    }

    /**
     * (2) 성별
     */
    public static final class GENDER_FLAG {
        public static final String Male = "CODE_GENDER_FLAG_MALE";          // 남자
        public static final String FeMale = "CODE_GENDER_FLAG_FEMALE";      // 여자
    }
}
