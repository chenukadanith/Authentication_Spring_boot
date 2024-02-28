package com.auth1.Registration;

public record RegistrationRequest(String firstName,
                                  String lastName,

                                  String email,
                                  String password,
                                  String role) {
}
