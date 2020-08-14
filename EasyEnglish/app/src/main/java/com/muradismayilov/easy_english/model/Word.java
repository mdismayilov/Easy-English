package com.muradismayilov.easy_english.model;

public class Word {
    private final String word;
    private final String meaning;
    private final String transcription;

    public Word(String word, String meaning, String transcription) {
        this.word = word;
        this.meaning = meaning;
        this.transcription = transcription;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getTranscription() {
        return transcription;
    }
}
