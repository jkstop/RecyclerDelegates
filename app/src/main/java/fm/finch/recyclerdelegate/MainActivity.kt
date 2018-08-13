package fm.finch.recyclerdelegate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fm.finch.recyclerdelegate.delegate.PostAdapter
import fm.finch.recyclerdelegate.posts.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SampleAdapter().run {
            vRecycler.adapter = this

            replace(listOf(
                    RedPostModel("Red"),
                    GreenPostModel("Green"),
                    OrangePostModel("Orange"),
                    BluePostModel("Blue"),
                    GreenPostModel("Green"),
                    RedPostModel("Red"),
                    OrangePostModel("Orange"),
                    BluePostModel("Blue"),
                    GreenPostModel("Green"),
                    RedPostModel("Red"),
                    GreenPostModel("Green"),
                    OrangePostModel("Orange"),
                    GreenPostModel("Green"),
                    OrangePostModel("Orange"),
                    BluePostModel("Blue")
            ))
        }
    }
}

class SampleAdapter : PostAdapter(
        RedPostDelegate(),
        GreenPostDelegate(),
        OrangePostDelegate(),
        BluePostDelegate()
)
