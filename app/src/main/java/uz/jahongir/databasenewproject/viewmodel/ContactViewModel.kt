package uz.jahongir.databasenewproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.jahongir.databasenewproject.db.AppDataBase
import uz.jahongir.databasenewproject.db.MyContact
import uz.jahongir.databasenewproject.repository.ContactRepository

class ContactViewModel(application: Application):AndroidViewModel(application) {
    private val repository: ContactRepository
    val getContact:LiveData<List<MyContact>>

    init {
        val myContactDao = AppDataBase.getInstance(application).myContactDao()
        repository = ContactRepository(myContactDao)
        getContact = repository.getContact
    }

    fun addContact(myContact: MyContact){
        viewModelScope.launch (Dispatchers.IO){
            repository.addContact(myContact)
        }
    }

    fun updateContact(myContact: MyContact){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateContact(myContact)
        }
    }

    fun deleteContact(myContact: MyContact){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteContact(myContact)
        }
    }

    fun deleteAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
}