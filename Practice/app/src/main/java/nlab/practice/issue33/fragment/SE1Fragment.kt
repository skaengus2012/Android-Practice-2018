package nlab.practice.issue33.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_se1.*
import kotlinx.android.synthetic.main.fragment_se1.view.*

import nlab.practice.R
import nlab.practice.util.GlideApp

/**
 * Shared Element 첫번째 페이지
 *
 * @author Doohyun
 */
class SE1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_se1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.layoutFragment, SE2Fragment(), SE1Fragment::class.java.canonicalName)
                    ?.setReorderingAllowed(true)
                    ?.addSharedElement(view.ivPhoto, ViewCompat.getTransitionName(view.ivPhoto))
                    ?.addToBackStack(null)
                    ?.commit()
        }

        GlideApp.with(this)
                .load(SHARED_ELEMENT_FRAGMENT_IMAGE_URL)
                .into(view.ivPhoto)
    }
}
