package com.example.recyclerapp1

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var context : Context, private var listOfPersons : ArrayList<Person>, val malePic : Int, val femalePic: Int) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (listOfPersons[position].sex == "male"){ // slow code, images take long to load
            holder.pic.setImageResource(malePic)
        }
        else{
            holder.pic.setImageResource(femalePic)
        }
        holder.txtViewName.text = listOfPersons[position].name
        holder.txtViewPhone.text = listOfPersons[position].phoneNumber

    }

    override fun getItemCount(): Int {
        return listOfPersons.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pic : ImageView = itemView.findViewById(R.id.picture)
        val txtViewName : TextView = itemView.findViewById(R.id.name)
        val txtViewPhone : TextView = itemView.findViewById(R.id.phone)
    }

}
