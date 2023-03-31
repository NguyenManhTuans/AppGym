package com.example.appgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appgym.ListdataActivity;
import com.example.appgym.activity.MainActivity;

public class ListviewtestActivity extends AppCompatActivity {

    ListView listView;

    ImageView btnBack;
    String[] exerciseNames = {"Push Up", "Mountain Climber", "Two Leg Hips"};
    int[] exerciseImages = {R.drawable.pushup, R.drawable.mountain_climber,  R.drawable.two_legs_hips};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewtest);

        listView = findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        btnBack = findViewById(R.id.backButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getApplicationContext(), ListdataActivity.class);
//                intent.putExtra("name", exerciseNames[i]);
//                intent.putExtra("image", exerciseImages[i]);
//                startActivity(intent);
//            }
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), ListdataActivity.class);
                intent.putExtra("name", exerciseNames[position]);
                intent.putExtra("vitri",position);
                intent.putExtra("image", exerciseImages[position]);
                startActivity(intent);
            }

        });
    }
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exerciseNames.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data,null);

            TextView name = view1.findViewById(R.id.txtExcercises);
            ImageView image = view1.findViewById(R.id.imgExercises);

            name.setText(exerciseNames[i]);
            image.setImageResource(exerciseImages[i]);
            return view1;
        }
    }
}


