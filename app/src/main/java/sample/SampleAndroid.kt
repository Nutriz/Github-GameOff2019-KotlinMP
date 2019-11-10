package sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//actual class Sample {
//    actual fun checkMe() = 44
//}
//
//actual object Platform {
//    actual val name: String = "Android"
//}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}