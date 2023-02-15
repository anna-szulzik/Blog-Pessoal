package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){

        usuarioRepository.deleteAll();

        usuarioRepository.save(new Usuario(0L, "Nicole Mota", "nic.mota@email.com.br", "13465278", "https://i.imgur.com/NtyGneo.jpg"));

        usuarioRepository.save(new Usuario(0L, "Victor Alves", "victor_alves17@email.com", "13465278", "https://i.imgur.com/FETvs2O.jpg"));

        usuarioRepository.save(new Usuario(0L, "Ingrid Ramos", "ingrid.bramos@email.com.br", "13465278", "https://i.imgur.com/mB3VM2N.jpg"));

        usuarioRepository.save(new Usuario(0L, "Bruno Nunes", "bruno_nunes9@email.com", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

    }

    @Test
    @DisplayName("Retorna 1 usuario")
    public void deveRetornarUmUsuario() {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("nic.mota@email.com.br");

        assertTrue(usuario.get().getUsuario().equals("nic.mota@email.com.br"));
    }

    @Test
    @DisplayName("Retorna 3 usuários")
    public void deveRetornarTresUsuarios() {

        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");

        assertEquals(3, listaDeUsuarios.size());

        assertTrue(listaDeUsuarios.get(0).getNome().equals("Nicole Mota"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("Victor Alves"));
        assertTrue(listaDeUsuarios.get(2).getNome().equals("Ingrid ramos"));

    }

    @AfterAll
    public void end() {
        usuarioRepository.deleteAll();
    }

}