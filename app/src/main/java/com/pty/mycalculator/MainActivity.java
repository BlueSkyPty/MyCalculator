package com.pty.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private List<Item> items=new ArrayList<Item>();
    private boolean editflag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        TextView formula=(TextView)findViewById(R.id.formula_area);
        TextView result=(TextView)findViewById(R.id.result_area);
        switch (view.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
            {
                Button btn=(Button) view;
                String strAdded=btn.getText().toString();

                formula.append(strAdded);

            }
            break;

            /**
             *    +5*9连续运算
             *    从结果继续运算
             *    bool flag  按下运算符就变为false    按下数字就为true 连续输入运算符时以最后输入的为准
             */
            case R.id.btn_add:
            {

                try {
                    String str = formula.getText().toString();
                    if (!str.equals("")) {
                        items.add(new Item(Double.parseDouble(str), Operator.NUM));
                    } else if(str.equals("")){
                        items.add(new Item(0, Operator.NUM));
                    }
                    items.add(new Item(0, Operator.ADD));
                    checkAndCompute();
                    formula.setText("");
                }
                catch (Exception e){
                    Toast.makeText(this,"错误！",Toast.LENGTH_SHORT ).show();
                }

            }
            break;
            case R.id.btn_sub:
            {
                try {
                    String str = formula.getText().toString();
                    if (!str.equals("")) {
                        items.add(new Item(Double.parseDouble(str), Operator.NUM));
                    } else {
                        items.add(new Item(0, Operator.NUM));
                    }
                    items.add(new Item(0, Operator.SUB));
                    checkAndCompute();
                    formula.setText("");
                }
                catch (Exception e){
                    Toast.makeText(this,"错误！",Toast.LENGTH_SHORT ).show();
                }
            }
            break;
            case R.id.btn_mul:
            {
                try {
                    String str = formula.getText().toString();
                    if (!str.equals("")) {
                        items.add(new Item(Double.parseDouble(str), Operator.NUM));
                    } else {
                        items.add(new Item(0, Operator.NUM));
                    }
                    items.add(new Item(0, Operator.MUL));
                    checkAndCompute();
                    formula.setText("");
                }
                catch (Exception e){
                    Toast.makeText(this,"错误！",Toast.LENGTH_SHORT ).show();
                }
            }
            break;
            case R.id.btn_div:
            {
                try {
                    String str = formula.getText().toString();
                    if (!str.equals("")) {
                        items.add(new Item(Double.parseDouble(str), Operator.NUM));
                    } else {
                        items.add(new Item(0, Operator.NUM));
                    }
                    items.add(new Item(0, Operator.DIV));
                    checkAndCompute();
                    formula.setText("");
                }
                catch (Exception e){
                    Toast.makeText(this,"错误！",Toast.LENGTH_SHORT ).show();
                }
            }
            break;
            case R.id.btn_c:
            {
                formula.setText("");
                result.setText("");

            }
            break;
            case R.id.btn_del:
            {
                String strContent=formula.getText().toString();
                if(strContent.length()>0) {
                    strContent = strContent.substring(0, strContent.length() - 1);
                    formula.setText(strContent);
                }
            }
            break;
            case R.id.btn_equ:
            {
                String str=formula.getText().toString();

                try {
                    if(str!=null){
                        items.add(new Item(Double.parseDouble(str),Operator.NUM));
                    }else{
                        items.add(new Item(0,Operator.NUM));
                    }

                    checkAndCompute();

                    result.setText(""+items.get(0).value);
                    formula.setText("");
                    items.clear();

                }
                catch (Exception e){
                    Toast.makeText(this,"错误！",Toast.LENGTH_SHORT ).show();
                }

            }
            break;
        }
    }


    public void checkAndCompute(){
        try {
            if (items.size() >= 3) {

                double a = items.get(0).value;
                double b = items.get(2).value;
                int opt = items.get(1).operator;

                switch (opt) {
                    case Operator.ADD: {
                        items.clear();
                        items.add(new Item(a + b, Operator.NUM));
                    }

                    break;
                    case Operator.SUB: {
                        items.clear();
                        items.add(new Item(a - b, Operator.NUM));
                    }
                    break;

                    case Operator.MUL: {
                        items.clear();
                        items.add(new Item(a * b, Operator.NUM));
                    }
                    break;

                    case Operator.DIV: {
                        items.clear();
                        items.add(new Item(a / b, Operator.NUM));
                    }
                    break;


                    default:
                        break;
                }
            }
        }
        catch (Exception e){
            Toast.makeText(this,"错误！",Toast.LENGTH_SHORT ).show();
        }
        }


}
