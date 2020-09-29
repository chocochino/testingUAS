package com.example.testinguas

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.testinguas.databinding.FragmentEndBinding
import com.example.testinguas.databinding.FragmentInputBinding

class EndFragment : Fragment() {

    lateinit var myData: MyData
    private lateinit var binding: FragmentEndBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentEndBinding>(inflater,
            R.layout.fragment_end,container,false)

        myData = arguments?.getParcelable("main")!!
        binding.myData = myData

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.end_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.resultActivity -> {
                val resultIntent = Intent(activity, ResultActivity::class.java)
                resultIntent.putExtra("main", myData)
                startActivity(resultIntent)
                true
            }
            R.id.mainActivity -> {
                val resultIntent = Intent(activity, MainActivity::class.java)
                resultIntent.putExtra("main", myData)
                startActivity(resultIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}