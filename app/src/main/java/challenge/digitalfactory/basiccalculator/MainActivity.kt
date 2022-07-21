package challenge.digitalfactory.basiccalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

/**
 * This activity controls the operation of the basic calculator, so the functions are VERY basic but
 * still intuitive
 *
 * Some things to note are:
 *
 * 1. The calculator is designed to handle any input from the buttons and display a result (no crash)
 * 2. If an input cannot be processed or is invalid, an error is shown
 *
 * ~By MB
 */
class MainActivity : AppCompatActivity() {

    private var resultShown = false  //used to reset the display when a number is pressed after a result is displayed
    private val decimalFormat = DecimalFormat("0.##########")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**executed when a number is pressed. The first number is displayed in place of the initial 0
     * all inputs after that are appended
     *
    * @param view the view for the number being clicked
    */
    fun onNumberPressed(view:View) {
        if(textArea.text.toString() == "0" || resultShown) {
            textArea.text = ""
            resultShown = false
        }

        textArea.append((view as Button).text)
    }

    /**executed when an arithmetic operator is pressed. all inputs are appended and evaluated
     * when the equal button is clicked
     *
     * @param view the view for the operator being clicked
     */
    fun onOperatorPressed(view:View) {
        resultShown = false
        textArea.append((view as Button).text)
    }

    /**executed when the AC button is pressed. This will clear all inputs and reset the display to 0
     *
     * @param view the view for the AC being clicked
     */
    fun onACPressed(view: View){
        this.textArea.text = "0"
    }

    /**executed when the SIN button is pressed. This will compute the sine of the input displayed
     * if it is possible. Otherwise an error is shown
     *
     * @param view the view for the sine function being clicked
     */
    fun onSinePressed(view: View){
        try {
            val value = textArea.text.toString()
            val sine = sin(value.toDouble())
            this.textArea.text = sine.toString()
            resultShown = true
        }
        catch(exception: Exception){
            exception.printStackTrace()
            this.textArea.text = "Error"
        }
    }

    /**executed when the COS button is pressed. This will compute the cosine of the input displayed
     * if it is possible. Otherwise an error is shown
     *
     * @param view the view for the cosine function being clicked
     */
    fun onCosinePressed(view: View){
        try {
            val value = textArea.text.toString()
            val cosine = cos(value.toDouble())
            this.textArea.text = cosine.toString()
            resultShown = true
        }
        catch(exception: Exception){
            exception.printStackTrace()
            this.textArea.text = "Error"
        }
    }

    /**executed when the TAN button is pressed. This will compute the tangent of the input displayed
     * if it is possible. Otherwise an error is shown
     *
     * @param view the view for the tangent function being clicked
     */
    fun onTangentPressed(view: View){
        try {
            val value = textArea.text.toString()
            val tangent = tan(value.toDouble())
            this.textArea.text = tangent.toString()
            resultShown = true
        }
        catch(exception: Exception){
            exception.printStackTrace()
            this.textArea.text = "Error"
        }
    }

    /**executed when the = button is pressed. This will evaluate the input displayed
     * if it is possible and display the result. Otherwise an error is shown
     *
     * @param view the view for the equal sign being clicked
     */
    fun onEqualPressed(view: View) {
        try{
            val text = textArea.text.toString()
            val eval = ExpressionBuilder(text).build()
            val result = decimalFormat.format(eval.evaluate())
            textArea.text = result
        }
        catch(exception: Exception){
            exception.printStackTrace()
            textArea.text = "Error"
        }
        finally {
            resultShown = true
        }
    }
}