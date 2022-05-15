package com.example.recyclerapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var listOfPersons : ArrayList<Person> // contains data classes of type Person
    private lateinit var recycler : RecyclerView
    private var sortFlag1 : Boolean = true
    private var sortFlag2 : Boolean = true
    private var sortFlag3 : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obj = JSONObject(getJson("json_data"))
        var personsArray = obj.getJSONArray("Persons") // contains JSON array of objects in it

        listOfPersons = ArrayList()
        for (i in 0 until personsArray.length()){
            val person = personsArray.getJSONObject(i)
            val name : String = person.getString("name")
            val sex : String = person.getString("sex")
            val phone : String = person.getString("phoneNumber")
            listOfPersons.add(Person(name, sex, phone))
        }

        recycler = findViewById(R.id.recyclerView)
        recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
        recycler.layoutManager = LinearLayoutManager(this)

        val sortByNameBtn : Button = findViewById(R.id.sortByName)
        val sortByPhoneBtn : Button = findViewById(R.id.sortByPhone)
        val sortBySexBtn : Button = findViewById(R.id.sortBySex)

        sortByNameBtn.setOnClickListener() {
            if (sortFlag1) {
                listOfPersons.sortBy { it.name }
                recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
            }
            else {
                listOfPersons.sortByDescending { it.name }
                recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
            }
            sortFlag1 = !sortFlag1
        }

        sortByPhoneBtn.setOnClickListener(){
            if (sortFlag2) {
                listOfPersons.sortBy { it.phoneNumber}
                recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
            }
            else {
                listOfPersons.sortByDescending { it.phoneNumber }
                recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
            }
            sortFlag2 = !sortFlag2
        }

        sortBySexBtn.setOnClickListener(){
            if (sortFlag3) {
                listOfPersons.sortBy { it.sex}
                recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
            }
            else {
                listOfPersons.sortByDescending { it.sex }
                recycler.adapter = MyAdapter(this, listOfPersons, R.drawable.male_icon, R.drawable.female_icon)
            }
            sortFlag3 = !sortFlag3
        }
    }



    private fun getJson(fileName : String) : String {
        lateinit var json : String
        val charset : Charset = Charsets.UTF_8
        try {
            val inputStream: InputStream = assets.open(fileName)
            val sizeOfFile: Int = inputStream.available()
            val buffer: ByteArray = ByteArray(sizeOfFile)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (e : Exception){
            println("err")
        }

        return json
    }

}