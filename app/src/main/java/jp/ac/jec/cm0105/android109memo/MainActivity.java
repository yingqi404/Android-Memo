package jp.ac.jec.cm0105.android109memo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSave=findViewById(R.id.btnSave);     //save 按钮 ID --> view
        btnSave.setOnClickListener(new SaveClickListenerImpl());  //save(view) 点击的监听器创建save类对象

        Button btnLoad=findViewById(R.id.btnLoad);   //load 按钮 ID --> view
        btnLoad.setOnClickListener(new LoadClickListener());   //load(view) 点击的监听器创建load类对象
        new LoadClickListener().onClick(btnLoad);   //load按钮对象调用onClick函数且传参view子类型load按钮


        Button btnAdd=findViewById(R.id.btnAdd);   //load 按钮 ID --> view
        btnAdd.setOnClickListener(new AddClickListenerimpl());  //save(view) 点击的监听器创建save类对象

        Button btnDelete=findViewById(R.id.btnDelete);   //load 按钮 ID --> view
        btnDelete.setOnClickListener(new deleteClickListenerimpl());  //save(view) 点击的监听器创建save类对象

    }//onCreate end


    //Save 按钮 类
    class SaveClickListenerImpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TextView txtMemolist=findViewById(R.id.txtMemoList);  //蓝色区域 ID --> textView

            SharedPreferences sp = getSharedPreferences("android109", Context.MODE_PRIVATE);//获取SharedPreferences对象
            SharedPreferences.Editor edtr=sp.edit();//获取Editor对象的引用

            edtr.putString("MODE",txtMemolist.getText().toString().replace("\n",","));//把获取的值放入文件
            edtr.commit();     //edtr.commit()；提交数据。

            TextView txtDate=findViewById(R.id.txtDate);  //时间区域 ID --> textView

            Date currentDate=new Date();
            String dateString=String.valueOf(currentDate);

            SharedPreferences dateInstance = getSharedPreferences("android109", Context.MODE_PRIVATE);
            SharedPreferences.Editor date=dateInstance.edit();

            date.putString("DATE", dateString);
            date.commit();

            SimpleDateFormat esayDate=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            String StringTime=esayDate.format(currentDate);

            txtDate.setText(StringTime);  //String输入框 + String蓝色区域 --> textView蓝色区域
            Toast.makeText(MainActivity.this,"保存しました",Toast.LENGTH_LONG).show();
        }
    }


    //add 按钮 类
    class AddClickListenerimpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            EditText text=findViewById(R.id.edtMemo);   //输入框 ID --> EditView
            String addText=text.getText().toString();       //输入框 EditView --> String

            TextView txtMemolist=findViewById(R.id.txtMemoList);  //蓝色区域 ID --> textView
            String memoList=txtMemolist.getText().toString();  //蓝色区域 textView --> String

            txtMemolist.setText(memoList+(addText+"\n"));  //String输入框 + String蓝色区域 --> textView蓝色区域
            text.setText("");
        }
    }


    //load 按钮 类
    class LoadClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            TextView txtMemolist=findViewById(R.id.txtMemoList);  //蓝色区域 ID --> textView

            SharedPreferences load = getSharedPreferences("android109", Context.MODE_PRIVATE);
            String loadString=load.getString("MODE","notfound");

            txtMemolist.setText(loadString.replace(",","\n"));  //String输入框 + String蓝色区域 --> textView蓝色区域

        }
    }


    //delete 按钮 类
    class deleteClickListenerimpl implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            EditText text=findViewById(R.id.edtMemo);   //输入框 ID --> EditView
//           String addText=text.getText().toString();       //输入框 EditView --> String

            TextView txtMemolist=findViewById(R.id.txtMemoList);  //蓝色区域 ID --> textView
            String memoList=txtMemolist.getText().toString();  //蓝色区域 textView --> String

            txtMemolist.setText("");  //textView蓝色区域设置为空
            text.setText("");
        }
    }



}//MainActivity end