package com.zhixue.lite.feature.report.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.zhixue.lite.feature.report.detail.navigation.ReportDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val reportId: String = savedStateHandle.toRoute<ReportDetailRoute>().reportId
}