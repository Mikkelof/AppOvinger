package inft2501.leksjon_07.managers

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import inft2501.leksjon_07.R

class MyPreferenceManager(private val activity: AppCompatActivity) {

	private val resources = activity.resources
	private val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
	private val editor: SharedPreferences.Editor = preferences.edit()

	fun putString(key: String, value: String) {
		editor.putString(key, value)
		editor.apply()
	}

	fun getString(key: String, defaultValue: String): String {
		return preferences.getString(key, defaultValue) ?: defaultValue
	}

	fun registerListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.registerOnSharedPreferenceChangeListener(activity)
	}

	fun unregisterListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.unregisterOnSharedPreferenceChangeListener(activity)
	}
}
