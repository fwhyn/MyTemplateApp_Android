package com.fwhyn.mytempapp.common.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.fwhyn.mytempapp.common.BaseFragment.Companion.dismissFragment

class CustomDialog private constructor() : DialogFragment() {

    companion object {

        private const val PARENT_FRAGMENT_TAG = "PARENT_FRAGMENT_TAG"

        fun show(fragmentManager: FragmentManager, tag: String, parentFragmentTag: String? = null) {
            val dialogFragment = CustomDialog()

            parentFragmentTag?.let {
                val arguments = Bundle()
                arguments.putString(PARENT_FRAGMENT_TAG, it)
                dialogFragment.arguments = arguments
            }

            dialogFragment.show(fragmentManager, tag)
        }

        fun dismiss(fragmentManager: FragmentManager, tag: String) {
            fragmentManager.dismissFragment(tag)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var fragment: Fragment? = null
        arguments?.let { fragment = getFragment(it) }

        val dialogInterface: OnCreateDialogCallback? =
            fragment as? OnCreateDialogCallback ?: activity as? OnCreateDialogCallback

        return dialogInterface?.onCreateDialog(tag) ?: super.onCreateDialog(savedInstanceState)
    }

    private fun getFragment(args: Bundle): Fragment? {
        val fragmentManager = requireActivity().supportFragmentManager
        val parentFragmentTag = args.getString(PARENT_FRAGMENT_TAG)

        return fragmentManager.findFragmentByTag(parentFragmentTag)
    }

    // ----------------------------------------------------------------
    interface OnCreateDialogCallback {
        fun onCreateDialog(tag: String?): Dialog?
    }
}