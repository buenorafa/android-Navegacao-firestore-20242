package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun TelaBuscarPorId(modifier: Modifier = Modifier, usuarioDAO: UsuarioDAO = UsuarioDAO(), onGoBackClick: ()-> Unit) {
    var id by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var usuarioEncontrado by remember { mutableStateOf<Usuario?>(null) }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("Digite o ID do usuário") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                // Chama o método de busca passando o id digitado
                usuarioDAO.buscarPorId(id) { usuario ->
                    if (usuario != null) {
                        usuarioEncontrado = usuario
                        mensagem = "Usuário encontrado: ${usuario.nome}"
                    } else {
                        usuarioEncontrado = null
                        mensagem = "Usuário não encontrado"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }
        Button(onClick = {onGoBackClick()}, modifier = Modifier.fillMaxWidth()) { Text("Voltar") }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = mensagem)

    }
}
