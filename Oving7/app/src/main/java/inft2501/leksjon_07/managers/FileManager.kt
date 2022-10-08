package inft2501.leksjon_07.managers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import inft2501.leksjon_07.Movie
import inft2501.leksjon_07.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.nio.charset.Charset

/**
 * Just contains basic code snippets relevant for reading from/to different files
 */
class FileManager(private val activity: AppCompatActivity) {

	private val filename: String = "filename.txt"

	private var dir: File = activity.filesDir
	private var file: File = File(dir, filename)

	private var externalDir: File? = activity.getExternalFilesDir(null)
	private var externalFile = File(externalDir, filename)


	fun write(str: String) {
		PrintWriter(file).use { writer ->
			writer.println(str)
		}
	}

	fun readLine(): String? {
		BufferedReader(FileReader(file)).use { reader ->
			return reader.readLine()
		}
	}

	fun writeToTxt() {
		try {
			val fileOutputStream: FileOutputStream = activity.openFileOutput("mytext2file.txt", Context.MODE_PRIVATE)
			val outputWriter = OutputStreamWriter(fileOutputStream)
			outputWriter.write("editText.text.toString()")
			outputWriter.write("editText.text.toString()")
			outputWriter.write("editText.text.toString()")
			outputWriter.write("editText.text.toString()")
			outputWriter.close()
		}
		catch (e: Exception) {
			e.printStackTrace()
		}
	}

	fun readFromJson(): JSONArray? {
		try {
			val obj = JSONObject(readJSON())
			return obj.getJSONArray("movies")
		} catch (e: JSONException) {
			e.printStackTrace()
		}
		return null
	}

	private fun readJSON(): String {
		val json: String?
		try {
			val inputStream = activity.resources.openRawResource(R.raw.movies);
			val size = inputStream.available()
			val buffer = ByteArray(size)
			val charset: Charset = Charsets.UTF_8
			inputStream.read(buffer)
			inputStream.close()
			json = String(buffer, charset)
		}
		catch (ex: IOException) {
			ex.printStackTrace()
			return ""
		}
		return json
	}

	/**
	 * Open file: *res/raw/id.txt*
	 *
	 * @param fileId R.raw.filename
	 */
	private fun readFileFromResFolder(fileId: Int): String {
		val content = StringBuffer("")
		try {
			val inputStream: InputStream = activity.resources.openRawResource(fileId)
			val reader = BufferedReader(InputStreamReader(inputStream)).use { reader ->
				var line = reader.readLine()
				while (line != null) {
					content.append(line)
					content.append("\n")
					line = reader.readLine()
				}

			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return content.toString()
	}
}
