package com.example.orvosi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> implements Filterable {
    private ArrayList<BookingItem> BookingItemData;
    private ArrayList<BookingItem> BookingItemDataAll;
    private Context context;
    private int lastPosition = -1;
    private static final String LOG_TAG = MainActivity.class.getName();


    BookingAdapter(Context context, ArrayList<BookingItem> itemsData) {
        this.BookingItemData = itemsData;
        this.BookingItemDataAll = itemsData;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list, parent, false));
    }

    @Override
    public void onBindViewHolder(BookingAdapter.ViewHolder holder, int position) {
        BookingItem currentItem= BookingItemData.get(position);

        holder.bindTo(currentItem);

        if (holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return BookingItemData.size();
    }

    @Override
    public Filter getFilter() {
        return BookingFilter;
    }

    private Filter BookingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<BookingItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = BookingItemDataAll.size();
                results.values = BookingItemDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (BookingItem item : BookingItemDataAll) {
                    if (item.getDiseaseName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            BookingItemData = (ArrayList) filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView doctor;
        private TextView consultingHours;
        private RatingBar rating;


        public ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.diseaseText);
            doctor = itemView.findViewById(R.id.doctorName);
            consultingHours = itemView.findViewById(R.id.consultingHours);
            rating = itemView.findViewById(R.id.rating);

            //TODO navigáció a foglalás oldalra
            itemView.findViewById(R.id.bookAppointment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //((BookingAppointmentActivity)context).bookAppointment();
                    Log.i(LOG_TAG, "Ez nincs megvalósítva.");
                }
            });
        }

        public void bindTo(BookingItem currentItem) {
            titleText.setText(currentItem.getDiseaseName());
            doctor.setText(currentItem.getDoctorName());
            consultingHours.setText(currentItem.getConsultingHours());
            rating.setRating(currentItem.getRating());


        }
    };
};

