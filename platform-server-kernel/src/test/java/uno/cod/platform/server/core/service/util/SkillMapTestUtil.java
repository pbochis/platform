package uno.cod.platform.server.core.service.util;

import uno.cod.platform.server.core.domain.CodingSkill;

import java.util.HashMap;
import java.util.Map;

public class SkillMapTestUtil {
    public static Map<CodingSkill, Double> getValidSkillMap() {
        Map<CodingSkill, Double> skills = new HashMap<>();
        skills.put(CodingSkill.CODING_SPEED, 0.2d);
        skills.put(CodingSkill.READABILITY, 0.6d);
        skills.put(CodingSkill.ALGORITHMICS, 0.2d);
        return skills;
    }
}
