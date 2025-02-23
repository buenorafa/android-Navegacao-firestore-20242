package com.example.navegacao1.ui.telas

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TelaCadastrar(
    modifier: Modifier = Modifier,
    usuarioDAO: UsuarioDAO = UsuarioDAO(),
    onCadastroSucesso: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Cria um novo objeto Usuario com os dados digitados
                val novoUsuario = Usuario(nome = nome, senha = senha)
                // Chama o méto-do adicionar do UsuarioDAO
                usuarioDAO.adicionar(novoUsuario) { usuarioAdicionado ->
                    if(usuarioAdicionado != null){
                    mensagem = "Usuário ${usuarioAdicionado.nome} adicionado com sucesso!"
                    // Após o cadastro, você pode redirecionar para outra tela, como a de login
                    onCadastroSucesso()
                    } else {mensagem = "Erro"}
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = mensagem)
    }
}
