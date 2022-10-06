package inft2501.leksjon_07

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import inft2501.leksjon_07.databinding.MinLayoutBinding
import inft2501.leksjon_07.managers.FileManager
import inft2501.leksjon_07.managers.MyPreferenceManager
import inft2501.leksjon_07.service.Database
import java.util.*

class MainActivity : AppCompatActivity() {

	private lateinit var db: Database
	private lateinit var minLayout: MinLayoutBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		minLayout = MinLayoutBinding.inflate(layoutInflater)
		setContentView(minLayout.root)

		db = Database(this)

		MyPreferenceManager(this).updateNightMode()
		FileManager(this)
	}

	private fun showResults(list: ArrayList<String>) {
		val res = StringBuffer("")
		for (s in list) res.append("$s\n")
		minLayout.result.text = res
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.settings, menu)
		menu.add(0, 1, 0, "Alle forfattere")
		menu.add(0, 2, 0, "Alle bøker")
		menu.add(0, 3, 0, "Alle bøker og forfattere")
		menu.add(0, 4, 0, "Bøker av Charles Dickens")
		menu.add(0, 5, 0, "Forfattere av \"All the Presidents Men\"")
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.settings -> startActivity(Intent("inft2501.leksjon_07.SettingsActivity"))
			1             -> showResults(db.allAuthors)
			2             -> showResults(db.allBooks)
			3             -> showResults(db.allBooksAndAuthors)
			4             -> showResults(db.getBooksByAuthor("Charles Dickens"))
			5             -> showResults(db.getAuthorsByBook("All The Presidents Men"))
			else          -> return false
		}
		return super.onOptionsItemSelected(item)
	}
}
