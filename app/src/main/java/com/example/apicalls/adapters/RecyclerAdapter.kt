package com.example.apicalls.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalls.R
import com.example.apicalls.model.UserInfo
import com.google.android.material.card.MaterialCardView

class RecyclerAdapter(private var count:Int, private var dataArray:ArrayList<UserInfo>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(cardView: View):RecyclerView.ViewHolder(cardView){
        var card:MaterialCardView=cardView.findViewById(R.id.recycler_card)
        var avatar:ImageView=cardView.findViewById(R.id.avatar_img)
        var fullName:TextView=cardView.findViewById(R.id.card_fullName)
        var email:TextView=cardView.findViewById(R.id.card_email)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_card_layout,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val color= arrayOf(R.color.color1,R.color.color2,R.color.color3,R.color.color4,R.color.color5,R.color.color6)
        val number=(0..5).random()
        holder.avatar.setImageResource(R.drawable.man)
        holder.card.setCardBackgroundColor(color[number])
        holder.email.text=dataArray[position].email
        val fullName="${dataArray[position].fName} ${dataArray[position].lName}"
        holder.fullName.text = fullName
    }

    override fun getItemCount(): Int {
        return count
    }
}