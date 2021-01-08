package pd.exemplos.rest_api.getfile.controllers;

import org.springframework.web.bind.annotation.*;
import pd.exemplos.rest_api.getfile.security.Token;
import pd.exemplos.rest_api.getfile.security.User;

@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("login")
    public User login(@RequestBody User user) {
        // TODO: Login with database?
        String token = Token.getNewToken(user.getUsername());
        user.setToken(token);
        System.out.println("user "+user+" criado.");
        return user;
    }
}
