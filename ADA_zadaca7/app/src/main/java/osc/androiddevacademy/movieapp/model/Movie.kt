package osc.androiddevacademy.movieapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Movie(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val averageVote: Float
) : Parcelable{
    @Ignore
    @IgnoredOnParcel
    var isFavorite: Boolean = false
}