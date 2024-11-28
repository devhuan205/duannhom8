package com.example.thoitrang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thoitrang.adapter.ExTopAdapter;
import com.example.thoitrang.adapter.TopAdapter;
import com.example.thoitrang.dao.GiayDAO;
import com.example.thoitrang.database.AppDatabase;
import com.example.thoitrang.databinding.FragmentTopBinding;
import com.example.thoitrang.dto.ItemDto;
import com.example.thoitrang.model.Cart;
import com.example.thoitrang.model.HoaDon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class TopFragment extends Fragment {

    FragmentTopBinding binding;
    AppDatabase appDatabase;
    TopAdapter topAdapter;
    GiayDAO giayDAO;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTopBinding.inflate(inflater, container, false);
        appDatabase = AppDatabase.getInstance(getContext());
        giayDAO = new GiayDAO(requireActivity());
        topAdapter = new TopAdapter(new ArrayList<>());

        binding.lvTop.setAdapter(topAdapter);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        List<HoaDon> hoaDons = appDatabase.appDao().getAllHoaDon();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (HoaDon hoaDon : hoaDons) {
            for (ItemDto itemDto : hoaDon.getItems()) {
                map.merge(itemDto.getMaGiay(), itemDto.getSoLuong(), Integer::sum);
            }
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        List<Map.Entry<Integer, Integer>> top5 = entryList.subList(0, Math.min(5, entryList.size()));

        List<Cart> carts = new ArrayList<>();

        for (Entry<Integer, Integer> entry : top5) {
            carts.add(new Cart(giayDAO.getID(String.valueOf(entry.getKey())), entry.getValue()));
        }

        topAdapter.setList(carts);


    }

    //    package com.example.mau.fragment;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.example.mau.R;
//import com.example.mau.adapter.LoaiGiayAdapter;
//import com.example.mau.adapter.NhanVienAdapter;
//import com.example.mau.dao.NhanVienDAO;
//import com.example.mau.model.LoaiGiay;
//import com.example.mau.model.NhanVien;
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.util.ArrayList;
//
///
//    public class NhanVienFragment extends Fragment {
//        TextInputEditText edUser, edHoTen, edPass, edRePass;
//        Button btnSave, btnCanel;
//        NhanVienDAO dao;
//        ListView lv;
//        ArrayAdapter<NhanVien> adapter;
//        ArrayList<NhanVien> list;
//        NhanVien item;
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//            final View v = inflater.inflate(R.layout.fragment_add_user, container, false);
//            edUser = v.findViewById(R.id.edUserName);
//            edHoTen = v.findViewById(R.id.edFullName);
//            edPass = v.findViewById(R.id.edPassword);
//            lv = v.findViewById(R.id.lvadd);
//            edRePass = v.findViewById(R.id.edRePassword);
//            btnSave = v.findViewById(R.id.btnAddUser);
//            btnCanel = v.findViewById(R.id.btnCancelUser);
//            dao = new NhanVienDAO(getActivity());
//            btnCanel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    edUser.setText("");
//                    edPass.setText("");
//                    edRePass.setText("");
//                    edHoTen.setText("");
//                }
//            });
//            btnSave.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    NhanVien thuThu = new NhanVien();
//                    thuThu.maTT = edUser.getText().toString();
//                    thuThu.hoTen = edHoTen.getText().toString();
//                    thuThu.matKhau = edPass.getText().toString();
//                    if(validate()>0){
//                        if(dao.insert(thuThu) > 0){
//                            Toast.makeText(getActivity(),"Lưu Thành Công",Toast.LENGTH_SHORT).show();
//                            edUser.setText("");
//                            edHoTen.setText("");
//                            edPass.setText("");
//                            edRePass.setText("");
//                        }else{
//                            Toast.makeText(getActivity(),"Lưu Thất Bại",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                }
//            });
//            return v;
//        }
//        public int validate(){
//            int check =1;
//            if(edUser.getText().length() == 0 || edHoTen.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0){
//                Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
//                check = -1;
//            }else{
//                String pass = edPass.getText().toString();
//                String rePass = edRePass.getText().toString();
//                if(!pass.equals(rePass)){
//                    Toast.makeText(getContext(),"Mật Khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
//                    check = -1;
//                }
//            }
//            return check;
//        }
//
//    }
    //
}