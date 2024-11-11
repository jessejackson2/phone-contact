package com.project.phonecontact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var contactList: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // a lis of contacts to display in the recycler view
        contactList = listOf(
            Contact("Ethan Matthews", "7412 678123", "ethan.matthews@example.com", R.drawable.boy),
            Contact("Emma Bennett", "7939 553456", "emma.bennett@example.com", R.drawable.woman),
            Contact("Jessica Davis", "7123 456789", "jessica.davis@example.com", R.drawable.woman1),
            Contact("Liam Turner", "7890 123456", "liam.turner@example.com", R.drawable.man),
            Contact("Olivia Williams", "7412 678123", "olivia.williams@example.com", R.drawable.woman2),
            Contact("Sophia Lee", "7939 553456", "sophia.lee@example.com", R.drawable.woman3),
            Contact("William Smith", "7123 456789", "william.smith@example.com", R.drawable.man1),
            Contact("Zoe Johnson", "7890 123456", "zoe.johnson@example.com", R.drawable.woman4),
            Contact("Alexander Davis", "7412 678123", "alexander.davis@example.com", R.drawable.man2),
            Contact("Brian Lee", "7939 553456", "brian.lee@example.com", R.drawable.man3),
            Contact("Charlie Smith", "7123 456789", "charlie.smith@example.com", R.drawable.man4),
            Contact("David Turner", "7890 123456", "david.turner@example.com", R.drawable.man5),
            Contact("Emily Williams", "7412 678123", "emily.williams@example.com", R.drawable.woman5),
            Contact("Frank Johnson", "7939 553456", "frank.johnson@example.com", R.drawable.man6),
            Contact("Grace Smith", "7123 456789", "grace.smith@example.com", R.drawable.woman6),
            Contact("Hannah Turner", "7890 123456", "hannah.turner@example.com", R.drawable.man7),
            Contact("Isabella Davis", "7412 678123", "isabella.davis@example.com", R.drawable.woman7),
            Contact("Jack Lee", "7939 553456", "jack.lee@example.com", R.drawable.oldman),
            Contact("Kate Smith", "7123 456789", "kate.smith@example.com", R.drawable.woman8),
            Contact("Lucas Turner", "7890 123456", "lucas.turner@example.com", R.drawable.programmer),
        )

        // Initialize the adapter and set it to the recycler view
        contactsAdapter = ContactsAdapter(contactList) { contact ->
            // Handle click: Use Intent to call the contact
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.phone}"))
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contactsAdapter

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = contactList.filter {
                    it.name.contains(newText ?: "", ignoreCase = true)
                }
                (recyclerView.adapter as ContactsAdapter).updateData(filteredList)
                return true
            }
        })
    }
}
