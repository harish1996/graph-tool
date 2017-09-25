package com.dev.harish.graphingapp;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidplot.xy.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.*;
import com.androidplot.util.PixelUtils;


import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private XYPlot xyplot ;
    final ArrayList<Number> a;
    ArrayList<Number> b;
    EditText input ;
    LineAndPointFormatter iformat ;
    LineAndPointFormatter eformat ;
    XYSeries incomeSeries;

    View.OnClickListener buttonlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Number temp = (Number) Double.parseDouble(input.getText().toString());
            //b.add(temp);
            incomeSeries.size()
            input.getText().clear();

            XYSeries
            xyplot.addSeries(incomeSeries, iformat);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = new ArrayList<Number>();
        b = new ArrayList<Number>();
        iformat = new LineAndPointFormatter(this, R.xml.line_formatter);
        eformat = new LineAndPointFormatter(this, R.xml.line_formatter_2);
        xyplot = (XYPlot) findViewById(R.id.xyplot);
        xyplot.setDomainBoundaries(-15,15,BoundaryMode.FIXED);
        xyplot.getGraph().getLineLabelInsets().setLeft(PixelUtils.dpToPix(5));

        input = (EditText) findViewById(R.id.input);
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener( buttonlistener );
        //final Number[] domainLabels = {1, 2, 3};
        //Number[] income = {2000, 4000, 9000};
        //Number[] expense = {4000, 1000, 7000};
        incomeSeries = new XYSeries("incomeSeries");
        incomeSeries.
        /*XYSeries expenseSeries = new SimpleXYSeries(
                Arrays.asList(expense),
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Expense"
        );*/


        eformat.getLinePaint().setPathEffect(new DashPathEffect(new float[]{
                PixelUtils.dpToPix(20), PixelUtils.dpToPix(15)},0));

        iformat.setInterpolationParams(new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        eformat.setInterpolationParams(new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        //xyplot.addSeries(expenseSeries, eformat);
        xyplot.addSeries(incomeSeries, iformat);

        xyplot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                    int i = Math.round(((Number) obj).floatValue());
                    return toAppendTo.append(a.get(i));
                }
            @Override
            public Object parseObject (String source, ParsePosition pos){
                    return null;
            }
        });
    }
}
