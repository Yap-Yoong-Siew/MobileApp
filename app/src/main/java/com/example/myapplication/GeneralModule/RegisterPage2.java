package com.example.myapplication.GeneralModule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.ui.Utils.UsersManage;
import com.example.myapplication.ui.Utils.database.SQLiteManager;

import java.util.Calendar;

public class RegisterPage2 extends AppCompatActivity {
    private EditText addressEdit, phoneEdit, GenderEdit;
    private Button dateButton;
    private DatePickerDialog datePickerDialog;
    private String role,name,email,password ,phone , address, gender, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page2);
        Bundle bundle = getIntent().getExtras();

        role = bundle.getString("role");
        name = bundle.getString("name");
        email = bundle.getString("email");
        password = bundle.getString("password");
        initWidgets();
        date = getTodaysDate();

        loadFromDBToMemory();


    }

    private void initWidgets() {
        addressEdit = findViewById(R.id.AddressEDIT);
        phoneEdit = findViewById(R.id.Phone_NN);
        GenderEdit = findViewById(R.id.GenderEDIT);
        dateButton = findViewById(R.id.dateButton2);
        dateButton.setText(getTodaysDate());
        initDatePicker();

    }
    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateUserListArray();
    }

    public void ContinueRegister(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        int id = UsersManage.UsersList.size();

        address = String.valueOf(addressEdit.getText());
        phone = String.valueOf(phoneEdit.getText());
        gender = String.valueOf(GenderEdit.getText());

        SQLiteManager sqLiteUsers = SQLiteManager.instanceOfDatabase(this);

        UsersManage user = new UsersManage(id ,role,email,password,date,name,address,gender,phone,-1);
        UsersManage.UsersList.add(user);
        sqLiteUsers.addUserToDatabase(user);
        finish();
        startActivity(intent);
    }

        private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }


    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String dateString = makeDateString(day, month,year);
                dateButton.setText(dateString);
                date = dateString;

            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this , style , dateSetListener , year ,month , day);
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }

    public void DataChoose(View view) {
        datePickerDialog.show();
    }



}