package com.example.quanly.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.NameValueDataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.example.quanly.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityThongKe extends AppCompatActivity {
    //    back
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_ke);
        setControl();
        backControl();

        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new NameValueDataEntry("B", "Nhân Viên", 1000));
        data.add(new NameValueDataEntry("B", "Hóa Đơn", 1000));
        data.add(new NameValueDataEntry("B", "Chi Tiết Hóa Đơn", 1000));
        data.add(new NameValueDataEntry("B", "Sản Phẩm", 1000));
//        data.add(new ValueDataEntry("Jake" ,12000));
//        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);
        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);

    }
    private void backControl() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void setControl() {
        imgBack = findViewById(R.id.imgBack);
    }
}