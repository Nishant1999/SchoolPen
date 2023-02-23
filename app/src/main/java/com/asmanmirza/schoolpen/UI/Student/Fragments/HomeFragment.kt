package com.asmanmirza.schoolpen.UI.Student.Fragments

import android.app.Application
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.Adapters.*
import com.asmanmirza.schoolpen.Helpers.ApiClient
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.Helpers.ZoomOutPageTransformer
import com.asmanmirza.schoolpen.Models.*
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.Models.TestData
import com.asmanmirza.schoolpen.UI.Student.Fee.ActivityStudentProfile
import com.asmanmirza.schoolpen.UI.Student.Home.CalanderActivity
import com.asmanmirza.schoolpen.UI.Student.Home.LiveClassesActivity
import com.asmanmirza.schoolpen.UI.Student.Home.Models.DataUserId
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelClassUserId
import com.asmanmirza.schoolpen.UI.Student.Home.Models.ModelTeacherNote
import com.asmanmirza.schoolpen.UI.Student.Home.Models.TeacherNote
import com.asmanmirza.schoolpen.UI.Student.Home.NoticeActivity
import com.asmanmirza.schoolpen.UI.Student.Home.NotificationActivity
import com.asmanmirza.schoolpen.UI.Student.Home.viewmodel.ViewModelHome
import com.asmanmirza.schoolpen.UI.Student.StudentHome
import com.asmanmirza.schoolpen.UI.Student.chat.StudentChatHomeActivity
import com.asmanmirza.schoolpen.UI.Student.models.HomeViewModelFactory
import com.asmanmirza.schoolpen.UI.Student.models.ModelUserPeriod
import com.asmanmirza.schoolpen.UI.Student.models.Period
import com.asmanmirza.schoolpen.UI.Student.repository.HomeRepository
import com.asmanmirza.schoolpen.UI.Student.retrofit.MyApi
import com.asmanmirza.schoolpen.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterHomeDates: AdapterHomeDates;
    lateinit var db: TinyDB;

    lateinit var myApi: MyApi
    lateinit var data: ArrayList<TodayliveClassDtos>
    lateinit var periodData: ArrayList<Period>
    lateinit var periodDataTomorrow: ArrayList<Period>
    lateinit var homeViewModel: ViewModelHome
    lateinit var milliSecond:ArrayList<Long>
    lateinit var noticeData: ArrayList<Data>
    lateinit var dataUserId: ArrayList<DataUserId>

//    lateinit var teacherNote: ArrayList<Long>
    val milliSecondHashmap = HashMap<Long, Long>()

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory


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
        //homeDetailViewModel=ViewModelProvider(this)
        homeViewModelFactory =
            HomeViewModelFactory(HomeRepository(context?.applicationContext as Application))
        db = TinyDB(requireContext())
        myApi = ApiClient.getClient()?.create(MyApi::class.java)!!
        dataUserId = ArrayList()
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
          delay(5000)
            Toast.makeText(
                requireContext(),
                "classIDDD" + db.getString("classId").toDouble().toInt(),
                Toast.LENGTH_LONG
            ).show()
        }
        /*Toast.makeText(
            requireContext(),
            "classIdOutside" + db.getString("classId").toDouble().toInt(),
            Toast.LENGTH_LONG
        ).show()*/


//        Toast.makeText(requireContext(),""+db.getString("schoolId").toDouble().toInt(),Toast.LENGTH_LONG).show()

        milliSecond = ArrayList()
        data = ArrayList()
        periodData = ArrayList()
        periodDataTomorrow = ArrayList()
        noticeData = ArrayList()
        view.findViewById<TextView>(R.id.tv_user).text = db.getString("username")

        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory)[ViewModelHome::class.java]
        val token = db.getString("token")

        homeViewModel.getTodayLiveData(
            "Bearer $token"
        )

        // homeViewModel.getPeriodClassId(db.getString("classId").toDouble().toInt(),"Bearer $token")

        binding.apply {
            /* MainActivity.instance.updateStatusBarColor("#99F86005")
            HostFragment.instance.hideBottomNavBar(0)
            HostFragment.instance.setNavBarColor("#00000000", "#99f86005")
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)*/

            btnViewAllLiveClasses.setOnClickListener {
                startActivity(Intent(requireContext(), LiveClassesActivity::class.java))
            }

            with(viewPagerLiveClasses) {
                //data=getLiveClasses()


                    homeViewModel.todayData.observe(viewLifecycleOwner) {
                        /*Toast.makeText(
                            requireContext(),
                            "class 1" + db.getString("classId").toDouble().toInt(),
                            Toast.LENGTH_LONG
                        ).show()*/
                        data.addAll(it)
                        adapter = AdapterHomeLiveClasses(requireContext(), data)
                        setPageTransformer(true, ZoomOutPageTransformer())
                        dotsIndicator.attachTo(binding.viewPagerLiveClasses)


                }

                }


            with(viewPagerTodaysClasses) {
                    coroutineScope.launch {
                   //  delay(10000)

                        Toast.makeText(
                            requireContext(),
                            "classIdToday" + db.getString("classId").toDouble().toInt(),
                            Toast.LENGTH_LONG
                        ).show()
                        myApi.getPeriodByClassId(
                            db.getString("classId").toDouble().toInt(),
                            "Bearer" + " " + db.getString("token")
                        ).enqueue(object : Callback<ModelUserPeriod> {
                            override fun onResponse(
                                call: Call<ModelUserPeriod>,
                                response: Response<ModelUserPeriod>
                            ) {
                                if (response.isSuccessful) {

                                    Log.d("+++respose", response.body().toString())
                                    val test = response.body()?.data!!.period as ArrayList<Period>
                                    for (i in test) {
                                        periodDataTomorrow.add(i)
                                    }
                                    adapter = AdapterHomeTodaysClasses(
                                        requireContext(),
                                        periodDataTomorrow,
                                        R.drawable.back_todays_classes
                                    )
                                    setPageTransformer(true, ZoomOutPageTransformer())
                                    binding.dotsIndicator1.attachTo(viewPagerTodaysClasses)
                                } else {
                                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }

                            override fun onFailure(call: Call<ModelUserPeriod>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                    }

            }

            with(viewPagerTomorrowClasses) {
                coroutineScope.launch {

                  // delay(10000)

                    Toast.makeText(
                        requireContext(),
                        "classIDTommoroow" + db.getString("classId").toDouble().toInt(),
                        Toast.LENGTH_LONG
                    ).show()
                    myApi.getPeriodByClassId(
                        db.getString("classId").toDouble().toInt(),
                        "Bearer" + " " + db.getString("token")
                    ).enqueue(object : Callback<ModelUserPeriod> {
                        override fun onResponse(
                            call: Call<ModelUserPeriod>,
                            response: Response<ModelUserPeriod>
                        ) {
                            if (response.isSuccessful) {

                                Log.d("+++respose", response.body().toString())
                                val test = response.body()?.data!!.period as ArrayList<Period>
                                for (i in test) {
                                    periodData.add(i)
                                }
                                adapter = AdapterHomeTodaysClasses(
                                    requireContext(),
                                    periodData,
                                    R.drawable.back_todays_classes
                                )
                                setPageTransformer(true, ZoomOutPageTransformer())
                                binding.dotsIndicator2.attachTo(viewPagerTomorrowClasses)
                            } else {
                                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<ModelUserPeriod>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                }

            }


            noticeDetails()
            teacherNote()



            btnViewAllNotices.setOnClickListener {
                startActivity(Intent(requireContext(), NoticeActivity::class.java))
            }

            val calendar = Calendar.getInstance()

            val monthText = DateFormatSymbols().months[calendar.get(Calendar.MONTH)]
            val yearText = calendar.get(Calendar.YEAR).toString()

            val monthYearText = "$monthText $yearText"

            val formatter = SimpleDateFormat("dd/MM/yyyy")

            val latestDates = mutableListOf<String>()
            for (i in -5..6) {
                calendar.add(Calendar.DATE, i)
                if (i in 0..4) {
                    latestDates.add(formatter.format(calendar.time))
                }
                calendar.add(Calendar.DATE, -i)
            }


            //Toast.makeText(context,latestDates.toString(),Toast.LENGTH_LONG).show()


            binding.btnOpenCalendar.text = monthYearText


            recDates.layoutManager = GridLayoutManager(requireContext(), 5)
            adapterHomeDates = AdapterHomeDates(requireContext(), latestDates, "#9163d7");
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
                startActivity(Intent(requireContext(), NotificationActivity::class.java))
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

            recEvents.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addEvents()
            tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

    private fun teacherNote() {


        myApi.getAllTeacherNote("Bearer"+" "+db.getString("token")).enqueue(object:Callback<ModelTeacherNote>{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<ModelTeacherNote>,
                response: Response<ModelTeacherNote>
            ) {
                val t= response.body()?.data?.teacherNotes
                if (t != null) {
                    for(i in t){
                        val formatter = DateTimeFormatter.ISO_DATE_TIME
                        val dateTime = LocalDateTime.parse(i.createDateTime, formatter)
                        val date = Date.from(dateTime.toInstant(ZoneOffset.UTC))
                        val formatterString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        val dateString = formatterString.format(date)
                        val dateFormatterString=formatterString.parse(dateString)



                        val timeInMillisFromDateNumber = dateFormatterString.time


                        if(System.currentTimeMillis()>=timeInMillisFromDateNumber) {
                            milliSecondHashmap.put(System.currentTimeMillis()-timeInMillisFromDateNumber,timeInMillisFromDateNumber)
                            //teacherNote.add(timeInMillisFromDateNumber)
                        }
                    }
                    var t1:Long=0L
                    val sortedMap: MutableMap<Long, Long> = TreeMap(milliSecondHashmap)
                    for ((key, value) in sortedMap) {
                        t1=value
                        break
                    }
                    //Toast.makeText(requireContext(),""+teacherNote.toString(),Toast.LENGTH_LONG).show()


                   // Toast.makeText(requireContext(),""+t1.toString(),Toast.LENGTH_LONG).show()
                    val date=Date(t1)

                   // Toast.makeText(requireContext(),""+date.toString(),Toast.LENGTH_LONG).show()

                    val simpleDateFormat1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val dateStringFormat1 = simpleDateFormat1.format(date)
                    val dateFormatterString=simpleDateFormat1.parse(dateStringFormat1)


                    val timeInMilli = dateFormatterString.time

                    val timeDifferenceMillis = System.currentTimeMillis() - timeInMilli
                    val timeDifferenceHours = TimeUnit.MILLISECONDS.toHours(timeDifferenceMillis)
                    val formattedDuration = formatDuration(timeDifferenceHours)
                    binding.itemTeacher.itemTeacherTime.text=formattedDuration

                    for(i in t){
                        if(sortedMap.containsValue(t1)){
                            binding.itemTeacher.itemTeacherName.text=i.teacher.name
                            binding.itemTeacher.itemTeacherSubject.text="Science"  //This has to be changed
                            binding.itemTeacher.itemTeacherDesc.text=i.teacherNote
                        }
                    }
                }
                else{
                    Toast.makeText(requireContext(),"dateString",Toast.LENGTH_LONG).show()
                }



            }

            override fun onFailure(call: Call<ModelTeacherNote>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun noticeDetails() {



            myApi.getNotice(
                db.getString("schoolId").toDouble().toInt(),
                "Bearer" + " " + db.getString("token")
            ).enqueue(object : Callback<ModelNotice> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<ModelNotice>, response: Response<ModelNotice>) {
                    if (response.isSuccessful) {

                        val res: List<Data> = response.body()!!.data

                        for (i in res) {
                            var date: Date
                            val formatter = SimpleDateFormat("yyyy-MM-dd")
                            try {
                                date = formatter.parse(i.date)
                                val timeInMilliseconds = date.time

                                val calendar = Calendar.getInstance()

                                if (timeInMilliseconds >= calendar.timeInMillis) {
                                    milliSecond.add(timeInMilliseconds)
                                }
                            } catch (e: ParseException) {
                                e.printStackTrace()
                            }
                        }

                        milliSecond.sort()

                        val t = milliSecond[0]
                        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                        val dateString = simpleDateFormat.format(t)


                        for (i in res) {
                            if (dateString.equals(i.date)) {
                                binding.layoutNotices.noticeDate.text = i.date
                                binding.layoutNotices.noticeTitle.text = i.heading
                            }
                        }
                        //Toast.makeText(context,""+dateString.toString(),Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


                override fun onFailure(call: Call<ModelNotice>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Internal server error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }


    fun getLiveClasses() {
       /* return ArrayList<ModelLiveClasses>().apply{
            add(ModelLiveClasses("", "History of India", "Sonu Sharma", "Social Science", "21", ""))
            add(ModelLiveClasses("", "Algebraic Expressions", "Nani Mathur", "Mathematics", "45", ""))
            add(ModelLiveClasses("", "Chemical Names", "D Jain", "Science", "32", ""))
            add(ModelLiveClasses("", "Q&A Session", "S Solanki", "English", "16", ""))
        }*/
    }
/*    fun getTodayClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }*/
/*    fun getTomorrowClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }*/

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

    fun formatDuration(duration: Long): String {
        val days = TimeUnit.HOURS.toDays(duration)
        val hours = duration - TimeUnit.DAYS.toHours(days)
        return when {
            days >= 1 -> "$days days ago"
            else -> "$hours hours ago"
        }
    }
}