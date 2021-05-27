package com.bangkit.gamastik.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import gov.ulama.uttp.utils.Resource
import kotlinx.coroutines.Dispatchers

fun <A> remoteOnlyOperation(
    networkCall: suspend () -> Resource<A>
): LiveData<Resource<A>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            emit(Resource.success(responseStatus.data!!))
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }
    }


