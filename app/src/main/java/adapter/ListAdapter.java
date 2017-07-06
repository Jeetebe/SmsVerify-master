package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.cty9.daunoionline.R;
import com.cty9.daunoionline.activity.FormActivity;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter implements Filterable {

    List<String> mData;
    List<String> mStringFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;
    Context context;
    public ListAdapter(Context context,List<String> cancel_type) {
        mData=cancel_type;
        mStringFilterList = cancel_type;
        this.context=context;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    // Get the data item for this position
    //int size= mData.size();
    final String strisdn = getItem(position);
    final String stt = Integer.toString(position+1);
    // Check if an existing view is being reused, otherwise inflate the view
    ViewHolder viewHolder; // view lookup cache stored in tag

    final View result;

    if (convertView == null) {

        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.row_item, parent, false);
        viewHolder.isdn = (TextView) convertView.findViewById(R.id.stringName);
        viewHolder.stt = (TextView) convertView.findViewById(R.id.txtstt);
        viewHolder.btn = (Button) convertView.findViewById(R.id.btndangky);
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, FormActivity.class);
                    intent.putExtra("isdn",strisdn);
                    intent.putExtra("isdangky",false);
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, 300);
                    activity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.null_animation);

                }
            });

        result=convertView;

        convertView.setTag(viewHolder);
    } else {
        viewHolder = (ViewHolder) convertView.getTag();
        result=convertView;
    }


    viewHolder.isdn.setText(strisdn);
    viewHolder.stt.setText(stt);

    return convertView;
}

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<String> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List<String>) results.values;
            notifyDataSetChanged();
        }

    }
    private class ViewHolder {
        ImageView icon;
        TextView isdn;
        Button btn;
        TextView stt;
    }
}
