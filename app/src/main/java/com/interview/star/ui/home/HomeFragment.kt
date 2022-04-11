package com.interview.star.ui.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.interview.star.R
import com.interview.star.databinding.FragmentHomeBinding
import com.interview.star.ui.base.BaseFragment
import com.interview.star.ui.detail.DetailFragment.Companion.USER_ARG
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    TextView.OnEditorActionListener {

    override val viewModel: HomeViewModel by viewModel()

    override val layoutRes = R.layout.fragment_home

    private var homeAdapter: HomeAdapter = HomeAdapter()

    override fun initView() {
        viewBinding.apply {
            rvUser.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = homeAdapter
            }

            ItemTouchHelper(itemTouchHelperCallback()).attachToRecyclerView(rvUser)

            edtSearch.setOnEditorActionListener(this@HomeFragment)
        }
    }

    override fun initData() {
        viewModel.fetchData()
    }

    override fun observeField() {
        viewModel.users.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                homeAdapter.submitList(it)
            }
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            viewModel.searchUser(viewBinding.edtSearch.text.toString())
        }
        return false
    }

    private fun itemTouchHelperCallback(): ItemTouchHelper.SimpleCallback {
        return object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                openDetailFragment(viewHolder)
            }
        }
    }

    private fun openDetailFragment(viewHolder: RecyclerView.ViewHolder) {
        val user = homeAdapter.getUserByPosition(viewHolder.adapterPosition)

        val bundle = Bundle()
        bundle.putParcelable(USER_ARG, user)

        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right)

        findNavController().navigate(R.id.nav_detail, bundle, navBuilder.build())
    }

}