package saxal.me.saxapokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.stetho.Stetho
import saxal.me.saxapokedex.api.database.Database

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // view SQLITE Database in Chrome
        // chrome://inspect
        Stetho.initializeWithDefaults(this)
        Database.initDatabase(applicationContext)
    }
}
