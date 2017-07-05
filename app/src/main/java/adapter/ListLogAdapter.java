package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.cty9.daunoionline.R;
import com.cty9.daunoionline.object.LogObj;

import java.util.List;

public class ListLogAdapter extends BaseAdapter implements Filterable {

    List<LogObj> mData;
    //List<String> mStringFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;
    Context context;
    public ListLogAdapter(Context context, List<LogObj> cancel_type) {
        mData=cancel_type;
        //mStringFilterList = cancel_type;
        this.context=context;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public LogObj getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        LogObj dataModel = getItem(position);
        String stt = Integer.toString(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row_item_log, parent, false);
            viewHolder.isdn = (TextView) convertView.findViewById(R.id.strisdnlog);
            viewHolder.ngay = (TextView) convertView.findViewById(R.id.ngay);
            viewHolder.txtghichu = (TextView) convertView.findViewById(R.id.ghichu);
            viewHolder.iconstatus = (ImageView) convertView.findViewById(R.id.iconstatus);
            viewHolder.stt = (TextView) convertView.findViewById(R.id.txtstt);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        viewHolder.isdn.setText(dataModel.getIsdn());
        viewHolder.ngay.setText(dataModel.getNgay());
        viewHolder.txtghichu.setText(dataModel.getGhichu());
        viewHolder.stt.setText(stt);

        if (dataModel.getStatus().equals("0"))
        {
            viewHolder.iconstatus.setImageResource(R.drawable.ic_access_time_yellow_500_24dp);
        }
                if (dataModel.getStatus().equals("1"))
                {
                    viewHolder.iconstatus.setImageResource(R.drawable.ic_check_circle_light_green_800_24dp);
                    viewHolder.txtghichu.setText("Thành công");
                }
                else if (dataModel.getStatus().equals("3"))
                {
                    viewHolder.iconstatus.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
                }
                else
                {
                    viewHolder.txtghichu.setText("Đang chờ kiểm duyệt");
                }


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

//            if (constraint != null && constraint.length() > 0) {
//                List<String> filterList = new ArrayList<>();
//                for (int i = 0; i < mStringFilterList.size(); i++) {
//                    if ((mStringFilterList.get(i).toUpperCase()).contains(constraint.toString().toUpperCase())) {
//                        filterList.add(mStringFilterList.get(i));
//                    }
//                }
//                results.count = filterList.size();
//                results.values = filterList;
//            } else {
//                results.count = mStringFilterList.size();
//                results.values = mStringFilterList;
//            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
           // mData = (List<String>) results.values;
            notifyDataSetChanged();
        }

    }
    private class ViewHolder {
        ImageView iconstatus;
        TextView isdn;
        TextView txtghichu;
        TextView ngay;
        TextView stt;
    }
}
