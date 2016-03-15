package uno.cod.platform.server.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.codingcontest.sync.dto.CodingcontestDto;
import uno.cod.platform.server.codingcontest.sync.dto.ContestInfoDto;
import uno.cod.platform.server.codingcontest.sync.dto.ParticipationDto;
import uno.cod.platform.server.core.security.AllowedForAdmin;

import java.util.UUID;

@RestController
public class CodingcontestController {

    @AllowedForAdmin
    @RequestMapping(value = "/contestuploadraw", method = RequestMethod.POST)
    public ResponseEntity<String> contestuploadraw(@RequestBody CodingcontestDto dto) {
        System.out.println(dto);
        //return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @AllowedForAdmin
    @RequestMapping(value = "/useruploadraw", method = RequestMethod.POST)
    public void useruploadraw(@RequestBody ParticipationDto dto) {
        System.out.println(dto);
    }

    @AllowedForAdmin
    @RequestMapping(value = "/api/users/{uuid}/activate", method = RequestMethod.POST)
    public void activate(@PathVariable("uuid") UUID uuid) {
        /* dummy */
    }


    @AllowedForAdmin
    @RequestMapping(value = "/api/contests/{uuid}/report/json", method = RequestMethod.GET)
    public ContestInfoDto getResults(@PathVariable("uuid") UUID uuid) {
        ContestInfoDto contestInfoDto = new ContestInfoDto();
        contestInfoDto.setUuid(uuid);
        return contestInfoDto;
    }
}