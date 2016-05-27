package uno.cod.platform.server.codingcontest.sync.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DescriptionDto {
    @JacksonXmlProperty(localName = "id")
    private String canonicalName;
    @JacksonXmlProperty(localName = "displayName")
    private String name;
    private String visibility;
    private String text;
    private String timeframe;

    public String getCanonicalName() {
        return canonicalName != null && !canonicalName.equals("") ? canonicalName : getName();
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public String getName() {
        return name != null && !name.equals("") ? name : getText();
    }

    public void setName(String name) {
        this.name = name;
    }
}