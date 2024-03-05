package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.dtos.UsuarioDTO;
import co.edu.uniminuto.gremlinsapi.entitys.Usuario;
import co.edu.uniminuto.gremlinsapi.enums.Estados;
import co.edu.uniminuto.gremlinsapi.exceptions.GeneralException;
import co.edu.uniminuto.gremlinsapi.mappers.UsuarioMapper;
import co.edu.uniminuto.gremlinsapi.repositorys.UsuarioRepository;
import co.edu.uniminuto.gremlinsapi.validations.LoginValidation;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = {"*"})
@RestController
public class LoginController {

	@Value("${jwt.key}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long timeExpiration;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LoginValidation validation;
	
	
	

	private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

	/*
	 * @Autowired public LoginController(UsuarioMapper _usuarioMapper) {
	 * this.usuarioMapper = _usuarioMapper; }
	 */

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Usuario userIn) {

		try {

			validation.login(userIn);

			Usuario usuarioOpt = usuarioRepository.findByEmailAndPassword(userIn.getEmail(), userIn.getPassword());
			usuarioOpt.getRole().getFunciones();
			
			

			// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			if (Objects.nonNull(usuarioOpt)) {
				if (usuarioOpt.getEstado().equals(Estados.DESACTIVADO)) {
					throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Error el usuario esta desactivado",
							null);
				}
				
				UsuarioDTO usuarioOut = UsuarioMapper.mapUsuarioToDTO(usuarioOpt); 
				
				String token = getJWTToken(usuarioOpt.getEmail(), "");
				usuarioOut.setToken(token);
				usuarioOut.setPassword(null);
				return ResponseEntity.ok(usuarioOut);
			} else {
				return new ResponseEntity<>("Error no existe el usuario  ", HttpStatus.BAD_REQUEST);
			}

		} catch (GeneralException e) {
			LOGGER.severe("Error al hacer login: " + e.getMessage());
			return new ResponseEntity<>(e.getMessageLogical(), e.getHttpStatus());

		} catch (Exception e) {
			LOGGER.severe("Error al hacer login: " + e.getMessage());
			return new ResponseEntity<>("Error al hacer login: ", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private String getJWTToken(String username, String roles) {

		// List<GrantedAuthority> grantedAuthorities =
		// AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

		String token = Jwts.builder().setId("softtekJWT").setSubject(username).claim("authorities", null)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + timeExpiration))
				.signWith(SignatureAlgorithm.HS512, this.secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}