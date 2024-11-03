package br.com.projeto.tabice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cadastro extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextCpf, editTextTelefone, editTextEndereco, editTextSenha;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextSenha = findViewById(R.id.editTextTextPassword2);
        buttonCadastrar = findViewById(R.id.buttonCadastro);

        buttonCadastrar.setOnClickListener(v -> {
            String nome = editTextNome.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String cpf = editTextCpf.getText().toString().trim();
            String telefone = editTextTelefone.getText().toString().trim();
            String endereco = editTextEndereco.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();

            if (!nome.isEmpty() && !email.isEmpty() && !cpf.isEmpty() && !telefone.isEmpty() && !endereco.isEmpty() && !senha.isEmpty()) {
                if (isValidEmail(email)) {
                    // Salvar dados no SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("NOME", nome);
                    editor.putString("EMAIL", email);
                    editor.putString("PASSWORD", senha); // Proteja a senha adequadamente
                    editor.putString("CPF", cpf);
                    editor.putString("TELEFONE", telefone);
                    editor.putString("ENDERECO", endereco);
                    editor.apply();

                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Cadastro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Cadastro.this, "Por favor, insira um email válido.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Cadastro.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
