package `in`.crazybytes.currencyconverter.ui.views

import `in`.crazybytes.currencyconverter.R
import `in`.crazybytes.currencyconverter.utils.Helper
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

/**
 * Created By Prabhat Pandey for CrazyBytes
 * on Sunday, 14 February, 2021 at 7:15 AM
 */

class CustomChartMarkerView(context: Context, private val currencySymbol: String): MarkerView(context, R.layout.custom_chart_marker_view) {


    override fun refreshContent(entry: Entry?, highlight: Highlight?) {

        val valueView = rootView.findViewById<TextView>(R.id.valueView)

        entry?.let {

            val markerViewText = if(it.y < 0.01)
                "$currencySymbol ${ Helper.roundToFourDecimalPlacesString(it.y.toDouble()) }"
            else
                "$currencySymbol ${ Helper.roundToTwoDecimalPlacesString(it.y.toDouble()) }"

            valueView.text = markerViewText
        }

        super.refreshContent(entry, highlight)
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        return MPPointF(-posX, -posY)
    }
}