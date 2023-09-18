package com.mdd.foodies.newWork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdd.foodies.R
import com.mdd.foodies.databinding.ActivityHomePageBinding
import com.mdd.foodies.newWork.modal.ShopsModel

class homePage : AppCompatActivity() {
    var binding: ActivityHomePageBinding? = null
    var adapter: ShopsAdapter? = null
    var list: ArrayList<ShopsModel>? = null
    lateinit var recyclerView: RecyclerView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)
        list = ArrayList()
        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git credential reject
        /*
here wee need to fetch all the stores from firebase and show it to both vendors and users
after fetching we have to pass it to list
* */adapter = ShopsAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}