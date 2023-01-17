package uz.jahongir.databasenewproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.jahongir.databasenewproject.databinding.RvItemBinding
import uz.jahongir.databasenewproject.db.MyContact

class MyRvAdapter(val rvEvent: RvEvent) :
    RecyclerView.Adapter<MyRvAdapter.VH>() {
    private var list = emptyList<MyContact>()

    inner class VH(private var rvItemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        fun onBind(myContact: MyContact, position: Int) {
            rvItemBinding.name.text = myContact.name
            rvItemBinding.number.text = myContact.number

            rvItemBinding.menuImage.setOnClickListener {
                rvEvent.menuClick(myContact, position, rvItemBinding.menuImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    fun setData(myContact: List<MyContact>) {
        this.list = myContact
        notifyDataSetChanged()
    }

    fun getContactAt(position: Int): MyContact {
        return list[position]
    }

    interface RvEvent {
        fun menuClick(myContact: MyContact, position: Int, view: ImageView)

    }


}


