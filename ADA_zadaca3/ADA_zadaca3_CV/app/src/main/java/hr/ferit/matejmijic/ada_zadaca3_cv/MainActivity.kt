package hr.ferit.matejmijic.ada_zadaca3_cv

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){

    override fun getViewResourceId(): Int = R.layout.activity_main

    override fun setUpUi() {
        fbIcon.setOnClickListener{fbIconClicked()}
        igIcon.setOnClickListener{igIconClicked()}
    }

    private fun igIconClicked() {
        val igIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/matej_mijiic/"))
        startActivity(igIntent)
    }

    private fun fbIconClicked() {
        val fbIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/matej.mijic3"))
        startActivity(fbIntent)
    }


}
