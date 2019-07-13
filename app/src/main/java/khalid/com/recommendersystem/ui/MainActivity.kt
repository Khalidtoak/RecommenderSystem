package khalid.com.recommendersystem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import khalid.com.recommendersystem.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
     var  bankToUse  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gloryIsChecked = checkBox.isChecked
        var apexIsChecked = checkBox2.isChecked
        var stdChatsIsChecked = checkBox3.isChecked


        button.setOnClickListener {
            if (amount_text.text.toString().isEmpty()) {
                amount_text.error = "Amount cannot be empty"
                return@setOnClickListener
            }
            if (amount_text.text.toString().toInt() < 500 || amount_text.text.toString().toInt() > 500000) {
                amount_text.error = "Amount has to be within 500 to 500,000 naira"
                return@setOnClickListener
            }
            var listOfBanks = getCheckedBanks()
           val bankToUse =  calculateRewards(amount_text.text.toString().toInt())
            AlertDialog.Builder(this@MainActivity).setMessage(bankToUse).setTitle("Bank to use").show()
        }
    }
    private fun calculateRewards(amount : Int) : String{
        var ans = 0
       val maxRewardForGtb =  amount.times((5.75.div(100))) - 70
        val maxRewardForApc= amount.times((6.div(100))) - 120
        val maxRewardForSTC = amount.times((6.25.div(100))) - (1/100).times(amount)
        if (maxRewardForApc > maxRewardForSTC){
            ans = maxRewardForApc
            bankToUse = "Apex Bank"
        }
        else {
            ans = maxRewardForSTC.toInt()
            bankToUse = "Standard Charts"
        }
        if (ans < maxRewardForGtb){
            ans = maxRewardForGtb.toInt()
            bankToUse = "Glory Trust Bank"
        }
        return  "The best bank to use for this transaction is $bankToUse"

    }
    private fun getCheckedBanks() : List<String>{
        val listOfBanks = mutableListOf<String>()
        if (checkBox.isChecked){
            listOfBanks.add("Glory trust Bank")
        }
        if (checkBox2.isChecked){
            listOfBanks.add("Apex Bank")
        }
        if (checkBox3.isChecked){
            listOfBanks.add("Standard Charts")
        }
        return listOfBanks
    }
    
}
