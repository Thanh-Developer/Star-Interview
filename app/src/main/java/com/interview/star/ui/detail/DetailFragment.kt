package com.interview.star.ui.detail

import com.interview.star.R
import com.interview.star.databinding.FragmentDetailBinding
import com.interview.star.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModel()

    override val layoutRes = R.layout.fragment_detail

    override fun initView() {
        viewBinding.apply {
            viewModel = this@DetailFragment.viewModel
        }
    }

    override fun initData() {
        arguments?.let {
            viewModel.apply {
                user.value = it.getParcelable(USER_ARG)
                user.value?.let {
                    getProfile(it.login)
                }
            }
        }
    }

    override fun observeField() {
        viewModel.apply {
            profile.observe(viewLifecycleOwner) { profile ->
                viewBinding.profile = profile
            }

            isBackPress.observe(viewLifecycleOwner) {
                if (it == true) {
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    companion object {
        const val USER_ARG = "USER_ARG"
    }
}
