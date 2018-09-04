package nlab.practice.issue33

import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_shared_element_target.*
import nlab.practice.R
import nlab.practice.util.GlideApp

/**
 * SharedCurrentElement 를 눌렀을 때, 나타날 화면
 *
 * @author Doohyun
 */
private const val IMAGE_URL = "http://thumbnail.egloos.net/600x0/http://pds21.egloos.com/pds/201805/15/21/f0041321_5afaeddcd6541.jpg"

class SharedElementTargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_target)

        supportPostponeEnterTransition()

        GlideApp.with(this)
                .load(IMAGE_URL)
                .dontAnimate()
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()

                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()

                        return false
                    }
                })
                .into(ivProfile)
    }

}
