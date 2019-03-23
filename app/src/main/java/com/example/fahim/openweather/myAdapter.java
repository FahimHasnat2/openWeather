package com.example.fahim.openweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder> {
    private Context c;
    private List<Lists> list;

    public myAdapter(Context c, List<Lists> list) {
        this.c = c;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.list_item, viewGroup, false);
        View v2 = LayoutInflater.from(c).inflate(R.layout.first_item, viewGroup, false);
        if(i==0){
            return new viewHolder(v2);
        } else{
            return new viewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        long millis = list.get(i).getDt()*1000;
        Date date = new Date(millis);
        String showDate;

        switch(i){
            case 0:
                DateFormat formatter = new SimpleDateFormat("MMMM d");
                showDate = "Today, "+formatter.format(date);
                break;
            case 1:
                showDate = "Tomorrow";
                break;
            default:
                formatter = new SimpleDateFormat("EEEE");
                showDate = formatter.format(date);
        }


        viewHolder.dateView.setText(showDate);

        List<Weather> weather = list.get(i).getWeather();
        viewHolder.weatherView.setText(weather.get(0).getMain());

        Temp temp = list.get(i).getTemp();
        double maxTemp = Math.round(temp.getMax());
        viewHolder.maxTempView.setText(Integer.toString((int)maxTemp)+"°");

        double minTemp = Math.round(temp.getMin());
        viewHolder.minTempView.setText(Integer.toString((int)minTemp)+"°");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView dateView;
        TextView weatherView;
        TextView maxTempView;
        TextView minTempView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date);
            weatherView = itemView.findViewById(R.id.weather);
            maxTempView = itemView.findViewById(R.id.maxTemp);
            minTempView = itemView.findViewById(R.id.minTemp);
        }
    }
}
