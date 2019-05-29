package bookshare.api.controllers;

        import bookshare.api.entities.UserEntity;
        import bookshare.api.models.UserRegRequest;
        import bookshare.api.models.UserRegResponse;
        import bookshare.api.repositories.UserRepository;
        import bookshare.api.repositories.impl.UserRepositoryImpl;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepositoryImpl();
    }

    @PostMapping("/api/user/register")
    public UserRegResponse register(@RequestBody UserRegRequest regRequest) throws Exception {

        UserEntity newUser = new UserEntity();
        newUser.setUsername(regRequest.getUsername());
        newUser.setEmail(regRequest.getEmail());
        newUser.setPassword(regRequest.getPassword());
        newUser.setFirstName(regRequest.getFirstName());
        newUser.setLastName(regRequest.getLastName());
        newUser.setCity(regRequest.getCity());
        newUser.setPhone(regRequest.getPhone());


        newUser.setActive(true);

        UserEntity insertedUser = this.userRepository.insert(newUser);

        UserRegResponse regResponse = new UserRegResponse();
        regResponse.setUserId(insertedUser.getId());

        return regResponse;
    }

}