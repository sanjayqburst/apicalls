package com.example.apicalls.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalls.ProfileData
import com.example.apicalls.R

class RecyclerAdapter(private var context: Context, private var dataArray: ProfileData?) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView) {
        var cardView: CardView = cardView.findViewById(R.id.recycler_card)
        var avatar: ImageView = cardView.findViewById(R.id.avatar_img)
        var fullName: TextView = cardView.findViewById(R.id.card_fullName)
        var email: TextView = cardView.findViewById(R.id.card_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.recycler_card_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val colorAr = arrayOf(
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6
        )
        val number = (0..5).random()
        Log.d("Msg","$number ${colorAr[number]}")
        holder.cardView.setBackgroundColor(ContextCompat.getColor(context,colorAr[number]))
        Glide.with(context).load(dataArray?.data?.get(position)?.avatar).into(holder.avatar)
//        holder.avatar.setImageResource(R.drawable.man)
        holder.email.text = dataArray?.data?.get(position)?.email
        val fullName =
            dataArray?.data?.get(position)?.first_name + " " + dataArray?.data?.get(position)?.last_name
        holder.fullName.text = fullName


    }

    override fun getItemCount(): Int {
        return 6
    }
}