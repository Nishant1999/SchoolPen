package com.asmanmirza.schoolpen.UI.Student.Fragments

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.AdapterEvents
import com.asmanmirza.schoolpen.Adapters.AdapterHomeDates
import com.asmanmirza.schoolpen.Adapters.AdapterHomeLiveClasses
import com.asmanmirza.schoolpen.Adapters.AdapterHomeTodaysClasses
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Helpers.ZoomOutPageTransformer
import com.asmanmirza.schoolpen.Models.ModelClasses
import com.asmanmirza.schoolpen.Models.ModelDates
import com.asmanmirza.schoolpen.Models.ModelEvents
import com.asmanmirza.schoolpen.Models.ModelLiveClasses
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Fee.ActivityFeePortal
import com.asmanmirza.schoolpen.UI.Student.Fee.ActivityStudentProfile
import com.asmanmirza.schoolpen.UI.Student.Home.CalanderActivity
import com.asmanmirza.schoolpen.UI.Student.Home.LiveClassesActivity
import com.asmanmirza.schoolpen.UI.Student.Home.NoticeActivity
import com.asmanmirza.schoolpen.UI.Student.StudentHome
import com.asmanmirza.schoolpen.UI.Student.chat.StudentChatHomeActivity
import com.asmanmirza.schoolpen.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterHomeDates: AdapterHomeDates;
    lateinit var db: TinyDB;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        instance = this
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = TinyDB(requireContext());
        view.findViewById<TextView>(R.id.tv_user).text = db.getString("username")
        binding.apply {
           /* MainActivity.instance.updateStatusBarColor("#99F86005")
            HostFragment.instance.hideBottomNavBar(0)
            HostFragment.instance.setNavBarColor("#00000000", "#99f86005")
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)*/

            btnViewAllLiveClasses.setOnClickListener {
                startActivity(Intent(requireContext(), LiveClassesActivity::class.java))
            }

            with(viewPagerLiveClasses){
                adapter = AdapterHomeLiveClasses(requireContext(), getLiveClasses())
                setPageTransformer(true, ZoomOutPageTransformer())
                dotsIndicator.attachTo(this)
            }
            with(viewPagerTodaysClasses){
                adapter = AdapterHomeTodaysClasses(requireContext(), getTodayClasses(), R.drawable.back_todays_classes)
                setPageTransformer(true, ZoomOutPageTransformer())
                dotsIndicator1.attachTo(this)
            }
            with(viewPagerTomorrowClasses){
                adapter = AdapterHomeTodaysClasses(requireContext(), getTomorrowClasses(), R.drawable.back_todays_classes)
                setPageTransformer(true, ZoomOutPageTransformer())
                dotsIndicator2.attachTo(this)
            }

            btnViewAllNotices.setOnClickListener {
                startActivity(Intent(requireContext(), NoticeActivity::class.java))
            }


            recDates.layoutManager = GridLayoutManager(requireContext(), 5)
            adapterHomeDates =  AdapterHomeDates(requireContext(), getDates(), "#9163d7");
            recDates.adapter = adapterHomeDates

            homeScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                /*HostFragment.instance.hideDp(isViewVisible(homeDp1))
                HostFragment.instance.hideTopButtons(isViewVisible(layoutTopButtons))
                if(isViewVisible(homeDp1)){
                    HostFragment.instance.setNavBarColor("#00000000", "#99F86005");
                }else{
                    HostFragment.instance.setNavBarColor("#E4874F", "#E4874F");
                }*/

            })

            ivAlert.setOnClickListener {
                startActivity(Intent(requireContext(), ActivityStudentProfile::class.java))
            }

            ivMenu.setOnClickListener {
                (activity as StudentHome?)?.openDrawer()
            }

            ivMessage.setOnClickListener {
                startActivity(Intent(requireContext(), StudentChatHomeActivity::class.java))
            }

            btnOpenCalendar.setOnClickListener {
                /*MainActivity.instance.updateStatusBarColor("#ffffff")
                findNavController().navigate(R.id.action_homeFragment_to_calendarFragment)*/
                startActivity(Intent(requireContext(), CalanderActivity::class.java))
            }

            recEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addEvents()
            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    when (tab?.position) {
                        0 -> {
                            addEvents()
                        }
                        1 -> {
                            addTasks()
                        }
                        2 -> {
                            addBirthdays()
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    fun getLiveClasses():ArrayList<ModelLiveClasses>{
        return ArrayList<ModelLiveClasses>().apply{
            add(ModelLiveClasses("", "History of India", "Sonu Sharma", "Social Science", "21", ""))
            add(ModelLiveClasses("", "Algebraic Expressions", "Nani Mathur", "Mathematics", "45", ""))
            add(ModelLiveClasses("", "Chemical Names", "D Jain", "Science", "32", ""))
            add(ModelLiveClasses("", "Q&A Session", "S Solanki", "English", "16", ""))
        }
    }
    fun getTodayClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }
    fun getTomorrowClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }

    fun getDates():ArrayList<ModelDates>{

        return ArrayList<ModelDates>().apply {
            add(ModelDates(9, "09/01/2023", false))
            add(ModelDates(10, "10/01/2023", false))
            add(ModelDates(11, "11/01/2023", false))
            add(ModelDates(12, "12/01/2023", false))
            add(ModelDates(13, "13/01/2023", false))
        }

    }
    fun addEvents(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(12, 1, "Annual sports meet", "All Day", "", "event"))
        events.add(ModelEvents(13, 1, "Award ceremony", "14:00-17:00", "", "event"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addTasks(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(17, 1, "Ankita Sharma assignment", "05:00 PM", "High", "task"))
        events.add(ModelEvents(18, 1, "Read Ch-2 B V Ramana", "05:00 PM", "Med", "task"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addBirthdays(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(23, 1, "Akhil's Birthday 🎂", "10:00 AM", "", "birthday"))
        events.add(ModelEvents(27, 1, "Principal Sir Birthday 🎂", "01:00 AM", "", "birthday"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }
    fun scrollToTop(){
        binding.homeScroll.fullScroll(NestedScrollView.FOCUS_UP)
    }

    companion object {
        lateinit var instance: HomeFragment
            private set
    }


    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.homeScroll.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }
}