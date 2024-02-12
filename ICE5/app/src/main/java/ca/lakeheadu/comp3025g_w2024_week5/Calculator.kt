package ca.lakeheadu.comp3025g_w2024_week5

import android.view.View
import ca.lakeheadu.comp3025g_w2024_week5.databinding.ActivityMainBinding

class Calculator (binding: ActivityMainBinding)
{
    private var m_binding: ActivityMainBinding
    private var m_resultString: String

    init {
        this.m_binding = binding
        this.m_resultString = ""

        initializeOnClickListeners()
    }

    private fun initializeOnClickListeners() {
        // Listeners for Extras Buttons
        m_binding.clearButton.setOnClickListener { view -> processExtraButtons(view) }
        m_binding.percentButton.setOnClickListener { view -> processExtraButtons(view) }
        m_binding.backspaceButton.setOnClickListener { view -> processExtraButtons(view) }
        m_binding.plusminusButton.setOnClickListener { view -> processExtraButtons(view) }

        // Listeners for the Operator Buttons
        m_binding.divideButton.setOnClickListener { view -> processOperatorButtons(view) }
        m_binding.multiplyButton.setOnClickListener { view -> processOperatorButtons(view) }
        m_binding.addButton.setOnClickListener { view -> processOperatorButtons(view) }
        m_binding.subtractButton.setOnClickListener { view -> processOperatorButtons(view) }
        m_binding.equalsButton.setOnClickListener { view -> processOperatorButtons(view) }

        // Listeners for the Number Buttons (and decimal)
        m_binding.zeroButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        m_binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }
    }

    private fun processOperatorButtons(view: View)
    {
        this.m_binding.resultTextView.text = view.tag.toString()
    }

    private fun processExtraButtons(view: View)
    {
        when (view.tag.toString())
        {
            "backspace" -> {
                if((this.m_resultString.length < 2) ||
                  ((this.m_resultString.length == 2) && (this.m_resultString.contains("-"))))
                {
                    clear()
                }
                else
                {
                    this.m_resultString = this.m_resultString.dropLast(1)
                    this.m_binding.resultTextView.text = this.m_resultString
                }
            }
            "clear" ->
            {
                clear()
            }
            "plus_minus" ->
            {
                if(this.m_resultString.isNotEmpty())
                {
                    if(this.m_resultString.startsWith("-"))
                    {
                        this.m_resultString = this.m_resultString.removePrefix("-")
                    }
                    else
                    {
                        this.m_resultString = "-" + this.m_resultString
                    }
                    this.m_binding.resultTextView.text = this.m_resultString
                }
            }
        }
    }

    private fun processNumberButtons(view: View)
    {
        when (view.tag.toString())
        {
            "." ->
                {
                    if(this.m_resultString == "")
                    {
                        this.m_resultString = "0."
                    }
                    else if(!this.m_resultString.contains("."))
                    {
                        this.m_resultString += view.tag.toString()
                    }
                }
            else ->
            {
                this.m_resultString += view.tag.toString()
            }
        }

        this.m_binding.resultTextView.text = this.m_resultString
    }

    private fun clear()
    {
        this.m_resultString = ""
        this.m_binding.resultTextView.text = "0"
    }
}