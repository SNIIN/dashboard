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

import cn.edu.xmu.wlw.databinding.FragmentGalleryBinding;
import cn.edu.xmu.wlw.utils.ToastUtils;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ColumnChartView columnChartView = binding.powerColumnChart;
        List<Column> columns = new ArrayList<>();
        int[] data = new int[]{7,8,9,14,6};
        for (int i = 0; i < data.length; ++i) {
            List<SubcolumnValue> values = new ArrayList<>();
            values.add(new SubcolumnValue(data[i], Color.rgb(144,225,156)));
            Column column = new Column(values);
            columns.add(column);
        }
        ColumnChartData columnChartData = new ColumnChartData(columns);

        List<AxisValue> axisValues = new ArrayList<>();
        axisValues.add(new AxisValue(0).setLabel("1.1"));
        axisValues.add(new AxisValue(1).setLabel("1.2"));
        axisValues.add(new AxisValue(2).setLabel("1.3"));
        axisValues.add(new AxisValue(3).setLabel("1.4"));
        axisValues.add(new AxisValue(4).setLabel("1.5"));
        Axis axisX = new Axis(axisValues);

        Axis axisY = new Axis().setHasLines(true);
        columnChartData.setAxisYLeft(axisY);
        columnChartData.setAxisXBottom(axisX);

        columnChartView.setColumnChartData(columnChartData);
        columnChartView.setOnValueTouchListener(new ColumnChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
                ToastUtils.showMyToast(getContext(), String.format("1月%d日用电量: %.1f°", columnIndex, value.getValue()), Toast.LENGTH_LONG);
            }

            @Override
            public void onValueDeselected() {
                // 在这里处理取消选择事件
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