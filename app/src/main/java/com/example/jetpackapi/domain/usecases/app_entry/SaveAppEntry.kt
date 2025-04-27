package com.example.jetpackapi.domain.usecases.app_entry

import com.example.jetpackapi.domain.manger.LocalUserManger

class SaveAppEntry(
    private val  localUserManger:LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}