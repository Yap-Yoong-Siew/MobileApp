package com.example.myapplication.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MainActivity3;
import com.example.myapplication.MainActivity4;
import com.example.myapplication.R;
import com.example.myapplication.Session;
import com.example.myapplication.ui.Utils.AdapterOrder;
import com.example.myapplication.ui.Utils.CategoryManage;
import com.example.myapplication.ui.Utils.OrdersManage;
import com.example.myapplication.ui.Utils.OrdersManage;
import com.example.myapplication.ui.Utils.UsersManage;
import com.example.myapplication.ui.Utils.database.SQLiteManager;

import java.util.Calendar;

public class OrderDetailsForWorker extends AppCompatActivity {
    private TextView typeTExtView, DateTextView, priceTextView , descTextView;
    private int currentUser , OrderId;
    private OrdersManage ordersManage;
    private SQLiteManager sqLiteManager;
    private UsersManage usersManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_for_worker);
        currentUser = ((Session) this.getApplication()).getSomeVariable();
        Bundle bundle = getIntent().getExtras();
        OrderId = bundle.getInt("order");
        initWidgets();

        OrdersManage.orderArrayList.clear();
        loadFromDBToMemory();



        for (int i = 0 ; i < OrdersManage.orderArrayList.size(); i++){
            if(OrdersManage.orderArrayList.get(i).getId() == OrderId){
                ordersManage = OrdersManage.orderArrayList.get(i);
            }
        }
        for (int i = 0 ; i < UsersManage.UsersList.size(); i++){
            if(UsersManage.UsersList.get(i).getId() == currentUser){
                usersManage = UsersManage.UsersList.get(i);
            }
        }


        typeTExtView.setText(ordersManage.getType());
        DateTextView.setText(ordersManage.getDate());
        priceTextView.setText(ordersManage.getPrice() + "");
        descTextView.setText(ordersManage.getDescription());




    }

    private void loadFromDBToMemory() {
        sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateOrderListArray();
    }
    private void initWidgets() {

        typeTExtView = findViewById(R.id.textView15);
        priceTextView = findViewById(R.id.textView17);
        DateTextView = findViewById(R.id.textView16);
        descTextView = findViewById(R.id.textView14);


    }

    public void Accept(View view) {
        for (int i = 0 ; i < OrdersManage.orderArrayList.size(); i++){
            if(OrdersManage.orderArrayList.get(i).getId() == OrderId){
                 OrdersManage.orderArrayList.get(i).setAccepted_user_id(currentUser);
            }
        }
        ordersManage.setAccepted_user_id(currentUser);
        usersManage.setActiveOrder(OrderId);
        sqLiteManager.UpdateOrder(ordersManage);
        sqLiteManager.UpdateUser(usersManage);


        Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
        intent.putExtra("order" , OrderId);
        finish();
        startActivity(intent);
    }
}