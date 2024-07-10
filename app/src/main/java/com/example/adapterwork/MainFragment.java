package com.example.adapterwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    private int gold = 15; // Initialize gold variable
    private Button coinsGen; // Button reference
    private TextView coinsDisplay; // TextView reference
    private TextView incrementDisplay; // TextView reference
    private Items[] shopItems; // Array to hold shop items

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String formatNumbers(int input) {
        if (input < 1000) {
            return String.valueOf(input);
        } else if (input < 1000000) {
            return String.format("%.2fK", input / 1000.0);
        } else if (input < 1000000000) {
            return String.format("%.3fM", input / 1000000.0);
        } else {
            return String.format("%.3fB", input / 1000000000.0);
        }
    }



    private Items[] getShopItems() {
        Items tier1 = new Items();
        tier1.name = "Enhanced Cursor";
        tier1.amount = 0;
        tier1.incrementIncrease = 1;
        tier1.cost = 15;

        Items tier2 = new Items();
        tier2.name = "Miner";
        tier2.amount = 0;
        tier2.incrementIncrease = 5;
        tier2.cost = 200;

        Items tier3 = new Items();
        tier3.name = "Trader";
        tier3.amount = 0;
        tier3.incrementIncrease = 30;
        tier3.cost = 5000;

        Items tier4 = new Items();
        tier4.name = "Bank";
        tier4.amount = 0;
        tier4.incrementIncrease = 200;
        tier4.cost = 20000;

        Items tier5 = new Items();
        tier5.name = "Rocket";
        tier5.amount = 0;
        tier5.incrementIncrease = 4500;
        tier5.cost = 700000;

        Items tier6 = new Items();
        tier6.name = "Book Of Wisdom";
        tier6.amount = 0;
        tier6.incrementIncrease = 80000;
        tier6.cost = 15000000;

        Items tier7 = new Items();
        tier7.name = "Map";
        tier7.amount = 0;
        tier7.incrementIncrease = 1100000;
        tier7.cost = 200000000;

        Items tier8 = new Items();
        tier8.name = "Atomic Collider";
        tier8.amount = 0;
        tier8.incrementIncrease = 15000000;
        tier8.cost = 950000000;


        return new Items[]{tier1, tier2, tier3, tier4, tier5, tier6, tier7, tier8};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize button and set click listener
        coinsGen = view.findViewById(R.id.coinsGen);
        coinsDisplay = view.findViewById(R.id.coinsDisplay);
        incrementDisplay = view.findViewById(R.id.incrementDisplay);

        coinsGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementGold();
            }
        });

        shopItems = getShopItems(); // Initialize shop items
        setShopListView(view); // Set up the shop items RecyclerView
    }


    public void setShopListView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.shop_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ItemsAdapter itemsAdapter = new ItemsAdapter(shopItems, this);
        recyclerView.setAdapter(itemsAdapter);
    }

    private void incrementGold() {
        int totalIncrement = 0;
        for (Items item : shopItems) {
            totalIncrement += item.amount * item.incrementIncrease;
        }
        gold += totalIncrement;
        updateGoldDisplay();
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
        updateGoldDisplay();
    }

    private void updateGoldDisplay() {
        if (gold == 1) {
            coinsDisplay.setText("You have " + gold + " coin.");
        } else {
            coinsDisplay.setText("You have " + formatNumbers(gold) + " coins.");
        };
    }

    public void updateIncrementDisplay() {
        int totalIncrement = 0;
        for (Items item : shopItems) {
            totalIncrement += item.amount * item.incrementIncrease;
        };
        if (totalIncrement == 1) {
            incrementDisplay.setText("You earn " + formatNumbers(totalIncrement) + " coin per click.");
        } else {
            incrementDisplay.setText("You earn " + formatNumbers(totalIncrement) + " coins per click.");
        };
    }
}
