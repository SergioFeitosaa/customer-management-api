package br.com.sergio.customer_management_api.dto;

public record LoginRequestDTO(

        Long id,
        String email,
        String password
) {


}
