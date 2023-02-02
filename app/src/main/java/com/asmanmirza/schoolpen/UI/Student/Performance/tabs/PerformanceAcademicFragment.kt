package com.asmanmirza.schoolpen.UI.Student.Performance.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Performance.AdapterSubjectWiseMarks
import com.asmanmirza.schoolpen.UI.Student.Performance.ModelSubjectMarks
import com.asmanmirza.schoolpen.databinding.FragmentPerformanceAcademicBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class PerformanceAcademicFragment : Fragment() {


    lateinit var lineDataSetList: ArrayList<ILineDataSet>
    lateinit var timeLineDataSet: LineDataSet
    lateinit var homeworkLineDataSet: LineDataSet
    lateinit var testLineDataSet: LineDataSet
    private lateinit var viewBinding : FragmentPerformanceAcademicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentPerformanceAcademicBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {

            recGradeCard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recGradeCard.adapter = AdapterSubjectWiseMarks(requireContext(), getMarks())

        }
        setHomeLineChart()
        setTimeLineChart()
        setTestLineChart()
        setHomeWorkLineChart()

    }

    fun getMarks():ArrayList<ModelSubjectMarks>{
        return ArrayList<ModelSubjectMarks>().apply {
            add(ModelSubjectMarks("", "Science", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "Math", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "English", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "Hindi", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "Social Science", "8.2", "8.0", "7.2", "9.4"))
        }
    }


    fun setHomeLineChart(){


        getHomeDataSet()
        /*lineDataSet = LineDataSet(lineDataSetList)
        lineDataSet.apply {

            setAxisDependency(YAxis.AxisDependency.LEFT)
            setLineWidth(2f)
            setDrawValues(false)
            setColor(Color.CYAN)
            setCircleRadius(6f)
            setCircleHoleRadius(3f)
            setDrawCircles(false)
            setDrawHighlightIndicators(true)
            setHighlightEnabled(true)
            setHighLightColor(Color.CYAN)
            setValueTextSize(10f)
            setValueTextColor(Color.DKGRAY)
            setMode(LineDataSet.Mode.STEPPED)

        }*/
        viewBinding.apply {

            lineChart.setTouchEnabled(true)
            lineChart.setPinchZoom(true)

            val lineData = LineData(lineDataSetList)
            lineChart.getDescription().setTextSize(10f)
            lineChart.getDescription().setEnabled(false)
            lineChart.animateY(1000)
            lineChart.setData(lineData)

            //Hide the bottom colors
            val l: Legend = viewBinding.lineChart.getLegend() // get legend of pie
            l.isEnabled = false

            lineChart.getAxisRight().setEnabled(false)
            lineChart.xAxis.isEnabled = false
            lineChart.invalidate()
        }
    }

    fun setTimeLineChart(){

        timeLineDataSet = LineDataSet(getTimeDataSet(),"")

        timeLineDataSet.setDrawCircles(true)
        timeLineDataSet.setCircleRadius(4f)
        timeLineDataSet.setDrawValues(false)
        timeLineDataSet.setLineWidth(3f)
        timeLineDataSet.setColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))
        timeLineDataSet.setCircleColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))

        viewBinding.apply {

            timeLineChart.setTouchEnabled(true)
            timeLineChart.setPinchZoom(true)

            val lineData = LineData(timeLineDataSet)
            timeLineChart.getDescription().setTextSize(10f)
            timeLineChart.getDescription().setEnabled(false)
            timeLineChart.animateY(1000)
            timeLineChart.setData(lineData)

            //Hide the bottom colors
            val l: Legend = viewBinding.timeLineChart.getLegend() // get legend of pie
            l.isEnabled = false

            timeLineChart.getAxisRight().setEnabled(false)
            timeLineChart.axisLeft.setEnabled(false)
            timeLineChart.xAxis.isEnabled = false
            timeLineChart.invalidate()
        }
    }


    fun setTestLineChart(){

        testLineDataSet = LineDataSet(getTimeDataSet(),"")

        testLineDataSet.setDrawCircles(true)
        testLineDataSet.setCircleRadius(4f)
        testLineDataSet.setDrawValues(false)
        testLineDataSet.setLineWidth(3f)
        testLineDataSet.setColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))
        testLineDataSet.setCircleColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))

        viewBinding.apply {

            testLineChart.setTouchEnabled(true)
            testLineChart.setPinchZoom(true)

            val lineData = LineData(testLineDataSet)
            testLineChart.getDescription().setTextSize(10f)
            testLineChart.getDescription().setEnabled(false)
            testLineChart.animateY(1000)
            testLineChart.setData(lineData)

            //Hide the bottom colors
            val l: Legend = viewBinding.testLineChart.getLegend() // get legend of pie
            l.isEnabled = false

            testLineChart.getAxisRight().setEnabled(false)
            testLineChart.axisLeft.setEnabled(false)
            testLineChart.xAxis.isEnabled = false
            testLineChart.invalidate()
        }
    }

    fun setHomeWorkLineChart(){

        homeworkLineDataSet = LineDataSet(getTimeDataSet(),"")

        homeworkLineDataSet.setDrawCircles(true)
        homeworkLineDataSet.setCircleRadius(4f)
        homeworkLineDataSet.setDrawValues(false)
        homeworkLineDataSet.setLineWidth(3f)
        homeworkLineDataSet.setColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))
        homeworkLineDataSet.setCircleColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))

        viewBinding.apply {

            homeLineGraph.setTouchEnabled(true)
            homeLineGraph.setPinchZoom(true)

            val lineData = LineData(homeworkLineDataSet)
            homeLineGraph.getDescription().setTextSize(10f)
            homeLineGraph.getDescription().setEnabled(false)
            homeLineGraph.animateY(1000)
            homeLineGraph.setData(lineData)

            //Hide the bottom colors
            val l: Legend = viewBinding.homeLineGraph.getLegend() // get legend of pie
            l.isEnabled = false

            homeLineGraph.getAxisRight().setEnabled(false)
            homeLineGraph.axisLeft.setEnabled(false)
            homeLineGraph.xAxis.isEnabled = false
            homeLineGraph.invalidate()
        }
    }


    private fun getHomeDataSet()  {

        lineDataSetList = ArrayList()

        val lineEntries1: ArrayList<Entry> = ArrayList()
        lineEntries1.add(Entry(10f, 5f))
        lineEntries1.add(Entry(20f, 0f))
        lineEntries1.add(Entry(30f, 3f))
        lineEntries1.add(Entry(40f, 1f))
        lineEntries1.add(Entry(50f, 4f))
        lineEntries1.add(Entry(60f, 5f))
        lineEntries1.add(Entry(70f, 6f))
        lineEntries1.add(Entry(80f, 8f))
        lineEntries1.add(Entry(90f, 10f))
        lineEntries1.add(Entry(100f, 9f))

        var lineDataSet1 =  LineDataSet(lineEntries1, "")
        lineDataSet1.setDrawCircles(true)
        lineDataSet1.setCircleRadius(4f)
        lineDataSet1.setDrawValues(false)
        lineDataSet1.setLineWidth(3f)
        lineDataSet1.setColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))
        lineDataSet1.setCircleColor(ContextCompat.getColor(requireContext(), R.color.blue_chart))
        lineDataSetList.add(lineDataSet1)


        val lineEntries2: ArrayList<Entry> = ArrayList()
        lineEntries2.add(Entry(10f, 5f))
        lineEntries2.add(Entry(30f, 0f))
        lineEntries2.add(Entry(40f, 3f))
        lineEntries2.add(Entry(60f, 1f))
        lineEntries2.add(Entry(50f, 4f))
        lineEntries2.add(Entry(80f, 5f))
        lineEntries2.add(Entry(85f, 6f))
        lineEntries2.add(Entry(90f, 8f))
        lineEntries2.add(Entry(100f, 10f))
        lineEntries2.add(Entry(80f, 9f))

        var lineDataSet2 =  LineDataSet(lineEntries2, "")
        lineDataSet2.setDrawCircles(true)
        lineDataSet2.setCircleRadius(4f)
        lineDataSet2.setDrawValues(false)
        lineDataSet2.setLineWidth(3f)
        lineDataSet2.setColor(ContextCompat.getColor(requireContext(), R.color.dark_skyblue_chart))
        lineDataSet2.setCircleColor(ContextCompat.getColor(requireContext(), R.color.dark_skyblue_chart))
        lineDataSetList.add(lineDataSet2)

        val lineEntries3: ArrayList<Entry> = ArrayList()
        lineEntries3.add(Entry(20f, 5f))
        lineEntries3.add(Entry(30f, 0f))
        lineEntries3.add(Entry(50f, 3f))
        lineEntries3.add(Entry(40f, 1f))
        lineEntries3.add(Entry(70f, 4f))
        lineEntries3.add(Entry(80f, 5f))
        lineEntries3.add(Entry(85f, 6f))
        lineEntries3.add(Entry(80f, 8f))
        lineEntries3.add(Entry(90f, 10f))
        lineEntries3.add(Entry(100f, 9f))

        var lineDataSet3 =  LineDataSet(lineEntries3, "")
        lineDataSet3.setDrawCircles(true)
        lineDataSet3.setCircleRadius(4f)
        lineDataSet3.setDrawValues(false)
        lineDataSet3.setLineWidth(3f)
        lineDataSet3.setColor(ContextCompat.getColor(requireContext(), R.color.purple_chart))
        lineDataSet3.setCircleColor(ContextCompat.getColor(requireContext(), R.color.purple_chart))
        lineDataSetList.add(lineDataSet3)

    }

    private fun getTimeDataSet() : ArrayList<Entry> {

        val lineEntries1: ArrayList<Entry> = ArrayList()
        lineEntries1.add(Entry(10f, 5f))
        lineEntries1.add(Entry(20f, 0f))
        lineEntries1.add(Entry(30f, 3f))
        lineEntries1.add(Entry(40f, 1f))
        lineEntries1.add(Entry(50f, 4f))
        lineEntries1.add(Entry(60f, 5f))
        lineEntries1.add(Entry(70f, 6f))
        lineEntries1.add(Entry(80f, 8f))
        lineEntries1.add(Entry(90f, 10f))
        lineEntries1.add(Entry(100f, 9f))

        return lineEntries1

    }

}