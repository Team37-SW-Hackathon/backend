package SWHackathonTeam37.SimplePrompt.domain.vo;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
public record Choice (
        String text,
        int index,
        String finishReason
) {}
