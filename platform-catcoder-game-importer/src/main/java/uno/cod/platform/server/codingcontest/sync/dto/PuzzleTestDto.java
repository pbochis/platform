package uno.cod.platform.server.codingcontest.sync.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PuzzleTestDto {

    @JacksonXmlProperty(isAttribute = true, localName = "testid")
    private String index;

    private String data;
    private String solution;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
