package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.msg.spapp.R;

import java.util.List;

import Entities.Service;
import Entities.ServiceCategory;

/**
 * Created by julianmolina on 29/12/15.
 */
public class ServiceAdapter extends BaseAdapter {

    private Context context;
    private List<Service> items;

    public ServiceAdapter(Context context, List<Service> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        Service service = (Service) getItem(position);

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_service, parent, false);
        }

        LinearLayout dad = (LinearLayout) rowView.findViewById(R.id.lyDad);
        dad.setBackgroundResource(rowView.getResources().getIdentifier(service.getImage(), "drawable", context.getPackageName()));

        TextView title = (TextView) rowView.findViewById(R.id.service_title);
        title.setText(service.getName());

        TextView description = (TextView) rowView.findViewById(R.id.service_description);
        description.setText(service.getDescription());

        return rowView;
    }

}
