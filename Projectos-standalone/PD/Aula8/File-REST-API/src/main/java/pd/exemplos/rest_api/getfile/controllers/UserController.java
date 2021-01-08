package pd.exemplos.rest_api.getfile.controllers;

import org.springframework.web.bind.annotation.*;
import pd.exemplos.rest_api.getfile.security.Token;
import pd.exemplos.rest_api.getfile.security.User;

@RestController
//@RequestMapping("user")
public class UserController
{
    @PostMapping("user/login")
    public User login(@RequestBody User user)
    {
        // TODO: Login with database (check username and password)?

        String token = Token.getNewToken(user.getUsername());
        user.setToken(token);
        return user;
    }
}

