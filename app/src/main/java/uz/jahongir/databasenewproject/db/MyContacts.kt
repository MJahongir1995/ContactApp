package uz.jahongir.databasenewproject.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity()
data class MyContact(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var number:String
):Parcelable