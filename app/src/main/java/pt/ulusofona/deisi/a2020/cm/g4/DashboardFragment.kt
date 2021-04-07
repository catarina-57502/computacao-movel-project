package pt.ulusofona.deisi.a2020.cm.g4

import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import io.github.dvegasa.arcpointer.ArcPointer
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.eazegraph.lib.charts.StackedBarChart
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.StackedBarModel
import pt.ulusofona.deisi.a2020.cm.g4.data.DataSource

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val arcPointer: ArcPointer = getView()!!.findViewById(R.id.arcpointer)
        arcPointer.value = current_level
        arcPointer.setNotches(3)
        val cores = listOf(Color.GREEN, Color.YELLOW, Color.RED)
        arcPointer.setNotchesColors(cores.toIntArray())
        arcPointer.setNotchesStrokeWidth(15.0f)
        arcPointer.setAnimated(true)
        arcPointer.setMarkerLengthRatio(0.2f)
        arcPointer.setLineLengthRatio(0.7f)
        arcPointer.setMarkerStrokeWidth(7.0f)
        arcPointer.setLineStrokeWidth(7.0f)

        if(current_level==0.75f){
            current_level = danger_levels.get(0)
        }else{
            current_level = danger_levels.get(danger_levels.indexOf(current_level)+1)
        }

        confirmados_api.text = truncateNumber(DataSource().confirmados.toFloat()).toString()
        obitos_api.text = truncateNumber(DataSource().obitos.toFloat()).toString()
        recuperados_api.text = truncateNumber(DataSource().recuperados.toFloat()).toString()
        internados_api.text = DataSource().internados.toString()
        internados_uci_api.text = DataSource().internados_uci.toString()

        norte_api.text = DataSource().confirmados_arsnorte.toString()
        centro_api.text = DataSource().confirmados_arscentro.toString()
        lvt_api.text = DataSource().confirmados_arslvt.toString()
        alentejo_api.text = DataSource().confirmados_arsalentejo.toString()
        algarve_api.text = DataSource().confirmados_arsalgarve.toString()
        acores_api.text = DataSource().confirmados_acores.toString()
        madeira_api.text = DataSource().confirmados_madeira.toString()

        val mStackedBarChart: StackedBarChart = getView()!!.findViewById(R.id.stackedbarchart) as StackedBarChart

        val conf_0_9= StackedBarModel("0-9")

        conf_0_9.addBar(BarModel(DataSource().confirmados_0_9_f.toFloat(), resources.getColor(R.color.pink)))
        conf_0_9.addBar(BarModel(DataSource().confirmados_0_9_m.toFloat(), -0xa9480f))

        val conf_10_19 = StackedBarModel("10-19")
        conf_10_19.addBar(BarModel(DataSource().confirmados_10_19_f.toFloat(), resources.getColor(R.color.pink)))
        conf_10_19.addBar(BarModel(DataSource().confirmados_10_19_m.toFloat(), -0xa9480f))

        val conf_20_29 = StackedBarModel("20-29")

        conf_20_29.addBar(BarModel(DataSource().confirmados_20_29_f.toFloat(), resources.getColor(R.color.pink)))
        conf_20_29.addBar(BarModel(DataSource().confirmados_20_29_m.toFloat(), -0xa9480f))

        val conf_30_39 = StackedBarModel("30-39")
        conf_30_39.addBar(BarModel(DataSource().confirmados_30_39_f.toFloat(), resources.getColor(R.color.pink)))
        conf_30_39.addBar(BarModel(DataSource().confirmados_30_39_m.toFloat(), -0xa9480f))

        val conf_40_49 = StackedBarModel("40-49")
        conf_40_49.addBar(BarModel(DataSource().confirmados_40_49_f.toFloat(), resources.getColor(R.color.pink)))
        conf_40_49.addBar(BarModel(DataSource().confirmados_40_49_m.toFloat(), -0xa9480f))

        val conf_50_59 = StackedBarModel("50-59")
        conf_50_59.addBar(BarModel(DataSource().confirmados_50_59_f.toFloat(), resources.getColor(R.color.pink)))
        conf_50_59.addBar(BarModel(DataSource().confirmados_50_59_m.toFloat(), -0xa9480f))

        val conf_60_69 = StackedBarModel("60-69")
        conf_60_69.addBar(BarModel(DataSource().confirmados_60_69_f.toFloat(), resources.getColor(R.color.pink)))
        conf_60_69.addBar(BarModel(DataSource().confirmados_60_69_m.toFloat(), -0xa9480f))

        val conf_70_79 = StackedBarModel("70-79")
        conf_70_79.addBar(BarModel(DataSource().confirmados_70_79_f.toFloat(), resources.getColor(R.color.pink)))
        conf_70_79.addBar(BarModel(DataSource().confirmados_70_79_m.toFloat(), -0xa9480f))

        val conf_80_plus = StackedBarModel("80+")
        conf_80_plus.addBar(BarModel(DataSource().confirmados_80_plus_f.toFloat(), resources.getColor(R.color.pink)))
        conf_80_plus.addBar(BarModel(DataSource().confirmados_80_plus_m.toFloat(), -0xa9480f))

        mStackedBarChart.addBar(conf_0_9)
        mStackedBarChart.addBar(conf_10_19)
        mStackedBarChart.addBar(conf_20_29)
        mStackedBarChart.addBar(conf_30_39)
        mStackedBarChart.addBar(conf_40_49)
        mStackedBarChart.addBar(conf_50_59)
        mStackedBarChart.addBar(conf_60_69)
        mStackedBarChart.addBar(conf_70_79)
        mStackedBarChart.addBar(conf_80_plus)

        mStackedBarChart.startAnimation()

         if(DataSource().rt_nacional<=1){
             rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_ok))
        }else{
             rt_image.setImageDrawable(resources.getDrawable(R.drawable.ic_bad))
        }


        rt_text.text = resources.getString(R.string.rt) + " = " + DataSource().rt_nacional

    }

    fun truncateNumber(floatNumber: Float): String? {
        val thousand = 1000L
        val million = 1000000L
        val billion = 1000000000L
        val trillion = 1000000000000L
        val number = Math.round(floatNumber).toLong()
        if(number<million){
            val fraction = calculateFraction(number, thousand)
            return java.lang.Float.toString(fraction) + "m"
        }
        if (number >= million && number < billion) {
            val fraction = calculateFraction(number, million)
            return java.lang.Float.toString(fraction) + "M"
        } else if (number >= billion && number < trillion) {
            val fraction = calculateFraction(number, billion)
            return java.lang.Float.toString(fraction) + "B"
        }
        return java.lang.Long.toString(number)
    }

    fun calculateFraction(number: Long, divisor: Long): Float {
        val truncate = (number * 10L + divisor / 2L) / divisor
        return truncate.toFloat() * 0.10f
    }

}