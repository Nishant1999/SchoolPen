package com.asmanmirza.schoolpen.UI.Teacher.Fragments

import android.animation.AnimatorSet
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.core.widget.NestedScrollView.OnScrollChangeListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.UI.Student.Learn.Adapters.AdapterWorldKnowledge
import com.asmanmirza.schoolpen.databinding.FragmentTeacherExploreBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import render.animations.Bounce
import render.animations.Render
import render.animations.Slide


class TeacherExploreFragment : Fragment() {

    private var _binding: FragmentTeacherExploreBinding? = null
    private val binding get() = _binding!!
    lateinit var render: Render;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherExploreBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render = Render(requireContext())
        binding.apply {
            getWorldKnowledge()
            tabs.getTabAt(0)?.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
            tabs.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                    when(tab?.position){

                        0->{
                            getWorldKnowledge()
                        }
                        1->{
                            tabs.getTabAt(0)?.select()
                            Toast.makeText(requireContext(), "You only can view first section", Toast.LENGTH_SHORT).show()
                        }
                        2->{
                            tabs.getTabAt(0)?.select()
                            Toast.makeText(requireContext(), "You only can view first section", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(Color.parseColor("#707070"), PorterDuff.Mode.SRC_IN)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

            scrollView.setOnScrollChangeListener(OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val scrollBounds = Rect()
                scrollView.getHitRect(scrollBounds)
                if (isVisible(topExploreImage)) {
                    // The ImageView is Visible on the screen while scrolling
                    showFloatingRocket(false)
                    println("------------visible")
                } else {
                    showFloatingRocket(true)
                    println("------------not visibile")
                    // The ImageView is not Visible on the screen while scrolling
                }
            })
        }
    }

    fun isVisible(view: View?): Boolean {
        if (view == null) {
            return false
        }
        if (!view.isShown) {
            return false
        }
        val actualPosition = Rect()
        view.getGlobalVisibleRect(actualPosition)
        val screen = Rect(0, 0, Resources.getSystem().displayMetrics.widthPixels, Resources.getSystem().displayMetrics.heightPixels)
        return actualPosition.intersect(screen)
    }


    fun showFloatingRocket(show:Boolean){
        if(show){
            if(binding.floatingImage.visibility != View.VISIBLE) {
                binding.floatingImage.visibility = View.VISIBLE
                startAnimation(Bounce.InDown(binding.floatingImage), 500)
            }
        }else{
            if(binding.floatingImage.visibility == View.VISIBLE) {
                binding.floatingImage.visibility = View.GONE
            }
        }
    }

    fun startAnimation(anim: AnimatorSet, duration: Long) {
        render.setAnimation(anim)
        render.setDuration(duration)
        render.start()
    }
    fun getWorldKnowledge(){
        binding.recWorldKnowledge.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recWorldKnowledge.adapter = AdapterWorldKnowledge(requireContext(), ArrayList())
    }

}