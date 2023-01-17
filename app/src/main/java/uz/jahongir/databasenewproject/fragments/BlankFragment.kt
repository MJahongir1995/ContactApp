package uz.jahongir.databasenewproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.jahongir.databasenewproject.R
import uz.jahongir.databasenewproject.viewmodel.ContactViewModel
import uz.jahongir.databasenewproject.databinding.FragmentBlankBinding
import uz.jahongir.databasenewproject.db.MyContact

class BlankFragment : Fragment() {
    private lateinit var binding: FragmentBlankBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentBlankBinding.inflate(layoutInflater)
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binding.save.setOnClickListener {
            addContactToDB()
            findNavController().navigate(R.id.action_blankFragment_to_blankFragment2)
        }
        return binding.root
    }
    private fun addContactToDB(){
        val name = binding.edtName.text.toString()
        val number = binding.edtNumber.text.toString()

       if (inputCheck(name,number)){
           val myContact = MyContact(0,
               binding.edtName.text.toString(),
               binding.edtNumber.text.toString())

           contactViewModel.addContact(myContact)
           Toast.makeText(binding.root.context, "Saved", Toast.LENGTH_SHORT).show()

           binding.edtName.text.clear()
           binding.edtNumber.text.clear()

       }else{
           Toast.makeText(binding.root.context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
       }
    }

    private fun inputCheck(name:String, number:String): Boolean{
        return! (TextUtils.isEmpty(name) && (TextUtils.isEmpty(number)))
    }
}