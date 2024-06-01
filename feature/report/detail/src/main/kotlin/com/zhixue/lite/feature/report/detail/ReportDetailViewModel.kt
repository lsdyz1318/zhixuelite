package com.zhixue.lite.feature.report.detail

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.zhixue.lite.core.domain.GetReportDetailUseCase
import com.zhixue.lite.core.domain.SyncReportDetailUseCase
import com.zhixue.lite.core.model.ReportDetail
import com.zhixue.lite.feature.report.detail.navigation.ReportDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val syncReportDetailUseCase: SyncReportDetailUseCase,
    private val getReportDetailUseCase: GetReportDetailUseCase
) : ViewModel() {

    var uiState: ReportDetailUiState by mutableStateOf(ReportDetailUiState.Loading)
        private set

    private val reportId: String = savedStateHandle.toRoute<ReportDetailRoute>().reportId

    private var isInitialized: Boolean = false

    @MainThread
    fun initialize() {
        if (isInitialized) return
        isInitialized = true

        viewModelScope.launch {
            syncReportDetailUseCase(reportId)
            runCatching {
                getReportDetailUseCase(reportId)
            }.onSuccess { reportDetail ->
                uiState = ReportDetailUiState.Success(reportDetail)
            }.onFailure {
                uiState = ReportDetailUiState.Failure
            }
        }
    }
}

sealed class ReportDetailUiState {
    data object Loading : ReportDetailUiState()
    data object Failure : ReportDetailUiState()
    data class Success(val reportDetail: ReportDetail) : ReportDetailUiState()
}