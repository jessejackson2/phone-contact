package com.project.phonecontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
* This is the adapter class for the recycler view
* */
class ContactsAdapter(
    private var contacts: List<Contact>,
    private val clickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    // ViewHolder class for each item in the recycler view
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.contactName)
        val phone: TextView = itemView.findViewById(R.id.contactPhone)
        val email: TextView = itemView.findViewById(R.id.contactEmail)
        val image: ImageView = itemView.findViewById(R.id.contactImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    // Binds the data to the view holder
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.name.text = contact.name
        holder.phone.text = contact.phone
        holder.email.text = contact.email
        holder.image.setImageResource(contact.image)
        holder.itemView.setOnClickListener { clickListener(contact) }
    }

    override fun getItemCount() = contacts.size

    fun updateData(newContacts: List<Contact>) {
    this.contacts = newContacts
    notifyDataSetChanged()
}

}
