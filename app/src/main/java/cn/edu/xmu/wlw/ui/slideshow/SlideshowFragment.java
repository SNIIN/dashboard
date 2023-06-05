package cn.edu.xmu.wlw.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import cn.edu.xmu.wlw.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.xmu.wlw.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView listView = binding.alarmShow;

        // 准备数据源
        String[] alarmInfo = {"Alarm 1", "Alarm 2", "Alarm 3", "Alarm 4", "Alarm 5","Alarm 1", "Alarm 2", "Alarm 3", "Alarm 4", "Alarm 5","Alarm 1", "Alarm 2", "Alarm 3", "Alarm 4", "Alarm 5"};
        String[] alarmTime = {"10:00", "11:00", "12:00", "13:00", "14:00","10:00", "11:00", "12:00", "13:00", "14:00","10:00", "11:00", "12:00", "13:00", "14:00"};
        ArrayList<HashMap<String, String>> listItems = new ArrayList<>();
        for (int i = 0; i < alarmInfo.length; i++) {
            HashMap<String, String> listItem = new HashMap<>();
            listItem.put("告警信息", alarmInfo[i]);
            listItem.put("告警时间", alarmTime[i]);
            listItems.add(listItem);
        }

// 创建 SimpleAdapter 适配器
        SimpleAdapter adapter = new SimpleAdapter(getContext(), listItems, R.layout.list_header,
                new String[]{"告警信息", "告警时间"}, new int[]{R.id.alarm_info_text_view, R.id.alarm_time_text_view});

// 设置适配器
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}