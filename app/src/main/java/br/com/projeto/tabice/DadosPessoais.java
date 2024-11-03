package br.com.projeto.tabice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DadosPessoais extends AppCompatActivity {

    private TextView textViewNome;
    private TextView textViewEmail;
    private TextView textViewCpf;
    private TextView textViewTelefone;
    private TextView textViewEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dados_pessoais);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewNome = findViewById(R.id.textViewNome);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewCpf = findViewById(R.id.textViewCpf);
        textViewTelefone = findViewById(R.id.textViewTelefone);
        textViewEndereco = findViewById(R.id.textViewEndereco);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("NOME");
        String email = intent.getStringExtra("EMAIL");
        String cpf = intent.getStringExtra("CPF");
        String telefone = intent.getStringExtra("TELEFONE");
        String endereco = intent.getStringExtra("ENDERECO");

        if (nome == null || email == null || cpf == null || telefone == null || endereco == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
            nome = sharedPreferences.getString("NOME", "Nenhum dado encontrado.");
            email = sharedPreferences.getString("EMAIL", "Nenhum e-mail encontrado.");
            cpf = sharedPreferences.getString("CPF", "Nenhum CPF encontrado.");
            telefone = sharedPreferences.getString("TELEFONE", "Nenhum telefone encontrado.");
            endereco = sharedPreferences.getString("ENDERECO", "Nenhum endereço encontrado.");
        }

        textViewNome.setText(nome);
        textViewEmail.setText(email);
        textViewCpf.setText(cpf);
        textViewTelefone.setText(telefone);
        textViewEndereco.setText(endereco);

        ImageButton calendario1 = findViewById(R.id.calendario1);
        calendario1.setOnClickListener(v -> {
            Intent agendaIntent = new Intent(DadosPessoais.this, Agenda.class);
            startActivity(agendaIntent);
        });

        ImageButton concluido1 = findViewById(R.id.concluido1);
        concluido1.setOnClickListener(v -> {
            Toast.makeText(DadosPessoais.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); // Finaliza a atividade
        });

        ImageButton casa1 = findViewById(R.id.casa1);
        casa1.setOnClickListener(v -> {
            Intent homeIntent = new Intent(DadosPessoais.this, Home.class);
            startActivity(homeIntent);
        });

        Button sair = findViewById(R.id.sair);
        sair.setOnClickListener(v -> {
            Intent homeIntent = new Intent(DadosPessoais.this, MainActivity.class);
            startActivity(homeIntent);
        });
    }
}
