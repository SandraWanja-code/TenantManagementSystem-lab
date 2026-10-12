package com.example.tenantmanagementsystem

import android.content.Intent 
import android.net.Uri 
import android.os.Bundle 
import android.widget.Toast 
import androidx.appcompat.app.AppCompatActivity 
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding 

class MainActivity : AppCompatActivity() { 
    private lateinit var binding: ActivityMainBinding 
    private var lastTenant: Tenant? = null 
    
    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState) 
        binding = ActivityMainBinding.inflate(layoutInflater) 
        setContentView(binding.root) 
        
        // Welcome the user (Try it yourself #2)
        val email = intent.getStringExtra("EMAIL")
        if (email != null) {
            Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
        }
        
        binding.saveButton.setOnClickListener { 
            val name = binding.tenantNameEditText.text.toString().trim() 
            val phone = binding.phoneEditText.text.toString().trim() 
            val rent = binding.rentEditText.text.toString().trim() 
            
            // Validate the tenant (Try it yourself #3)
            var hasError = false
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Required"
                hasError = true
            }
            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Required"
                hasError = true
            }
            if (rent.isEmpty()) {
                binding.rentEditText.error = "Required"
                hasError = true
            }
            
            if (hasError) return@setOnClickListener
            
            val tenant = Tenant(name, phone, rent) 
            binding.tenant = tenant 
            lastTenant = tenant 
            
            Toast.makeText(this, "Tenant saved successfully", Toast.LENGTH_SHORT).show()
        } 
        
        // Implicit Intent: Dialer (Step 12)
        binding.callButton.setOnClickListener { 
            val tenant = lastTenant 
            if (tenant == null) { 
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show() 
                return@setOnClickListener 
            } 
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tenant.phone}")) 
            startActivity(intent) 
        } 

        // Implicit Intent: Share (Try it yourself #1)
        binding.shareButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, tenant.summary())
            startActivity(Intent.createChooser(intent, "Share tenant"))
        }
    }
}