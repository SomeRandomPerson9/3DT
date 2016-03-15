package com.harry9137.api.util;

import java.util.Map;

public class EnvironmentVariables {
    public static Map<String,String> variables = null;
    public static void update(){
        variables = System.getenv();
    }
}
