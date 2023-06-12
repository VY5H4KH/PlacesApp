package com.example.myapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.location.Address
import android.location.Geocoder
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.NearMeAdapter
import com.example.myapp.NearMeModel
import java.io.IOException

class SecondActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var al : ArrayList<NearMeModel>
    lateinit var image : Array<Int>
    lateinit var text:  Array<String>
//    lateinit var lat: Array<String>
//    lateinit var long: Array<String>
    lateinit var label: Array<String>
    lateinit var pl: EditText
    lateinit var bu: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        pl=findViewById(R.id.message_edit_text)
        bu=findViewById(R.id.send_btn)
        image = arrayOf(R.drawable.fortkochi, R.drawable.cheraibeach, R.drawable.marine_drive, R.drawable.kathakali, R.drawable.folklore, R.drawable.cave, R.drawable.parsyn)
        text = arrayOf("Fort Kochi", "Cherai Beach", "Marine Drive", "Kerala Kathakali Centre", "Kerala Folklore Museum", "Kochareekkal Caves", "Paradesi Synagogue")

//        lat = arrayOf(, "10.1416° N", "9.9772° N", "9° 57' 58.104\" N", "9° 55' 58.548\" N", "9.97833333333 N", "9°57′26″N").toString()
//        long = arrayOf("76.2421° E", "76.1783° E", "76.2773° E", "76° 14' 38.112\" E", "76° 17' 57.516\" E", "76.2836111111 E", "76°15′34″E")
        label = arrayOf("Fort Kochi", "cherai beach", "Marine Drive", "Kerala Kathakali Centre", "Kerala Folklore Museum", "Kochareekkal Caves", "paradesi synagogue - clock tower")
        rv = findViewById(R.id.nearMeRV)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rv.isNestedScrollingEnabled = false
        rv.setHasFixedSize(true)
        al = arrayListOf<NearMeModel>()
        getUserdata()

        bu.setOnClickListener {
            var t:String=pl.text.toString().trim()
            var addressList : List<Address>? = null

            if (t==null || t==""){
                Toast.makeText(this,"Provide location",Toast.LENGTH_SHORT).show()
            }
            else{
                val geocoder = Geocoder(this)
                try{
                    addressList=geocoder.getFromLocationName(t,1)
                }catch(e: IOException){
                    e.printStackTrace()
                }
                val address = addressList!![0]
                val latt = address.latitude
                val longt = address.longitude
                val labell = t
                val uri = "geo:$latt,$longt?q=$labell"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                startActivity(intent)
            }
        }
    }

    private fun code(s: String): Double {
        var addressList : List<Address>? = null
        val geocoder = Geocoder(this)
        try{
            addressList=geocoder.getFromLocationName(s,1)
        }catch(e: IOException){
            e.printStackTrace()
        }
        val address = addressList!![0]
        return address.latitude;

    }

    fun getUserdata() {

        for(i in image.indices){
            val map = NearMeModel(image[i],text[i])
            al.add(map)
        }

        var adapter = NearMeAdapter(al)
        rv.adapter = adapter
        adapter.setOnItemClickListener(object: NearMeAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val latt = code(label[position])
                val longt = codel(label[position])
                val labell = label[position]
                val uri = "geo:$latt,$longt?q=$labell"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                startActivity(intent)
            }


        })
    }

    private fun codel(s: String): Double {
        var addressList : List<Address>? = null
        val geocoder = Geocoder(this)
        try{
            addressList=geocoder.getFromLocationName(s,1)
        }catch(e: IOException){
            e.printStackTrace()
        }
        val address = addressList!![0]
        return address.longitude;
    }
}