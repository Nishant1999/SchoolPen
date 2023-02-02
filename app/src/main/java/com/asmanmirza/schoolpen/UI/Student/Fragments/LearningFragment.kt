package com.asmanmirza.schoolpen.UI.Student.Fragments

import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelLeaderBoard
import com.asmanmirza.schoolpen.UI.Student.Learn.Models.ModelLearnButtons
import com.asmanmirza.schoolpen.databinding.FragmentLearningBinding
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.UI.Student.Learn.Adapters.*
import com.asmanmirza.schoolpen.UI.Student.Learn.AddFeedQuestionActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import render.animations.Bounce
import render.animations.Render


class LearningFragment : Fragment(), View.OnTouchListener {

    private var _binding: FragmentLearningBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterLearnButtons: AdapterLearnButtons;
    var dX = 0f
    var dY = 0f
    private var lastAction = 0
    var firstTouch = false;
    var default_dx = 0f;
    var default_dy = 0f
    lateinit var render: Render;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearningBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render = Render(requireContext())
        binding.apply {
            /*HostFragment.instance.hideBottomNavBar(0)
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            HostFragment.instance.hideMenuIcon(true)
            HostFragment.instance.setNavBarColor("#00000000", "#259163D7")*/
            recLearnButtons.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            adapterLearnButtons = AdapterLearnButtons(requireContext(), getButtons());
            recLearnButtons.adapter = adapterLearnButtons
            binding.floatingButton.setOnClickListener {
                startActivity(Intent(requireContext(), AddFeedQuestionActivity::class.java))
            }
            getData(0)
            getChallenges()

            ItemClickSupport.addTo(recLearnButtons).setOnItemClickListener(object:OnItemClickListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    adapterLearnButtons.selected = position;
                    adapterLearnButtons.notifyDataSetChanged()
                    getData(position)
                }

            })

            Glide.with(requireContext()).load("https://i.pravatar.cc/300").thumbnail(0.8f).diskCacheStrategy(
                DiskCacheStrategy.NONE).into(imageWinner)

            scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val scrollBounds = Rect()
                scrollView.getHitRect(scrollBounds)
                if (isVisible(topExploreImage)) {
                    // The ImageView is Visible on the screen while scrolling
                    showFloatingRocket(false)
                } else {
                    showFloatingRocket(true)
                    // The ImageView is not Visible on the screen while scrolling
                }
            })
        }

    }

    fun getData(pos:Int){

        if(pos == 0){
            binding.layoutChallenges.visibility = View.VISIBLE
            getChallenges()
        }else{
            binding.layoutChallenges.visibility = View.GONE
        }
        if(pos == 1){
            binding.layoutFeeds.visibility = View.VISIBLE
            getFeeds()
        }else{
            binding.layoutFeeds.visibility = View.GONE
        }

        if(pos == 2){
            binding.layoutWorldKnowledge.visibility = View.VISIBLE
            getWorldKnowledge()
        }else{
            binding.layoutWorldKnowledge.visibility = View.GONE
        }
    }

    fun getChallenges(){
        binding.recLeaderboard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recLeaderboard.adapter = AdapterLeaderboardProfiles(requireContext(), getProfiles())
        binding.floatingButton.visibility = View.GONE
    }

    fun getFeeds(){
        binding.recFeeds.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recFeeds.adapter = AdapterFeeds(requireContext(), ArrayList())
        binding.floatingButton.visibility = View.VISIBLE
    }

    fun getWorldKnowledge(){
        binding.recWorldKnowledge.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recWorldKnowledge.adapter = AdapterWorldKnowledge(requireContext(), ArrayList())
        binding.floatingButton.visibility = View.GONE
    }


    fun getProfiles():ArrayList<ModelLeaderBoard>{

        return ArrayList<ModelLeaderBoard>().apply {

            add(ModelLeaderBoard("", "Jain Edward", "98.7%", ""))
            add(ModelLeaderBoard("", "Jahan Singh", "98.1%", ""))
            add(ModelLeaderBoard("", "Sonu Sharma", "98%", ""))
            add(ModelLeaderBoard("", "Sujata Mathur", "97.5%", ""))

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

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {

        when (event.actionMasked) {

            MotionEvent.ACTION_DOWN -> {
                dX = view.x - event.rawX
                dY = view.y - event.rawY
                lastAction = MotionEvent.ACTION_DOWN
                Toast.makeText(requireContext(), "down", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_MOVE -> {
                view.y = event.rawY + dY
                view.x = event.rawX + dX
                lastAction = MotionEvent.ACTION_MOVE
                if (!firstTouch) {
                    firstTouch = true;
                    default_dx = view.x
                    default_dy = view.y
                }
                Toast.makeText(requireContext(), "move", Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_UP -> {
                if (lastAction == MotionEvent.ACTION_DOWN) {
                    startActivity(Intent(requireContext(), AddFeedQuestionActivity::class.java))
                } /*else if (lastAction === MotionEvent.ACTION_MOVE) {
                    binding.floatingButton.x = default_dx;
                    binding.floatingButton.y = default_dy
                }*/else{
                    return false
                }
            }

        }
        return true
    }



    fun getButtons():ArrayList<ModelLearnButtons>{

        return ArrayList<ModelLearnButtons>().apply {
            add(ModelLearnButtons(R.drawable.challanges_vector, "Challenges"))
            add(ModelLearnButtons(R.drawable.feeds_vector, "Feeds"))
            add(ModelLearnButtons(R.drawable.world_knowledge, "World Knowledge"))
        }

    }

}