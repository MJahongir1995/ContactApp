package uz.jahongir.databasenewproject.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.jahongir.databasenewproject.R
import uz.jahongir.databasenewproject.viewmodel.ContactViewModel
import uz.jahongir.databasenewproject.adapter.MyRvAdapter
import uz.jahongir.databasenewproject.databinding.FragmentBlank2Binding
import uz.jahongir.databasenewproject.db.MyContact

class BlankFragment2 : Fragment(), MyRvAdapter.RvEvent {
    private lateinit var binding: FragmentBlank2Binding
    private lateinit var myRvAdapter: MyRvAdapter
    private lateinit var list: ArrayList<MyContact>
    private val args by navArgs<BlankFragment2Args>()
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBlank2Binding.inflate(layoutInflater)

        setHasOptionsMenu(true)

        list = ArrayList()
        myRvAdapter = MyRvAdapter(this)
        binding.myRv.adapter = myRvAdapter

        //ViewModel
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getContact.observe(viewLifecycleOwner, Observer { myContact ->
            myRvAdapter.setData(myContact)
        })

        binding.FAButton.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment2_to_blankFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete_all){
            deleteAllContacts()
            item.isVisible = false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun menuClick(myContact: MyContact, position: Int, imageView: ImageView) {
        val popupMenu = PopupMenu(binding.root.context, imageView)
        popupMenu.inflate(R.menu.my_menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_delete -> {
                    deleteContact(position)
                }
                R.id.menu_update -> {
                    val action = BlankFragment2Directions.actionBlankFragment2ToEditFragment(
                        currentContact = myContact
                    )
                    findNavController().navigate(action)
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun deleteContact(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            contactViewModel.deleteContact(myRvAdapter.getContactAt(position))

            Toast.makeText(binding.root.context, "${myRvAdapter.getContactAt(position).name} deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${myRvAdapter.getContactAt(position).name}?")
        builder.setMessage("Are you sure to delete ${myRvAdapter.getContactAt(position).name}?")
        builder.create().show()
    }

    private fun deleteAllContacts(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            contactViewModel.deleteAll()
            Toast.makeText(binding.root.context, "Everything deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure to delete everything?")
        builder.create().show()
    }
}