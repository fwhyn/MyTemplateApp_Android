package com.fwhyn.data.repository

interface CoroutineBaseRepository<PARAM, DATA> {
    suspend fun get(param: PARAM): DATA

    suspend fun set(param: PARAM, data: DATA)
}