package osc.androiddevacademy.movieapp.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("results") val reviews : List<Review>
)