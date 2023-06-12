package com.example.myapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NearMeAdapter(val list: ArrayList<NearMeModel>): RecyclerView.Adapter<NearMeAdapter.viewHolder>(){


    lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.near_me_card_container,parent,false)
        return viewHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: viewHolder, position:Int){
        val currentItem = list[position]
        holder.image.setImageResource(currentItem.image)

        holder.name.text = currentItem.text
    }

    inner class viewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

        val image: ImageButton = itemView.findViewById(R.id.button)

        init{
            image.setOnClickListener {

                listener.onItemClick(adapterPosition)
            }
        }

        val name : TextView =itemView.findViewById(R.id.place_name)



    }





}