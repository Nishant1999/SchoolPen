package com.asmanmirza.schoolpen

import com.asmanmirza.schoolpen.UI.Student.assignment.Models.AssignmentQuesAnsModel
import com.asmanmirza.schoolpen.UI.Student.assignment.Models.ReviewQuestionModel

class DummyList {

    companion object{

        fun getQuesNo() : ArrayList<ReviewQuestionModel>{

            var demoString = ArrayList<ReviewQuestionModel>()
            demoString.add(ReviewQuestionModel("1", "Submit"))
            demoString.add(ReviewQuestionModel("2", "Submit"))
            demoString.add(ReviewQuestionModel("3", "Marked for Review"))
            demoString.add(ReviewQuestionModel("4", "Submit"))
            demoString.add(ReviewQuestionModel("5", "Submit"))
            demoString.add(ReviewQuestionModel("6", "Skipped"))
            demoString.add(ReviewQuestionModel("7", "Submit"))
            demoString.add(ReviewQuestionModel("8", "Marked for Review"))
            demoString.add(ReviewQuestionModel("9", "Skipped"))
            demoString.add(ReviewQuestionModel("10", "Submit"))
            demoString.add(ReviewQuestionModel("11", "Skipped"))
            demoString.add(ReviewQuestionModel("12", "Skipped"))
            demoString.add(ReviewQuestionModel("13", "Marked for Review"))
            demoString.add(ReviewQuestionModel("14", "Marked for Review"))
            demoString.add(ReviewQuestionModel("15", "Marked for Review"))
            demoString.add(ReviewQuestionModel("16", "Submit"))
            demoString.add(ReviewQuestionModel("17", "Submit"))
            demoString.add(ReviewQuestionModel("18", "Submit"))
            demoString.add(ReviewQuestionModel("19", "Submit"))

            return demoString
        }


    val SUBJECTIVE_ANS_PHOTO = "SUBJECTIVE_ANS_PHOTO"
    val ONE_WORD_ANSWER = "ONE_WORD_ANSWER"
    val MCQ_SINGLE_SELECTION = "MCQ_SINGLE_SELECTION"
    val MCQ_MULTI_SELECTION = "MCQ_MULTI_SELECTION"
    val MCQ_TRUE_FALSE_SELECTION = "MCQ_TRUE_FALSE_SELECTION"
    val MULTIPLE_FILL_IN_THE_BLANK = "MULTIPLE_FILL_IN_THE_BLANK"
    val SINGLE_FILL_IN_THE_BLANK = "SINGLE_FILL_IN_THE_BLANK"
    val IMAGE_WITH_DESCRIPTION = "IMAGE_WITH_DESCRIPTION"
    val IMAGE_WITH_SINGLE_SELECTION = "IMAGE_WITH_SINGLE_SELECTION"
    val IMAGE_WITH_MULTI_SELECTION = "IMAGE_WITH_MULTI_SELECTION"
    val VIDEO_WITH_DESCRIPTION = "VIDEO_WITH_DESCRIPTION"

    fun getAssingmentQues(): ArrayList<AssignmentQuesAnsModel>{

        var demoData = ArrayList<AssignmentQuesAnsModel>()
        demoData.add(AssignmentQuesAnsModel(SUBJECTIVE_ANS_PHOTO, false))
        demoData.add(AssignmentQuesAnsModel(ONE_WORD_ANSWER, false))
        demoData.add(AssignmentQuesAnsModel(MCQ_SINGLE_SELECTION, false))
        demoData.add(AssignmentQuesAnsModel(MCQ_MULTI_SELECTION, false))
        demoData.add(AssignmentQuesAnsModel(MCQ_TRUE_FALSE_SELECTION, false))
        demoData.add(AssignmentQuesAnsModel(MULTIPLE_FILL_IN_THE_BLANK, false))
        demoData.add(AssignmentQuesAnsModel(SINGLE_FILL_IN_THE_BLANK, false))
        demoData.add(AssignmentQuesAnsModel(IMAGE_WITH_DESCRIPTION, false))
        demoData.add(AssignmentQuesAnsModel(IMAGE_WITH_SINGLE_SELECTION, false))
        demoData.add(AssignmentQuesAnsModel(IMAGE_WITH_MULTI_SELECTION, false))
        demoData.add(AssignmentQuesAnsModel(VIDEO_WITH_DESCRIPTION, false))
        return demoData
    }

    }
}