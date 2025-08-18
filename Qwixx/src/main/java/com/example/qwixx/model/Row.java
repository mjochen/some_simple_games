package com.example.qwixx.model;

public class Row {
    private List<Integer> numbers;
    private List<Boolean> ticked;

    // constructor
    public Row(List<Integer> numbers, List<Boolean> ticked) {
        this.numbers = numbers;
        this.ticked = ticked;
    }

    // getters + setters
    public List<Integer> getNumbers() { return numbers; }
    public void setNumbers(List<Integer> numbers) { this.numbers = numbers; }

    public List<Boolean> getTicked() { return ticked; }
    public void setTicked(List<Boolean> ticked) { this.ticked = ticked; }
}
