package com.exemplo.meuprojeto07;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Principal extends Activity implements OnClickListener {
	Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Recuperação do Botão
        botao = (Button) findViewById(R.id.btnListar);
        //Registro do botão junto ao Ouvinte
        botao.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//Conexao e abertura do Banco de Dados
		SQLiteDatabase db = openOrCreateDatabase("locadora.db", Context.MODE_PRIVATE, null);
		//Instrução SQL a ser executada. Estamos usando um atributo para consulta
		//Escolhemos o ano por ser inteiro e podermos demonstrar que podemos
		//usar o array de parametros (obrigatoriamente um array de Strings)
		//para este propósito
		String sql = "select marca,modelo,ano from carros where ano = ?";
		//Parâmetro
		String[] parametros = {"2000"};
		//Estamos usando um Cursor que funciona semelhante ao resultset do JDBC
		Cursor c = db.rawQuery(sql, parametros);
		while (c.moveToNext()) {
			//O argumento 0 do método getString esta associado a marca
			//Para obter o modelo deveríamos usar o 1 e para o ano o 2
			//c.getInt(2) -> ano
			Toast.makeText(this, "Marca:" + c.getString(0), Toast.LENGTH_SHORT).show();
		}
		
	}
}
