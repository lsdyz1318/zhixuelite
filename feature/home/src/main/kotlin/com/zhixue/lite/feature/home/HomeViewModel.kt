package com.zhixue.lite.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zhixue.lite.core.data.repository.ReportRepository
import com.zhixue.lite.core.model.ReportInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    reportRepository: ReportRepository
) : ViewModel() {

    val examReportInfoList: Flow<PagingData<ReportInfo>> =
        reportRepository.getReportInfoList(type = "exam").cachedIn(viewModelScope)

    val homeworkReportInfoList: Flow<PagingData<ReportInfo>> =
        reportRepository.getReportInfoList(type = "homework").cachedIn(viewModelScope)
}