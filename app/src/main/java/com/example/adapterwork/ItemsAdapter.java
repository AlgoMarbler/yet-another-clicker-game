package com.example.adapterwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private final Items[] items;
    private final MainFragment mainFragment;

    public ItemsAdapter(Items[] items, MainFragment mainFragment) {
        this.items = items;
        this.mainFragment = mainFragment;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Items item = items[position];
        holder.itemName.setText(item.name);
        holder.itemBenefit.setText("+" + mainFragment.formatNumbers(item.incrementIncrease) + " coins per click");
        holder.amountOwned.setText("(x" + item.amount + ")");
        holder.itemCostAndPurchase.setText("Buy (" + mainFragment.formatNumbers(item.cost) + ")");

        // Set OnClickListener for the purchase button
        holder.itemCostAndPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userGold = mainFragment.getGold();
                if (userGold >= item.cost) {
                    mainFragment.setGold(userGold - item.cost);
                    item.amount++;
                    holder.amountOwned.setText("(x" + item.amount + ")");
                    Toast.makeText(v.getContext(), "Purchased " + item.name, Toast.LENGTH_SHORT).show();
                    mainFragment.updateIncrementDisplay();
                } else {
                    Toast.makeText(v.getContext(), "Not enough gold!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemBenefit;
        TextView amountOwned;
        Button itemCostAndPurchase;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemBenefit = itemView.findViewById(R.id.itemBenefit);
            amountOwned = itemView.findViewById(R.id.amountOwned);
            itemCostAndPurchase = itemView.findViewById(R.id.itemCostAndPurchase);
        }
    }
}
