package com.example.springjal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarChart_WaterQuality extends AppCompatActivity {
    private List<String>Spring= Arrays.asList("SGNR","MDU","DEL","BOM","CHEN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_water_quality);
        BarChart barChart=findViewById(R.id.chart1);
        barChart.getAxisRight().setDrawLabels(false);
        ArrayList<BarEntry> enteries= new ArrayList<>();
        enteries.add(new BarEntry(0,45f));
        enteries.add(new BarEntry(1,85f));
        enteries.add(new BarEntry(2,35f));
        enteries.add(new BarEntry(3,25f));
        enteries.add(new BarEntry(4,55f));
        YAxis yAxis=barChart.getAxisLeft();
        yAxis.setAxisMaximum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);
        BarDataSet dataSet= new BarDataSet(enteries,"Quality");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData=new BarData(dataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(Spring));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

    }
}