package ru.vixtor.springbootdemo;

public class DevProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Dev";
    }
}
