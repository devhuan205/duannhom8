package com.example.thoitrang.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoitrang.databinding.TopItemBinding;
import com.example.thoitrang.model.Cart;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private final List<Cart> list;

    public TopAdapter(List<Cart> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(TopItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.ViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.binding.tvTenGiayT.setText(cart.giay.tenGiay);
        holder.binding.tvsoLuong.setText(cart.soLuong + "");
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Cart> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TopItemBinding binding;

        public ViewHolder(TopItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
