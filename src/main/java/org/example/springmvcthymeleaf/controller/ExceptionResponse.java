package org.example.springmvcthymeleaf.controller;

import lombok.Data;

//Класс для отправки сообщений об ошибках
@Data
public class ExceptionResponse {

    private String reason;

}
