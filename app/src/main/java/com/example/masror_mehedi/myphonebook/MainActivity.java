package com.example.masror_mehedi.myphonebook;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etxtID, etxtName, etxtCell;
    Button btnInsert, btnView, btnUpdate, btnDelete;
    MySQliteDB myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtID = findViewById(R.id.etxt_id);
        etxtName = findViewById(R.id.etxt_name);
        etxtCell = findViewById(R.id.etxt_cell);

        btnInsert = findViewById(R.id.btn_insert);
        btnView = findViewById(R.id.btn_view);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        myDb = new MySQliteDB(MainActivity.this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etxtID.getText().toString();
                String name = etxtName.getText().toString();
                String cell = etxtCell.getText().toString();

                if (id.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Please Enter ID!", Toast.LENGTH_SHORT).show();
                } else if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (cell.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Cell number!", Toast.LENGTH_SHORT).show();
                } else {

                    boolean check = myDb.addToTable(id, name, cell);
                    if (check == true) {
                        Toast.makeText(MainActivity.this, "Data Inserted!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(MainActivity.this, "Data Not Inserted!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public void viewData(View v) {
        Cursor getData = myDb.display();

        if (getData.getCount() == 0) {

            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer buffer = new StringBuffer();
            getData.moveToFirst(); //Cursor object k first row point kore dewa
            do {
                buffer.append("ID:" + getData.getString(0) + "\n");
                buffer.append("Name:" + getData.getString(1) + "\n");
                buffer.append("Cell:" + getData.getString(2) + "\n");
            } while (getData.moveToNext());

            showData("My Contacts", buffer.toString());
        }

    }

    public void showData(String title, String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();
    }

    public void delete(View v) {
        String getId = etxtID.getText().toString();
        int check = myDb.deleteData(getId);
        if (check > 0) {
            Toast.makeText(this, "Data deleted successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data id not deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }



    public void update(View v) {
        String id = etxtID.getText().toString();
        String name = etxtName.getText().toString();
        String cell = etxtCell.getText().toString();

        if (id.isEmpty()) {

            Toast.makeText(MainActivity.this, "Please Enter ID!", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please Enter Name!", Toast.LENGTH_SHORT).show();
        } else if (cell.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please Enter Cell number!", Toast.LENGTH_SHORT).show();
        } else {

            boolean check = myDb.updateData(id, name, cell);
            if (check == true) {
                Toast.makeText(MainActivity.this, "Data Updated!", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(MainActivity.this, "Data Not Updated!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

