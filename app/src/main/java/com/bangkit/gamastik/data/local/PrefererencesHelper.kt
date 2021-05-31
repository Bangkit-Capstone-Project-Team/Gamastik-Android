package com.bangkit.gamastik.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject
constructor(@ApplicationContext context: Context) {

    companion object {
        const val PREF_FILE_NAME = "app_session"
        const val TOKEN = "token"
        const val NAME = "name"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun setUserToken(token: String?) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun setUserName(name: String?) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(NAME, name)

        editor.apply()
    }

    fun getUserToken(): String? {
        return preferences.getString(TOKEN, null)
    }

    fun getUserName(): String? {
        return preferences.getString(NAME, "")
    }


}