package tiger.com.windowlog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tiger.com.windowlog.R;

/**
 * Created by yinghu.gyh on 2016/5/11.
 *
 * @version 1.0
 */
public class LogItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mLogs = new ArrayList<>();

    public LogItemAdapter(Context context) {
        mContext = context;
    }

    public void setLogs(List<String> logs) {
        mLogs.addAll(logs);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLogs.size();
    }

    @Override
    public Object getItem(int position) {
        return mLogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.log_item, null, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        holder.getText().setText(mLogs.get(position));
        return convertView;
    }

    private class Holder{
        private TextView mText;

        public Holder(View rootView) {
            mText = (TextView) rootView.findViewById(R.id.id_item_text);
        }

        public TextView getText() {
            return mText;
        }
    }
}
