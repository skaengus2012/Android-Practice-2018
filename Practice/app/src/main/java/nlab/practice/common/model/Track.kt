package nlab.practice.common.model

/**
 * Track 데이터 정의
 *
 * @author Doohyun
 * @since 2018. 08. 23
 */
data class Track(
        val id : Int,
        var albumImg : String? = null,
        var title : String? = null,
        var artist : String? = null
)