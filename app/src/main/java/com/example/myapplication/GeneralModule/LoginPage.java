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
        loadFromDBToMemory(); //load data from database
        String email1 = String.valueOf(emailText.getText()); //get the email entered by the user
        String password1 = String.valueOf(passwordText.getText()); //get the password entered by the user
        for (UsersManage note : UsersManage.UsersList) // interate through the userlist in the database
        {
            if((note.getEmail().equals(email1) && (note.getPassword().equals(password1)))){ //see if there's a match
                Intent Main;
                if(note.getRole().equals("Customer")) { // check if the matched user is it customer or worker
                    if (note.getActiveOrder() != -1) { //if customer check if the customer got active order or not. -1 means got active order
                        for (int i = 0; i < OrdersManage.orderArrayList.size(); i++){
                            if(OrdersManage.orderArrayList.get(i).getId() == note.getActiveOrder()){
                                ordersManage = OrdersManage.orderArrayList.get(i); //get which order it is
                            }
                        }
                        if(ordersManage.getAccepted_user_id() != -1) {//customer with order placed page but haven't accepted
                            Main = new Intent(this, MainActivity5.class);
                        }
                        else { // customer with order placed page accepted
                            Main = new Intent(this, MainActivity2.class);
                        }
                    }


                    else {// no active order, go to the order categories page
                        Main = new Intent(this, MainActivity.class);
                    }



                }else {//worker got active order
                    if(note.getActiveOrder() == -1){
                    Main = new Intent(this, MainActivity3.class);

                }else{// worker without active order
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