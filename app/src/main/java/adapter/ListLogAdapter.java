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

import com.noc.smsverify.R;
import com.noc.smsverify.activity.FormActivity;
import com.noc.smsverify.object.LogObj;

import java.util.ArrayList;
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
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        String status=dataModel.getStatus();
                if (status.equals("2"))
                {
                    viewHolder.iconstatus.setImageResource(R.drawable.ic_check_circle_light_green_800_24dp);
                }
                else if (status.equals("3"))
                {
                    viewHolder.iconstatus.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
                }
        return convertView;
    }

//    @Override
//        public View getView(int position, View convertView, final ViewGroup parent) {
//            ViewHolder holder = null;
//            LogObj logobj =  getItem(position);
//            if (inflater == null) {
//                inflater = (LayoutInflater) parent.getContext()
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            }
//            //RowItemBinding rowItemBinding = DataBindingUtil.inflate(inflater, R.layout.row_item, parent, false);
//            // rowItemBinding.stringName.setText(mData.get(position));
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.row_item_log, null);
//                holder = new ViewHolder();
//                holder.iconstatus = (ImageView) convertView.findViewById(R.id.iconstatus);
//                holder.isdn = (TextView) convertView.findViewById(R.id.strisdnlog);
//                holder.txtghichu = (TextView) convertView.findViewById(R.id.ghichu);
//                holder.ngay = (TextView) convertView.findViewById(R.id.ngay);
//
//                // setting the image resource and title
//
//                holder.isdn.setText(position+"."+mData.get(position).getIsdn());
//                holder.ngay.setText(mData.get(position).getNgay());
//                holder.txtghichu.setText(mData.get(position).getGhichu());
//
//                String status=logobj.getStatus();
//                if (status.equals("2"))
//                {
//                    holder.iconstatus.setImageResource(R.drawable.ic_check_circle_light_green_800_24dp);
//                }
//                else if (status.equals("3"))
//                {
//                    holder.iconstatus.setImageResource(R.drawable.ic_report_problem_red_400_24dp);
//                }
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//                //result=holder;
//            }
//
//        return convertView;
//        //return rowItemBinding.getRoot();
//    }

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
    }
}
