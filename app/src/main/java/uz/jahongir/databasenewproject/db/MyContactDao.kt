package uz.jahongir.databasenewproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(myContact: MyContact)

    @Update
    suspend fun updateContact(myContact: MyContact)

    @Delete
    suspend fun deleteContact(myContact: MyContact)

    @Query("select * from myContact ORDER BY id ASC")
    fun getContact(): LiveData<List<MyContact>>

    @Query("delete from myContact")
    suspend fun deleteAll()
}