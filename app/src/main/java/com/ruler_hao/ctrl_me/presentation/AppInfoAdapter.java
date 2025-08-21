package com.ruler_hao.ctrl_me.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ruler_hao.ctrl_me.R;
import com.ruler_hao.ctrl_me.data.AppUsageHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.AppInfoViewHolder> {

    private List<AppUsageHelper.AppInfo> appInfoList;
    private OnMonitorSwitchListener onMonitorSwitchListener;

    public interface OnMonitorSwitchListener {
        void onMonitorSwitchChanged(AppUsageHelper.AppInfo appInfo, boolean isChecked);
    }

    public AppInfoAdapter(List<AppUsageHelper.AppInfo> appInfoList) {
        this.appInfoList = appInfoList;
    }

    public void setOnMonitorSwitchListener(OnMonitorSwitchListener listener) {
        this.onMonitorSwitchListener = listener;
    }

    @NonNull
    @Override
    public AppInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app_info, parent, false);
        return new AppInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppInfoViewHolder holder, int position) {
        AppUsageHelper.AppInfo appInfo = appInfoList.get(position);
        holder.bind(appInfo);
    }

    @Override
    public int getItemCount() {
        return appInfoList != null ? appInfoList.size() : 0;
    }

    public void updateData(List<AppUsageHelper.AppInfo> newAppInfoList) {
        this.appInfoList = newAppInfoList;
        notifyDataSetChanged();
    }

    class AppInfoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivAppIcon;
        private final TextView tvAppName;
        private final TextView tvUsageTime;
        private final Switch switchMonitor;

        public AppInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAppIcon = itemView.findViewById(R.id.iv_app_icon);
            tvAppName = itemView.findViewById(R.id.tv_app_name);
            tvUsageTime = itemView.findViewById(R.id.tv_usage_time);
            switchMonitor = itemView.findViewById(R.id.switch_monitor);
        }

        public void bind(AppUsageHelper.AppInfo appInfo) {
            // Set app icon
            ivAppIcon.setImageDrawable(appInfo.icon);

            // Set app name
            tvAppName.setText(appInfo.appName);

            // Format and set usage time
            tvUsageTime.setText(formatUsageTime(appInfo.usageTimeMillis));

            // Set monitor switch state
            switchMonitor.setOnCheckedChangeListener(null); // Clear previous listener
            switchMonitor.setChecked(appInfo.isMonitorable);

            // Set switch listener
            switchMonitor.setOnCheckedChangeListener((buttonView, isChecked) -> {
                appInfo.isMonitorable = isChecked;
                if (onMonitorSwitchListener != null) {
                    onMonitorSwitchListener.onMonitorSwitchChanged(appInfo, isChecked);
                }
            });
        }

        private String formatUsageTime(long timeInMillis) {
            if (timeInMillis == 0) {
                return itemView.getContext().getString(R.string.usage_time_not_used);
            }

            long hours = TimeUnit.MILLISECONDS.toHours(timeInMillis);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60;

            if (hours > 0) {
                return itemView.getContext().getString(R.string.usage_time_hours_minutes, hours, minutes);
            } else if (minutes > 0) {
                return itemView.getContext().getString(R.string.usage_time_minutes, minutes);
            } else {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillis);
                return itemView.getContext().getString(R.string.usage_time_seconds, seconds);
            }
        }
    }
}