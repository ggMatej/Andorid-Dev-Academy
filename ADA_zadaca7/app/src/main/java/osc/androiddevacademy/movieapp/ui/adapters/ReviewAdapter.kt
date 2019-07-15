package osc.androiddevacademy.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.model.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewViewHolder>(){

    private val reviewsList = mutableListOf<Review>()

    fun setReviewList(reviewsList: List<Review>){
        this.reviewsList.clear()
        this.reviewsList.addAll(reviewsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount() = reviewsList.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindData(reviewsList[position])
    }
}