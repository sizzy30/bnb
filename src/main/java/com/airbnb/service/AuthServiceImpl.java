package com.airbnb.service;

import com.airbnb.AppUserRepository;
import com.airbnb.entity.AppUser;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.AppUserDto;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import com.airbnb.payload.LoginDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{
    private AppUserRepository appUserRepository ;
private JWTService jwtService;
    public AuthServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AppUserDto create(AppUserDto appUserDto) {
        AppUser appUser = maptoEntity(appUserDto);
        Optional<AppUser> opEmail=appUserRepository.findByEmail(appUser.getEmail());
        if(opEmail.isPresent()){
            throw new UserExists("email exists already");
        }
        Optional<AppUser> opUsername=appUserRepository.findByUsername(appUser.getUsername());
        if(opUsername.isPresent()){
            throw new UserExists("username exists already");
        }
        String hashpw = BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt(5));
        appUser.setPassword(hashpw);

        AppUser save = appUserRepository.save(appUser);
        AppUserDto dto=maptoDto(save);
        return dto;


    }

    @Override
    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> byUsername = appUserRepository.findByUsername(LoginDto.getUsername());
        if(byUsername.isPresent()){
            AppUser user =byUsername.get();
            if( BCrypt.checkpw(dto.getPassword(),user.getPassword())){
            return jwtService.generateToken(user);
         }
        }
        return null;
    }
//MaptoEntity
    public AppUser maptoEntity(AppUserDto dto){
        AppUser user=new AppUser();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        return user;
    }
    //MaptoDto
    public AppUserDto maptoDto(AppUser user){
        AppUserDto dto=new AppUserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }
}
