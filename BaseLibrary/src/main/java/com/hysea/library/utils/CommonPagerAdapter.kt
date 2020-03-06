package com.hysea.library.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * create by hysea on 2019/12/2
 */
class CommonPagerAdapter(
    fm: FragmentManager,
    private val fragments: List<Fragment>,
    private val titles: List<String>? = null
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (titles.isNullOrEmpty()) {
            return super.getPageTitle(position)
        }

        return titles[position]
    }
}