package com.example.pr1shpref;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText et1;
	TextView tv2;
	TextView tv3;
	
	int numIa;
	int contIntentos;
	int contGan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.editText1);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        
		SharedPreferences prefe1=getSharedPreferences("archivo1", Context.MODE_PRIVATE);
		String ganaHist=prefe1.getString("win", "");
        
		if (ganaHist==null) {
	        contGan=0;
	        
	    	String Historial=String.valueOf(contGan);	        	
	    	
	    	Editor editor1=prefe1.edit();
	    	editor1.putString("win", Historial);
	    	editor1.commit();
		} else if (ganaHist!=null) {
	        contGan=Integer.parseInt(ganaHist);
		}
			
        sortear();
    }
    
    
    public void sortear () {
        numIa=1+(int)(Math.random()*50);
    }
    
   
    public void verificar (View v) {
    	String cadena=et1.getText().toString();
    	
    	if (cadena=="") {
    		Toast.makeText(this, "No has ingresado algún número.", Toast.LENGTH_LONG).show();
    	}else{
	    	int numUser=Integer.parseInt(cadena);
    		contIntentos=contIntentos+1;
    		    		
	    	if (numIa>numUser){
	    		Toast.makeText(this, "Tu número es MENOR al mío.", Toast.LENGTH_LONG).show();
	    		tv2.setText("Has realizado "+contIntentos+" intentos en esta sesión.");
	    		}
	    	else if(numIa<numUser){
	    		Toast.makeText(this, "Tu número es MAYOR al mío.", Toast.LENGTH_LONG).show();
	    		tv2.setText("Has realizado "+contIntentos+" intentos en esta sesión.");
	    	}
	    	else if(numIa==numUser){
	    		Toast.makeText(this, "Tu número "+numUser+" es IGUAL al mío, ¡GANASTE!", Toast.LENGTH_LONG).show();
	    		tv2.setText("Has realizado "+contIntentos+" intentos en esta sesión.");
	    		
	    		contGan=contGan+1;
	    		
			    SharedPreferences prefe1=getSharedPreferences("archivo1", Context.MODE_PRIVATE);
			        	
			    String Historial=String.valueOf(contGan);	        	
		
			    Editor editor1=prefe1.edit();
			    editor1.putString("win", Historial);
			    editor1.commit();
		    		
		        tv3.setText("Has ganado "+contGan+" veces en total, en toda la historia.");
		            
		        et1.setText("");	    		
		        sortear();
	    	}
    	}
    }
    
    
    public void reiniciar (View v) {
    	sortear();
    	contIntentos=0;
    	tv2.setText("Buena suerte!");
    }
    
}
