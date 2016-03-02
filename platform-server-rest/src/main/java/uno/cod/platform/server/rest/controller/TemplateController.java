package uno.cod.platform.server.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uno.cod.platform.server.core.domain.Template;
import uno.cod.platform.server.core.repository.TemplateRepository;
import uno.cod.platform.server.rest.RestUrls;
import uno.cod.storage.PlatformStorage;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Created by Paul on 3/2/2016.
 */
@RestController
public class TemplateController {
    private final TemplateRepository templateRepository;
    private final PlatformStorage storage;

    @Autowired
    public TemplateController(TemplateRepository templateRepository, PlatformStorage storage){
        this.templateRepository = templateRepository;
        this.storage = storage;
    }

    @RequestMapping(path = RestUrls.TEMPLATES_ID, method = RequestMethod.GET)
    public ResponseEntity<String> getTemplateUrl(@PathVariable Long id) throws GeneralSecurityException, UnsupportedEncodingException {
        Template template = templateRepository.findOne(id);
        //set expiration time to 2 hours
        Long expiration = (System.currentTimeMillis() / 1000) + 7200;
        return new ResponseEntity<>(storage.exposeFile(Template.BUCKET, template.filePath(), expiration), HttpStatus.OK);
    }
}
