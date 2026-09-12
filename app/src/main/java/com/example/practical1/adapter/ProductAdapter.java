package com.example.practical1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical1.R;
import com.example.practical1.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends
        RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    ArrayList<Product> productList;

    public ProductAdapter(
            ArrayList<Product> productList
    ) {

        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(
                parent.getContext()
        ).inflate(
                R.layout.item_product,
                parent,
                false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        Product product =
                productList.get(position);

        holder.name.setText(
                product.getName()
        );

        holder.price.setText(
                product.getPrice()
        );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView name;
        TextView price;

        public ViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            name = itemView.findViewById(
                    R.id.productName
            );

            price = itemView.findViewById(
                    R.id.productPrice
            );
        }
    }
}