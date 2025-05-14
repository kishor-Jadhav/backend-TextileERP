package com.kj.textile.TextileERP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data 
public class PasswordModel {
    private String email;
    private String oldPass;
    private String newPass;
}
