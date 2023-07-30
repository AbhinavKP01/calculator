package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Script;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV;
    TextView resultsTV;
    String workings ="";
    String formula ="";
    String tempFormula ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }
    private void initTextView(){
        workingsTV = (TextView) findViewById(R.id.data);
        resultsTV = (TextView) findViewById(R.id.output);
    }
    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }
    public void equalsOnClick(View view){
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rihno");
        checkForPowerOf();

        try {
            result =(double)engine.eval(formula);
        }
        catch(ScriptException e){
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
        }
        if(result != null){
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }
    }
    private void checkForPowerOf(){
        ArrayList<Integer> indexOfPowers = new ArrayList<>();//1,5
        for (int i = 0;i < workings.length();i++){
            if (workings.charAt(i)=='^')
                indexOfPowers.add(i);
        }
        formula = workings;
        tempFormula = workings;
        for (Integer index :indexOfPowers){
            changeFormula(index);
        }
        formula = tempFormula;
    }
    private void  changeFormula(Integer index){
        String numberLeft = "";
        String numberRight = "";

        for (int i= index+1; i< workings.length();i++){
            if ((isNumeric(workings.charAt(i))))
               numberRight = numberRight + workings.charAt(i);
            else
                break;
        }
        for (int i= index-1; i>=0;i--){
            if ((isNumeric(workings.charAt(i))))
                numberLeft = workings.charAt(i) + numberLeft;
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original, changed);
    }
     private  boolean isNumeric(char ch){
        if ((ch <='9'&& ch>='0')|| ch=='.')
            return true;
        return false;
     }

     public void  clearOnclick(View view){
        workingsTV.setText("");
        workings ="";
        resultsTV.setText("");
        leftBracket = true ;
     }
     boolean leftBracket =true;
     public void  bracketsOnclick(View view){
        if (leftBracket == true){
            setWorkings("(");
            leftBracket = false;
        }
        else {
            setWorkings(")");
            leftBracket = true;
        }
    }
    public void  powerOfOnclick(View view){
         setWorkings("^");
    }
    public void  divisionOnclick(View view){
        setWorkings("/");
    }
    public void  zeroOnclick(View view){
        setWorkings("0");
    }
    public void  oneOnclick(View view){
        setWorkings("1");
    }
    public void twoOnclick(View view){
        setWorkings("2");
    }
    public void threeOnclick(View view){
        setWorkings("3");
    }
    public void fourOnclick(View view){
        setWorkings("4");
    }
    public void fiveOnclick(View view){
        setWorkings("5");
    }
    public void sixOnclick(View view){
        setWorkings("6");
    }
    public void sevenOnclick(View view){
        setWorkings("7");
    }
    public void eightOnclick(View view){
        setWorkings("8");
    }
    public void nineOnclick(View view){
        setWorkings("9");
    }
    public void addOnclick(View view){
        setWorkings("+");
    }
    public void substractOnclick(View view){
        setWorkings("-");
    }
    public void multiplyOnclick(View view){
        setWorkings("*");
    }
}