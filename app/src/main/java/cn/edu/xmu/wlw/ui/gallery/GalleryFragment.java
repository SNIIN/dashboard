package cn.edu.xmu.wlw.ui.gallery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.xmu.wlw.databinding.FragmentGalleryBinding;
import cn.edu.xmu.wlw.utils.ToastUtils;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ColumnChartView columnChartView;

    private PieChartView pieChartView;

    public void drawPieChart() {
        // 创建饼图数据对象
        List<SliceValue> pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.parseColor("#FE6DA8")).setLabel("插座1"));
        pieData.add(new SliceValue(25, Color.parseColor("#56B7F1")).setLabel("插座2"));
        pieData.add(new SliceValue(10, Color.parseColor("#CDA67F")).setLabel("空调"));
        pieData.add(new SliceValue(60, Color.parseColor("#FED70E")).setLabel("热水器"));

        // 创建饼图属性对象
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);

        // 设置饼图属性
        pieChartView.setPieChartData(pieChartData);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Random random = new Random();
        // 绘制柱状图
        List<Column> columns = new ArrayList<>();
        int[] data = new int[10];
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt(20);
            axisValues.add(new AxisValue(i).setLabel(((Integer)random.nextInt(10)).toString()));
            List<SubcolumnValue> values = new ArrayList<>();
            values.add(new SubcolumnValue(data[i], Color.rgb(144,225,156)));
            Column column = new Column(values);
            columns.add(column);
        }
        // 绘制柱状图
        ColumnChartData columnChartData = new ColumnChartData(columns);
        Axis axisX = new Axis(axisValues);
        Axis axisY = new Axis().setHasLines(true);
        columnChartData.setAxisYLeft(axisY);
        columnChartData.setAxisXBottom(axisX);
        columnChartView.setColumnChartData(columnChartData);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        columnChartView = binding.powerColumnChart;
        columnChartView.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
                ToastUtils.showMyToast(getContext(), String.format("1月%d日宿舍总用电量:%.1f度", columnIndex+1, value.getValue()), Toast.LENGTH_SHORT);
                drawPieChart();
                pieChartView.setValueTouchEnabled(true);
            }

            @Override
            public void onValueDeselected() {
                // 在这里处理取消选择事件
            }
        });
        pieChartView = binding.powerPieChart;
        pieChartView.setValueTouchEnabled(false);
        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                ToastUtils.showMyToast(getContext(), String.format("1月%d日%s用电量为%.1f度", arcIndex, "空调", 10.0), Toast.LENGTH_SHORT);
            }

            @Override
            public void onValueDeselected() {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}