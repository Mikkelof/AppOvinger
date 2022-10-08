package inft2501.leksjon_07

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import inft2501.leksjon_07.databinding.MinLayoutBinding
import inft2501.leksjon_07.managers.FileManager
import inft2501.leksjon_07.managers.MyPreferenceManager
import inft2501.leksjon_07.service.Database
import org.json.JSONArray
import java.util.*

class MainActivity : AppCompatActivity() {

	private lateinit var db: Database
	private lateinit var minLayout: MinLayoutBinding
	private lateinit var fileManager: FileManager
	private lateinit var movies: ArrayList<Movie>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		minLayout = MinLayoutBinding.inflate(layoutInflater)
		setContentView(minLayout.root)
		db = Database(this)
		fileManager = FileManager(this)
		val moviesArray = fileManager.readFromJson()
		db.insertMoviesFromJson(moviesArray)
		saveMoviesToObject(moviesArray)
		fileManager.writeToTxt()

		val pref = MyPreferenceManager(this)
		pref.updateColor()

	}

	private fun saveMoviesToObject(moviesJSON: JSONArray?){
		movies =  ArrayList()
		if (moviesJSON != null) {
			for (i in 0 until moviesJSON.length()) {
				val userDetail = moviesJSON.getJSONObject(i)
				movies.add(Movie(userDetail.getString("title"),userDetail.getString("director"),userDetail.getString("actor").split(",").toTypedArray()))
			}
		}
	}

	private fun showResults(list: ArrayList<String>) {
		val res = StringBuffer("")
		for (s in list) res.append("$s\n")
		minLayout.result.text = res
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.settings, menu)
		menu.add(0, 1, 0, "All Movies")
		menu.add(0, 2, 0, "All Actors")
		menu.add(0, 3, 0, "All Movies And Actors")
		menu.add(0, 4, 0, "Moves by Sergio Leone")
		return super.onCreateOptionsMenu(menu)
	}

	private fun openActivityForResult(){
		val intent = Intent("inft2501.leksjon_07.SettingsActivity")
		startForResult.launch(intent)
	}

	private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
			result: ActivityResult ->
		if (result.resultCode == Activity.RESULT_OK) {
			val intent = result.data
			val c = intent?.getStringExtra("colors")
			println(c)
			var lay = findViewById<ConstraintLayout>(R.id.background)
			lay.setBackgroundColor(Color.parseColor(c))
		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.settings -> openActivityForResult()
			1             -> showResults(db.allMovies)
			2             -> showResults(db.allActors)
			3             -> showResults(db.allMoviesAndActors)
			4             -> showResults(db.moviesBySpecificDirector)
			else          -> return false
		}
		return super.onOptionsItemSelected(item)
	}
}
