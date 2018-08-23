package nlab.practice.issue34

import nlab.practice.common.model.Track

/**
 * Mock 으로 제공하는 PlayList
 *
 * @author Doohyun
 * @since 2018. 08. 23
 */
object PlayListMockManager {

    val playLists : List<Track> = arrayListOf(
            Track(
                    id = 1,
                    albumImg = "http://hiphople.com/files/attach/images/2531590/951/265/010/63065275a5c3fcebc65fb23e894a8507.jpg",
                    title = "Sample 1",
                    artist = "Doohyun"
            ),

            Track(
                    id = 1,
                    albumImg = "http://hiphople.com/files/attach/images/2531590/951/265/010/367a61d11c761d70276c339883da428a.jpg",
                    title = "Sample 2",
                    artist = "broduck"
            ),

            Track(
                    id = 1,
                    albumImg = "http://hiphople.com/files/attach/images/2531590/951/265/010/17d3f9f6dfac07da9077a2ac69025222.jpg",
                    title = "Sample 3",
                    artist = "Hyunji"
            ),

            Track(
                    id = 1,
                    albumImg = "http://hiphople.com/files/attach/images/2531590/951/265/010/4e973a5455a6fdaa524739e666258607.jpg",
                    title = "Sample 4",
                    artist = "윤호"
            )
    )

    var currentPosition : Int? = null

}