package com.example.appgym.activity.workout;

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

import com.example.appgym.R;

public class ChestviewActivity extends AppCompatActivity {

    ListView listView;
    TextView txtWorkName;
    ImageView btnBack;
    String[] exerciseNames = {
            "Cobra Crunches", "Push Up", "Jumping Jacks",
            "Crunches Stand","Jump Push Up", "Side Touch Heel",
            "Side Plank Push Up", "Crunches Reach"
    };
    int[] exerciseImages = {
            R.drawable.cobra_crunches, R.drawable.pushup,  R.drawable.jumping_jack,
            R.drawable.crunches_stand,  R.drawable.jump_pushup, R.drawable.side_touch_heel,
            R.drawable.side_plank_pushup, R.drawable.crunches_reach

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewtest);

        listView = findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        btnBack = findViewById(R.id.backButton);
        txtWorkName=findViewById(R.id.txtWorkoutName);
        txtWorkName.setText("CHEST WORKOUT");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), ChestActivity.class);
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


