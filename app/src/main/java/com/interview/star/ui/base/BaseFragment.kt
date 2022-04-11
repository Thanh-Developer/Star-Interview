package com.interview.star.ui.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.interview.star.R
import com.interview.star.utils.DialogUtils
import com.interview.star.utils.EspressoIdlingResource

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    abstract val viewModel: ViewModel

    abstract val layoutRes: Int

    lateinit var dialog: ProgressDialog

    lateinit var viewBinding: ViewBinding

    open fun initView() {}

    open fun initData() {}

    open fun observeField() {}

    private var messageDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initView()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            root.isClickable = true
            executePendingBindings()
        }
        lifecycle.addObserver(viewModel)
        initData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    EspressoIdlingResource.increment()
                    showLoadingFragment()
                } else {
                    EspressoIdlingResource.decrement()
                    hideLoadingFragment()
                }
            }

            errorMessage.observe(viewLifecycleOwner) { message ->
                hideLoadingFragment()
                if (!message.isNullOrEmpty()) {
                    handleShowErrorMessage(message)
                }
            }

        }

        observeField()
    }

    private fun showLoadingFragment() {
        dialog = ProgressDialog.show(
            activity,
            resources.getString(R.string.title_dialog),
            resources.getString(R.string.message_dialog),
            true
        )
    }

    private fun hideLoadingFragment() {
        dialog.dismiss()
    }

    open fun handleShowErrorMessage(message: String) {
        if (messageDialog != null || messageDialog?.isShowing == true) return
        messageDialog = DialogUtils.showMessage(
            context = context,
            message = message,
            textPositive = getString(R.string.btn_ok),
            positiveListener = DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
                messageDialog = null
            }
        )
    }

}