package fm.finch.recyclerdelegate_sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import fm.finch.recyclerdelegate_sample.models.BlueItemViewData
import fm.finch.recyclerdelegate_sample.models.GreenItemViewData
import fm.finch.recyclerdelegate_sample.models.OrangeItemViewData
import fm.finch.recyclerdelegate_sample.models.RedItemViewData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataAdapter = DataAdapter(
        onGreenClick = {
            Toast.makeText(this, "Green clicked", Toast.LENGTH_SHORT).show()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vRecycler.adapter = dataAdapter
        vRecycler.layoutManager = LinearLayoutManager(this)

        dataAdapter.replace(
            listOf(
                RedItemViewData("Red"),
                GreenItemViewData("Green"),
                OrangeItemViewData("Orange"),
                BlueItemViewData("Blue"),
                RedItemViewData("Red"),
                GreenItemViewData("Green"),
                OrangeItemViewData("Orange"),
                BlueItemViewData("Blue"),
                RedItemViewData("Red"),
                GreenItemViewData("Green"),
                OrangeItemViewData("Orange"),
                BlueItemViewData("Blue")
            )
        )
    }
}
