package uno.cod.platform.server.codingcontest.sync.dto.game;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class PuzzleDto {
    @JacksonXmlProperty(isAttribute = true, localName = "puzzleid")
    private String canonicalName;

    @JacksonXmlProperty(isAttribute = true, localName = "validationClass")
    private String validationClass;

    @JacksonXmlProperty(localName = "tests")
    private List<PuzzleTestDto> tests;

    @JacksonXmlProperty(localName = "explanation")
    private String instructionsFile;

    @JacksonXmlProperty(localName = "inputfile")
    private String inputFilePath;

    public String getCanonicalName() {
        return canonicalName;
    }

    public List<PuzzleTestDto> getTests() {
        return tests;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getInstructionsFile() {
        return instructionsFile;
    }

    public String getValidationClass() {
        return validationClass;
    }
}
