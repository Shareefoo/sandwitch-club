package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView ivIngredients;
    private TextView tvAlsoKnown;
    private TextView tvOrigin;
    private TextView tvIngredients;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivIngredients = findViewById(R.id.iv_picture);
        tvAlsoKnown = findViewById(R.id.tv_also_known);
        tvOrigin = findViewById(R.id.tv_origin);
        tvIngredients = findViewById(R.id.tv_ingredients);
        tvDescription = findViewById(R.id.tv_description);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        setTitle(sandwich.getMainName());

        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ivIngredients);

        tvAlsoKnown.setText(sandwich.getAlsoKnownAs().toString());
        tvOrigin.setText(sandwich.getPlaceOfOrigin());
        tvIngredients.setText(sandwich.getIngredients().toString());
        tvDescription.setText(sandwich.getDescription());

    }
}
