package hr.ferit.matejmijic.ada_taskie.presistence

import android.preference.PreferenceManager
import hr.ferit.matejmijic.ada_taskie.Taskie

object TaskiePrefs {
    const val KEY_LAST_PRIORITY = "KEY_LAST_PRIORITY"

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(Taskie.getAppContext())

    fun store(key: String, value: Int){
        sharedPrefs().edit().putInt(key,value).apply()
    }

    fun getInt(key: String): Int{
        return sharedPrefs().getInt(KEY_LAST_PRIORITY,0)
    }

}