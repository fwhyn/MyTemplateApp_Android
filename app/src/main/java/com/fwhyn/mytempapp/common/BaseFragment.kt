package com.fwhyn.mytempapp.common

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFragment : Fragment() {

    companion object {
        fun FragmentManager.showFragment(
            fragment: Fragment,
            tag: String
        ) {
            val fragmentTransaction = beginTransaction()
            fragmentTransaction
                .add(fragment, tag)
                .commit()
        }

        fun FragmentManager.showFragment(
            @IdRes containerViewId: Int,
            fragment: Fragment,
            tag: String
        ) {
            val fragmentTransaction = beginTransaction()
            fragmentTransaction
                .replace(containerViewId, fragment, tag)
                .commit()
        }

        fun FragmentManager.dismissFragment(
            tag: String
        ) {
            val fragment = findFragmentByTag(tag)
            fragment?.let {
                val fragmentTransaction = beginTransaction()
                fragmentTransaction
                    .remove(it)
                    .commit()
            }
        }
    }

    // ----------------------------------------------------------------
    override fun onDestroy() {
        super.onDestroy()


    }

    // ----------------------------------------------------------------
    interface OnFragmentResultCallback {
        fun onFragmentResult(
            tag: String,
            result: FragmentResult = FragmentResult.CANCELED,
            args: Bundle? = null,
        )
    }

    interface OnDestroyCallback {
        fun onDestroy(tag: String)
    }

    // ----------------------------------------------------------------
    enum class FragmentResult {
        OK,
        CANCELED,
    }
}