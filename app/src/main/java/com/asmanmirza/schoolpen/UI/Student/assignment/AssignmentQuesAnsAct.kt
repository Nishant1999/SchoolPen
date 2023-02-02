package com.asmanmirza.schoolpen.UI.Student.assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.asmanmirza.schoolpen.BaseActivity
import com.asmanmirza.schoolpen.DummyList
import com.asmanmirza.schoolpen.DummyList.Companion.IMAGE_WITH_DESCRIPTION
import com.asmanmirza.schoolpen.DummyList.Companion.IMAGE_WITH_MULTI_SELECTION
import com.asmanmirza.schoolpen.DummyList.Companion.IMAGE_WITH_SINGLE_SELECTION
import com.asmanmirza.schoolpen.DummyList.Companion.MCQ_MULTI_SELECTION
import com.asmanmirza.schoolpen.DummyList.Companion.MCQ_SINGLE_SELECTION
import com.asmanmirza.schoolpen.DummyList.Companion.MCQ_TRUE_FALSE_SELECTION
import com.asmanmirza.schoolpen.DummyList.Companion.MULTIPLE_FILL_IN_THE_BLANK
import com.asmanmirza.schoolpen.DummyList.Companion.ONE_WORD_ANSWER
import com.asmanmirza.schoolpen.DummyList.Companion.SINGLE_FILL_IN_THE_BLANK
import com.asmanmirza.schoolpen.DummyList.Companion.SUBJECTIVE_ANS_PHOTO
import com.asmanmirza.schoolpen.DummyList.Companion.VIDEO_WITH_DESCRIPTION
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.assignment.Adapters.AssignmentQuesAnsNoAdapter
import com.asmanmirza.schoolpen.UI.Student.assignment.Fragments.*
import com.asmanmirza.schoolpen.UI.Student.assignment.Models.AssignmentQuesAnsModel
import com.asmanmirza.schoolpen.databinding.ActivityAssignmentQuesAnsBinding


class AssignmentQuesAnsAct : BaseActivity(), View.OnClickListener {

    lateinit var binding: ActivityAssignmentQuesAnsBinding
    var previousClickPosition: Int = -1
    lateinit var adapter: AssignmentQuesAnsNoAdapter
    lateinit var assignmentQuesData: ArrayList<AssignmentQuesAnsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAssignmentQuesAnsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            txtSaveNextAssignmentQuesAnsAct.setOnClickListener(this@AssignmentQuesAnsAct)
            imgMarkMsgAssignmentQuesAnsAct.setOnClickListener(this@AssignmentQuesAnsAct)
            imgExclamationAssignmentQuesAnsAct.setOnClickListener(this@AssignmentQuesAnsAct)
            txtMarksAssignmentQuesAnsAct.text = "${resources.getString(R.string.marks)} 4"
        }

        setAdapterData()
    }


    fun setAdapterData() {

        assignmentQuesData = DummyList.getAssingmentQues()

        val layoutManager =
            LinearLayoutManager(this@AssignmentQuesAnsAct, LinearLayoutManager.HORIZONTAL, false)

        binding.headerAssignmentQuesAnsAct.rvQuesNoAssignmentHeader.layoutManager = layoutManager

        if (assignmentQuesData.size > 0) {
            previousClickPosition = 0
            assignmentQuesData.get(0).isSelected = true
            showQuestions(assignmentQuesData.get(0).questionType)
        }
        adapter = AssignmentQuesAnsNoAdapter(this, assignmentQuesData) {

            updateQuestionData(it)
            binding.headerAssignmentQuesAnsAct.rvQuesNoAssignmentHeader.scrollToPosition(it)
        }

        binding.headerAssignmentQuesAnsAct.rvQuesNoAssignmentHeader.adapter = adapter
    }

    fun updateQuestionData(currentPosition: Int) {

        if (previousClickPosition >= 0) {
            assignmentQuesData.get(previousClickPosition).isSelected = false
            adapter.notifyItemChanged(previousClickPosition)
        }

        assignmentQuesData.get(currentPosition).isSelected = true
        adapter.notifyItemChanged(currentPosition)

        previousClickPosition = currentPosition

        showQuestions(assignmentQuesData[currentPosition].questionType)
    }

    fun showQuestions(type: String) {

        lateinit var fragment: Fragment

        if (type.equals(SUBJECTIVE_ANS_PHOTO)) {
            fragment = AnswerPhotoUploadFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                SUBJECTIVE_ANS_PHOTO,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(ONE_WORD_ANSWER)) {
            fragment = OneWordAnswerFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                ONE_WORD_ANSWER,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(MCQ_SINGLE_SELECTION)) {
            fragment = McqSingleSelectionFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                MCQ_SINGLE_SELECTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(MCQ_MULTI_SELECTION)) {
            fragment = McqMultipleSelectionFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                MCQ_MULTI_SELECTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(MCQ_TRUE_FALSE_SELECTION)) {
            fragment = McqTrueFalseFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                MCQ_TRUE_FALSE_SELECTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(MULTIPLE_FILL_IN_THE_BLANK)) {
            fragment = MultipleFillInBlankFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                MULTIPLE_FILL_IN_THE_BLANK,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(SINGLE_FILL_IN_THE_BLANK)) {
            fragment = SingleFillInBlankFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                SINGLE_FILL_IN_THE_BLANK,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(IMAGE_WITH_DESCRIPTION)) {
            fragment = ImageWithDescriptonFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                IMAGE_WITH_DESCRIPTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(IMAGE_WITH_SINGLE_SELECTION)) {
            fragment = ImageWithMCQSingleSelectionFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                IMAGE_WITH_SINGLE_SELECTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(IMAGE_WITH_MULTI_SELECTION)) {
            fragment = ImageWithMCQMultipleSelectionFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                IMAGE_WITH_MULTI_SELECTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        } else if (type.equals(VIDEO_WITH_DESCRIPTION)) {
            fragment = VideoWithDescriptonFrgmnt()
            replaceFragmentWithoutBackStack(
                fragment,
                VIDEO_WITH_DESCRIPTION,
                null,
                R.id.flAnswer_assignQuesAnsAct
            )

        }

    }

    companion object {

        fun startActivity(context: Context) {

            var intent = Intent(context, AssignmentQuesAnsAct::class.java)
            context.startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {

        when (p0) {

            binding.txtSaveNextAssignmentQuesAnsAct -> {

                if (previousClickPosition < assignmentQuesData.size - 1) {

                    updateQuestionData(previousClickPosition + 1)
                } else {
                    AssignmentReviewActivity.startActivity(this)
                }
            }

            binding.imgExclamationAssignmentQuesAnsAct -> {
                showHideMarkMsg()
            }

            binding.imgMarkMsgAssignmentQuesAnsAct -> {
                showHideMarkMsg()
            }
        }
    }

    fun showHideMarkMsg() {
        if (binding.imgMarkMsgAssignmentQuesAnsAct.visibility == View.VISIBLE)
            binding.imgMarkMsgAssignmentQuesAnsAct.visibility = View.GONE
        else
            binding.imgMarkMsgAssignmentQuesAnsAct.visibility = View.VISIBLE
    }

}