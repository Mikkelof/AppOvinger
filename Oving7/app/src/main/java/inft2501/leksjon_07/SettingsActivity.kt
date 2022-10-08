package inft2501.leksjon_07

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import inft2501.leksjon_07.databinding.ActivitySettingsBinding
import inft2501.leksjon_07.managers.MyPreferenceManager

class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener,
                         Preference.SummaryProvider<ListPreference> {

	private lateinit var ui: ActivitySettingsBinding
	private lateinit var myPreferenceManager: MyPreferenceManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		myPreferenceManager = MyPreferenceManager(this)
		myPreferenceManager.registerListener(this)

		ui = ActivitySettingsBinding.inflate(layoutInflater)
		setContentView(ui.root)

		supportFragmentManager
			.beginTransaction()
			.replace(R.id.settings_container, SettingsFragment())
			.commit()

		ui.button.setOnClickListener {
			setResult(RESULT_OK, Intent().putExtra("colors",myPreferenceManager.getString("colors","none")))
			finish()
		}
	}

	override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
		println("placeholder")
	}

	override fun provideSummary(preference: ListPreference?): CharSequence {
		return when (preference?.key) {
			getString(R.string.colors) -> preference.entry
			else                           -> "Unknown Preference"
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		myPreferenceManager.unregisterListener(this)
	}

	class SettingsFragment : PreferenceFragmentCompat() {

		override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
			setPreferencesFromResource(R.xml.preference_screen, rootKey)
		}
	}
}
