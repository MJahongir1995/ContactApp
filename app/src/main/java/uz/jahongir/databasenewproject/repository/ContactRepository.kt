package uz.jahongir.databasenewproject.repository

import androidx.lifecycle.LiveData
import uz.jahongir.databasenewproject.db.MyContact
import uz.jahongir.databasenewproject.db.MyContactDao

class ContactRepository(private val myContactDao: MyContactDao) {
    val getContact:LiveData<List<MyContact>> = myContactDao.getContact()

    suspend fun addContact(myContact: MyContact){
        myContactDao.addContact(myContact)

    }

    suspend fun updateContact(myContact: MyContact){
        myContactDao.updateContact(myContact)

    }

    suspend fun deleteContact(myContact: MyContact){
        myContactDao.deleteContact(myContact)

    }

    suspend fun deleteAll(){
        myContactDao.deleteAll()

    }
}