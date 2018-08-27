package nlab.practice.issue34

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nlab.practice.R
import nlab.practice.databinding.ActivityPlayListManageBinding

/**
 * AppWidget 과 동기화 하기 위한 화면 정의
 *
 * @author Doohyun
 */
class PlayListManageActivity : AppCompatActivity() {

    private lateinit var _viewModel : PlayListManageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityPlayListManageBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_play_list_manage)

        _viewModel = ViewModelProviders.of(this).get(PlayListManageViewModel::class.java)
                .apply { binding.viewModel = this }

    }

    override fun onResume() {
        super.onResume()

        _viewModel.initCurrentTrack()
    }
}
