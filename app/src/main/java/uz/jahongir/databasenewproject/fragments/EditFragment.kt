package uz.jahongir.databasenewproject.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.jahongir.databasenewproject.R
import uz.jahongir.databasenewproject.adapter.MyRvAdapter
import uz.jahongir.databasenewproject.databinding.FragmentEditBinding
import uz.jahongir.databasenewproject.db.MyContact
import uz.jahongir.databasenewproject.viewmodel.ContactViewModel

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private val args by navArgs<EditFragmentArgs>()
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        binding.updateedtName.setText(args.currentContact.name)
        binding.updateedtNumber.setText(args.currentContact.number)

        binding.updatesave.setOnClickListener {
            updateContact()
        }
        return  binding.root
    }
    private fun updateContact(){
        val name = binding.updateedtName.text.toString()
        val number = binding.updateedtNumber.text.toString()

        if (inputCheck(name,number)){
            val updatedContact = MyContact(args.currentContact.id,name,number)
            contactViewModel.updateContact(updatedContact)
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(binding.root.context, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
        findNavController().navigate(R.id.action_editFragment_to_blankFragment2)
    }
    private fun inputCheck(name:String, number:String): Boolean{
        return! (TextUtils.isEmpty(name) && (TextUtils.isEmpty(number)))
    }
}