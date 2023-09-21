package com.example.sgaapplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class SgaApplication {

	public static void main(String[] args) {
		Application.launch(SGAJavaFX.class, args);
	}

}
