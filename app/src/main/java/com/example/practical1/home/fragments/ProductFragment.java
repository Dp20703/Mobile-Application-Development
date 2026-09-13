package com.example.practical1.home.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical1.R;
import com.example.practical1.adapter.ProductAdapter;
import com.example.practical1.model.Product;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;

    ProductAdapter productAdapter;

    ArrayList<Product> productList;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_product,
                container,
                false
        );

        recyclerView =
                view.findViewById(R.id.productRecyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        loadProducts();

        return view;
    }

    private void loadProducts() {

        productList = new ArrayList<>();

        productList.add(
                new Product("Laptop", "50000")
        );

        productList.add(
                new Product("Mobile", "20000")
        );

        productList.add(
                new Product("Headphones", "2000")
        );

        productList.add(
                new Product("Smart Watch", "5000")
        );

        productAdapter =
                new ProductAdapter(productList);

        recyclerView.setAdapter(productAdapter);
    }

    // ADD PRODUCT
    public void addProduct() {

        View dialogView =
                getLayoutInflater()
                        .inflate(
                                R.layout.dialog_product,
                                null
                        );

        EditText name =
                dialogView.findViewById(R.id.productName);

        EditText price =
                dialogView.findViewById(R.id.productPrice);

        AlertDialog dialog =
                new AlertDialog.Builder(getContext())
                        .setTitle("Add Product")
                        .setView(dialogView)
                        .setPositiveButton(
                                "Add",
                                null
                        )
                        .setNegativeButton(
                                "Cancel",
                                null
                        )
                        .create();

        dialog.setOnShowListener(d -> {

            dialog.getButton(
                    AlertDialog.BUTTON_POSITIVE
            ).setOnClickListener(v -> {

                String productName =
                        name.getText()
                                .toString()
                                .trim();

                String productPrice =
                        price.getText()
                                .toString()
                                .trim();

                if (productName.isEmpty()
                        || productPrice.isEmpty()) {

                    Toast.makeText(
                            getContext(),
                            "Enter all details",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                productList.add(
                        new Product(
                                productName,
                                productPrice
                        )
                );

                productAdapter.notifyDataSetChanged();

                Toast.makeText(
                        getContext(),
                        "Product Added",
                        Toast.LENGTH_SHORT
                ).show();

                dialog.dismiss();
            });
        });

        dialog.show();
    }

    // UPDATE PRODUCT
    public void updateProduct() {

        if (productList.isEmpty()) {
            return;
        }

        EditText input = new EditText(getContext());

        input.setHint("Enter product number");
        input.setInputType(
                InputType.TYPE_CLASS_NUMBER
        );

        new AlertDialog.Builder(getContext())
                .setTitle("Update Product")
                .setMessage(
                        "Enter product number (1, 2, 3...)"
                )
                .setView(input)
                .setPositiveButton(
                        "Next",
                        (dialog, which) -> {

                            try {

                                int position =
                                        Integer.parseInt(
                                                input.getText()
                                                        .toString()
                                        ) - 1;

                                if (position < 0
                                        || position >= productList.size()) {

                                    Toast.makeText(
                                            getContext(),
                                            "Invalid product number",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    return;
                                }

                                showUpdateDialog(position);

                            } catch (Exception e) {

                                Toast.makeText(
                                        getContext(),
                                        "Enter valid number",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        null
                )
                .show();
    }

    private void showUpdateDialog(int position) {

        View dialogView =
                getLayoutInflater()
                        .inflate(
                                R.layout.dialog_product,
                                null
                        );

        EditText name =
                dialogView.findViewById(R.id.productName);

        EditText price =
                dialogView.findViewById(R.id.productPrice);

        Product product =
                productList.get(position);

        name.setText(product.getName());
        price.setText(product.getPrice());

        new AlertDialog.Builder(getContext())
                .setTitle("Update Product")
                .setView(dialogView)
                .setPositiveButton(
                        "Update",
                        (dialog, which) -> {

                            productList.set(
                                    position,
                                    new Product(
                                            name.getText()
                                                    .toString(),
                                            price.getText()
                                                    .toString()
                                    )
                            );

                            productAdapter.notifyDataSetChanged();

                            Toast.makeText(
                                    getContext(),
                                    "Product Updated",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        null
                )
                .show();
    }

    // REMOVE PRODUCT
    public void removeProduct() {

        if (productList.isEmpty()) {
            return;
        }

        EditText input = new EditText(getContext());

        input.setHint("Enter product number");
        input.setInputType(
                InputType.TYPE_CLASS_NUMBER
        );

        new AlertDialog.Builder(getContext())
                .setTitle("Remove Product")
                .setMessage(
                        "Enter product number (1, 2, 3...)"
                )
                .setView(input)
                .setPositiveButton(
                        "Delete",
                        (dialog, which) -> {

                            try {

                                int position =
                                        Integer.parseInt(
                                                input.getText()
                                                        .toString()
                                        ) - 1;

                                if (position < 0
                                        || position >= productList.size()) {

                                    Toast.makeText(
                                            getContext(),
                                            "Invalid product number",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    return;
                                }

                                productList.remove(position);

                                productAdapter.notifyDataSetChanged();

                                Toast.makeText(
                                        getContext(),
                                        "Product Removed",
                                        Toast.LENGTH_SHORT
                                ).show();

                            } catch (Exception e) {

                                Toast.makeText(
                                        getContext(),
                                        "Enter valid number",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        null
                )
                .show();
    }
}