package uz.jahongir.databasenewproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyContact::class], version = 2)
abstract class AppDataBase : RoomDatabase(){
    abstract fun myContactDao(): MyContactDao

    companion object {
        @Volatile
        private var appDatabase: AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppDataBase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDataBase::class.java, "contacts")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return appDatabase!!
        }
    }
}
