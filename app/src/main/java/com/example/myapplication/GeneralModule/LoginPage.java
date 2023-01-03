package com.example.myapplication.GeneralModule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MainActivity2;
import com.example.myapplication.MainActivity3;
import com.example.myapplication.MainActivity4;
import com.example.myapplication.MainActivity5;
import com.example.myapplication.R;
import com.example.myapplication.Session;
import com.example.myapplication.ui.Utils.OrdersManage;
import com.example.myapplication.ui.Utils.UsersManage;
import com.example.myapplication.ui.Utils.database.SQLiteManager;

public class LoginPage extends AppCompatActivity {
    private EditText emailText, passwordText;
    private OrdersManage ordersManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        initWidgets();
    }
// more github comment
    private void initWidgets() {
        emailText = findViewById(R.id.editEmail);
        passwordText = findViewById(R.id.editPassword);
    }
    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateUserListArray();
        sqLiteManager.populateOrderListArray();
    }

    public void EnterTheSystem(View view) {
        loadFromDBToMemory();
        String email1 = String.valueOf(emailText.getText());
        String password1 = String.valueOf(passwordText.getText());
        for (UsersManage note : UsersManage.UsersList)
        {
            if((note.getEmail().equals(email1) && (note.getPassword().equals(password1)))){
                Intent Main;
                if(note.getRole().equals("Customer")) {
                    if (note.getActiveOrder() != -1) {
                        for (int i = 0; i < OrdersManage.orderArrayList.size(); i++){
                            if(OrdersManage.orderArrayList.get(i).getId() == note.getActiveOrder()){
                                ordersManage = OrdersManage.orderArrayList.get(i);
                            }
                        }
                        if(ordersManage.getAccepted_user_id() != -1) {
                            Main = new Intent(this, MainActivity5.class);
                        }
                        else {
                            Main = new Intent(this, MainActivity2.class);
                        }
                    }


                    else {
                        Main = new Intent(this, MainActivity.class);
                    }



                }else {
                    if(note.getActiveOrder() == -1){
                    Main = new Intent(this, MainActivity3.class);

                }else{
                    Main = new Intent(this, MainActivity4.class);

                    }
                }
                ((Session) this.getApplication()).setSomeVariable(note.getId());
                finish();
                startActivity(Main);
            }
        }

    }
    public void ForgotPassword(View view) {
        Intent NewPassword = new Intent(this, ForgotPassword.class);
        finish();
        startActivity(NewPassword);
    }
}