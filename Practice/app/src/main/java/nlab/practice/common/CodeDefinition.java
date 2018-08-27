package nlab.practice.common;

/**
 * 코드 정의 항목.
 *
 * @author ndh1002
 */
public final class CodeDefinition {

    public static final class CODE {
        public static final int DB_INFO = 1;            // (1) DB 프로퍼티 정의.
        public static final int SCHEME_INFO = 2;        // (2) 스킴정보 정의
        public static final int ACTION_INTO = 3;        // (3) ACTION 정보 정의
        public static final int GENDER_FLAG = 4;        // (4) 성별
    }

    /**
     * (1) DB 프로퍼티 정의.
     */
    public static final class DB_INFO {
        public static final String NAME ="practice-db";     // DB 이름
        public static final int VERSION = 1;                // 버전
    }

    /**
     * (2) 스킴정보 정의
     */
    public static final class SCHEME_INFO {
        public static final String SCHEME = "practice";           // 스킴명
        public static final String HOST = "open";               // 호스트명
        public static final String QUERY_TARGET = "target";     // 쿼리명 (타겟)
    }

    /**
     * (3) ACTION 정보 정의
     */
    public static final class ACTION_INTO {
        public static final String GO_PLAYLIST = "goPlayList"; // 플레이리스트로 이동
    }

    /**
     * (4) 성별
     */
    public static final class GENDER_FLAG {
        public static final String Male = "CODE_GENDER_FLAG_MALE";          // 남자
        public static final String FeMale = "CODE_GENDER_FLAG_FEMALE";      // 여자
    }
}
