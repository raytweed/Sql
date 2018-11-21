package application.android.example.com.sql;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText Stu_id,Stu_name,Stu_add,Stu_contact;
    Button save_bt,showbt,updatebt,deletebt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb =new DatabaseHelper(this);
        Stu_name=findViewById(R.id.stuname);
        showbt=findViewById(R.id.showbt);
        updatebt=findViewById(R.id.updatebt);
        deletebt=findViewById(R.id.deletebt);
        Stu_add=findViewById(R.id.stuaddress);
        Stu_id=findViewById(R.id.stuid);
        save_bt=findViewById(R.id.savebt);
        Stu_contact=findViewById(R.id.stucontect);
        save();
        showdata();
        updatedata();
        DeleteData();

    }
    public void save(){
       save_bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
          int id=Integer.parseInt(Stu_id.getText().toString());
          String name=Stu_name.getText().toString();
               String address=Stu_add.getText().toString();
              String contact=Stu_contact.getText().toString();
              long result= mydb.insertData(id,name,address);
               if(result==-1){
                   Toast.makeText(getApplicationContext(),"No Record Added",Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getApplicationContext(),"Record Added",Toast.LENGTH_SHORT).show();
                   }
           }
       });
    }
    public void showdata()
    {
        showbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor cur=mydb.fetchAllData();
                if(cur.moveToNext())
                {
                    do {
                        Toast.makeText(getApplicationContext(),"Id="+cur.getString(0)+"\n"+"Name="+cur.getString(1)+"\n"+"Address="+cur.getString(2)+"\nContect no="+cur.getString(3),Toast.LENGTH_LONG).show();

                    }while (cur.moveToNext());
                }

            }
        });
    }
    public  void updatedata(){
        updatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(Stu_id.getText().toString());
                String name=Stu_name.getText().toString();
                String address=Stu_add.getText().toString();
                String contact=Stu_contact.getText().toString();
                boolean result=mydb.updateData(id,name,address,contact);
                if(result==true)
                    Toast.makeText(getApplicationContext(),"Record update",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Record  not update",Toast.LENGTH_LONG).show();

            }
        });
    }
    public  void DeleteData(){
        deletebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(Stu_id.getText().toString());
                boolean res=mydb.Delete(id);
                if(res==true)
                    Toast.makeText(getApplicationContext(),"Recond Delete",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Recond not Delete",Toast.LENGTH_LONG).show();
            }
        });
    }
}