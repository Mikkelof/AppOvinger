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

	fun updateColor(): Int {
		val colorDefault = resources.getIntArray(R.array.color_values_not_string)
		println(colorDefault[2])
		val value = getString(
			resources.getString(R.string.colors),
			resources.getString(R.string.colors_default_value)
		)
		println(value)
		when (value) {
			"#00FF00" -> println("1")//minLayout.root.setBackgroundColor(colorDefault[1])
			"#0000FF" -> println("2")
			"#FF0000" -> println("3")
			"#FFFF00" -> println("4")//activity.findViewById<ConstraintLayout>(R.id.constrainLayout).setBackgroundColor(colorDefault[2])//activity.findViewById<ConstraintLayout>(R.id.constrainLayout).setBackgroundColor(colorDefault[3])*/
		}
		return 1
	}

	fun registerListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.registerOnSharedPreferenceChangeListener(activity)
	}

	fun unregisterListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.unregisterOnSharedPreferenceChangeListener(activity)
	}
}
