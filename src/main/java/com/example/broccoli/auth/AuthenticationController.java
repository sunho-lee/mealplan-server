package com.example.broccoli.auth;

import com.example.broccoli.auth.dto.AuthenticateRequest;
import com.example.broccoli.auth.dto.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    @GetMapping("/api/v1/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request){
        return ResponseEntity.ok(request.getEmail()+"\n"+request.getPassword());
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok("sign up");
    }

    @GetMapping("/api/v1/items")
    public String itemsEndPoint(){
        return "ddd";
    }

   // @RequestMapping(value = "/api/v1/authenticate", method = RequestMethod.POST)
  // public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest request) throws Exception {
  //      return ;
  // }

  // @RequestMapping(value = "/api/v1/signup", method = RequestMethod.POST)
   //public ResponseEntity<?>  saveUser(@RequestBody AuthRequest user ) throws Exception {
   //   return ResponseEntity.ok();
   //}

}
