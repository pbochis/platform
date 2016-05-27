package uno.cod.platform.server.codingcontest.sync.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "CCC")
public class CodingContestGameDto {
    @JacksonXmlProperty(localName = "description")
    private DescriptionDto description;

    @JacksonXmlProperty(localName = "puzzle")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PuzzleDto> puzzles;

    public List<PuzzleDto> getPuzzles() {
        return puzzles;
    }

    public String getCanonicalName() {
        return description.getCanonicalName().replace(" ", "-");
    }

    public String getVisibility() {
        return description.getVisibility();
    }

    public String getDescription() {
        return description.getText();
    }

    public String getTimeframe() {
        return description.getTimeframe();
    }

    public String getName() {
        return description.getName();
    }
}
