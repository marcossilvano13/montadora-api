package br.com.senac.montadora_api.service;

import br.com.senac.montadora_api.dtos.TokenResponseDto;
import br.com.senac.montadora_api.dtos.UsuariosRequestDto;
import br.com.senac.montadora_api.entidades.Usuarios;
import br.com.senac.montadora_api.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public Usuarios criar(UsuariosRequestDto usuario){
        return usuariosRepositorio.save(this.usuariosRequestDtoParaUsuarios(usuario));
    }

    public TokenResponseDto login(UsuariosRequestDto usuario) {
        Optional<Usuarios> usuariosRetorno = usuariosRepositorio.findByEmail(usuario.getEmail());
        if (usuariosRetorno.isPresent()) {
            Usuarios usuarioDb = usuariosRetorno.get();
            if (passwordEncoder.matches(usuario.getSenha(), usuarioDb.getSenha())) {
                return new TokenResponseDto(jwtService.gerarToken(usuarioDb));
            }
            throw new RuntimeException("Senha Inválida!");
        }
        throw new RuntimeException("Usuario não Encontrado!");
    }

    private Usuarios usuariosRequestDtoParaUsuarios(UsuariosRequestDto entrada) {
        Usuarios saida = new Usuarios();
        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setSenha(passwordEncoder.encode(entrada.getSenha()));
        return saida;
    }
}
