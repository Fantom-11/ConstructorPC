package org.example.domain;

public enum Component {
    PROCESSOR("json/processors.json"),
    MOTHER_BOARD("json/m_board.json"),
    HDD("json/hdd.json"),
    RAM("json/ram.json");

    private String fileName;

    Component(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
