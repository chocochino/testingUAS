package com.example.testinguas

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.testinguas.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    lateinit var myData: MyData
    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentInputBinding>(inflater,
            R.layout.fragment_input,container,false)

        myData = arguments?.getParcelable("main")!!
        binding.myData = myData
        if (myData.filled) {
            setResultActivity()
        } else {
            setMainLayout()
        }
        return binding.root
    }

    private fun submitClickListener() {
        myData.filled = true
        val message = String.format("%s, %s", myData.name, myData.nim)
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

        val resultIntent = Intent(activity, ResultActivity::class.java)
        resultIntent.putExtra("main", myData)
        startActivity(resultIntent)
    }

    private fun nextClickListener() {
        val resultIntent = Intent(activity, EndActivity::class.java)
        resultIntent.putExtra("main", myData)
        startActivity(resultIntent)
    }

    private fun setMainLayout() {
        binding.apply {
            nameInput.visibility = View.VISIBLE
            nimInput.visibility = View.VISIBLE
            instruction.text = getString(R.string.instruction_text)
            submitButton.text = getString(R.string.submit_text)
            submitButton.setOnClickListener { submitClickListener() }

            nameText.visibility = View.GONE
            nimText.visibility = View.GONE
        }
    }

    private fun setResultActivity() {
        binding.apply {
            nameInput.visibility = View.GONE
            nimInput.visibility = View.GONE
            instruction.text = getString(R.string.result_text)
            submitButton.text = getString(R.string.next_text)
            submitButton.setOnClickListener { nextClickListener() }

            nameText.visibility = View.VISIBLE
            nimText.visibility = View.VISIBLE
            nameText.text = myData?.name
            nimText.text = myData?.nim
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.result_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.endActivity -> {
                val resultIntent = Intent(activity, EndActivity::class.java)
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
