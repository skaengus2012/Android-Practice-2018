package nlab.practice.issue24

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nlab.practice.R
import nlab.practice.databinding.ActivityObservableDataBindingBinding

/**
 * Observable 데이터 테스트를 위한 화면 정의
 *
 * https://github.com/skaengus2012/Android-Practice-2018/issues/24
 *
 * @author Doohyun
 */
class ObservableDataBindingActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityObservableDataBindingBinding
    private lateinit var mViewModel : ObservableDataBindingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_observable_data_binding)
        mViewModel = ViewModelProviders.of(this).get(ObservableDataBindingViewModel::class.java)
        mBinding.viewModel = mViewModel
    }

    override fun onResume() {
        super.onResume()

        mViewModel.readUsers()
    }
}
