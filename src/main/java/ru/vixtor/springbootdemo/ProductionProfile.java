package ru.vixtor.springbootdemo;

public class ProductionProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Production";
    }
}
