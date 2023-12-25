package com.fwhyn.domain.usecase

import com.fwhyn.domain.helper.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<PARAM, RESULT> {

    private var statusNotifier: ((Results<RESULT, Exception>) -> Unit)? = null
    private var job: Job? = null

    fun setResultNotifier(statusNotifier: (Results<RESULT, Exception>) -> Unit): BaseUseCase<PARAM, RESULT> {
        this.statusNotifier = statusNotifier

        return this
    }

    open fun execute(
        param: PARAM,
        coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    ) {
    }

    open fun execute(param: PARAM) {}

    suspend fun notifyStatusFromBackground(result: Results<RESULT, Exception>) {
        withContext(Dispatchers.Main) {
            statusNotifier?.let { it(result) }
        }
    }

    fun notifyStatus(result: Results<RESULT, Exception>) {
        statusNotifier?.let { it(result) }
    }

    protected open fun run(
        coroutineScope: CoroutineScope,
        runAPi: suspend () -> RESULT,
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            notifyStatus(Results.Loading(0))
            if (job?.isActive == true) job?.cancelAndJoin()

            job = launch(Dispatchers.IO) {
                try {
                    val result = runAPi()
                    notifyStatusFromBackground(Results.Loading(100))
                    notifyStatusFromBackground(Results.Success(result))
                } catch (e: Exception) {
                    notifyStatusFromBackground(Results.Failure(e))
                }
            }
        }
    }

    protected open fun run(
        runAPi: () -> RESULT,
    ) {
        try {
            val result = runAPi()
            notifyStatus(Results.Success(result))
        } catch (e: Exception) {
            notifyStatus(Results.Failure(e))
        }
    }
}