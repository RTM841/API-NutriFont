package org.example.servicios;


import org.example.entidades.Rol;
import org.example.entidades.Usuario;
import org.example.repositorios.RoleRepository;
import org.example.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    JavaMailSender mailSender;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Optional<Rol> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List <Rol> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        if(usuario.isAdmin()){
            Optional<Rol> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMINSUPERMERCADO");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        usuario.setRoles(roles);
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByNombre(String nombre) throws UsernameNotFoundException {
        Optional<Usuario> userOptional = usuarioRepository.findByNombre(nombre);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException((String.format("Username %s no existe", nombre)));
        }
        Usuario usuario = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(usuario.getNombre(),
                usuario.getContrasenia(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities);

    }

    @Override
    @Transactional
    public Optional<Usuario> update(Long id, Usuario usuario) {
        Optional <Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioDB = usuarioOptional.orElseThrow();
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setContrasenia(usuario.getContrasenia());
            usuarioDB.setSupermercado(usuario.getSupermercado());
            return Optional.of(usuarioRepository.save(usuarioDB));
        }
        return usuarioOptional;
    }

    @Override
    public Optional<Usuario> delete(Long id) {
        Optional <Usuario> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.ifPresent( usuarioDb -> usuarioRepository.delete(usuarioDb));
        return usuarioOptional;
    }

    public String getRoleNamesByUserId(int userId) {
        return usuarioRepository.findRoleNamesByUserId(userId);
    }

    @Override
    public List<String> findAllCorreos() {
        return usuarioRepository.findAllCorreos();
    }


    public void sendVerificationEmail(Usuario user) {
        String verificationCode = user.generateVerificationCode();
        user.setCodigo_verificacion(verificationCode); // Guarda el código en el objeto User

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getCorreo().toString());
        message.setSubject("Código de verificación para " + "NutriFont");
        message.setText("Hola " + user.getNombre().toString() + ",\n\n" +
                "Tu código de verificación para completar tu registro en " + "NutriFont" + " es:\n\n" +
                verificationCode + "\n\n" +
                "Este código es válido por 5 minutos.\n\n" +
                "¡Un cordial saludo!\n\n" +
                "El equipo de " + "NutriFont");

        mailSender.send(message);
    }

    public boolean verifyUser(Usuario usuario, String codigoIngresado) {
        String storedCode = usuario.getCodigo_verificacion();

        // Verificar si los códigos coinciden
        if (storedCode != null && storedCode.equals(codigoIngresado)) {
            // Marcar la cuenta como verificada
            usuario.setEnabled(true);

            return true;
        }

        return false;
    }

}
