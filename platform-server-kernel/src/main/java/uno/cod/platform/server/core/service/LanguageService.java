package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uno.cod.platform.server.core.dto.language.LanguageShowDto;
import uno.cod.platform.server.core.repository.LanguageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LanguageService {
    private final LanguageRepository repository;

    @Autowired
    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public List<LanguageShowDto> findAll() {
        return repository.findAll().stream().map(LanguageShowDto::new).collect(Collectors.toList());
    }
}
