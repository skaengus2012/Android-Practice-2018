package nlab.practice.db.model.strategy

/**
 * 타격을 주는 행위 정의.
 *
 * @author ndh1002
 */
enum class Heat {
    Critical {
        override fun attack() = println("매우 강한 타격")
    },

    Normal {
        override fun attack() = println("일반 타")
    },

    Miss {
        override fun attack() = println("잘못된 타격")
    };

    /**
     * 타격 행위 정의.
     */
    abstract fun attack()
}